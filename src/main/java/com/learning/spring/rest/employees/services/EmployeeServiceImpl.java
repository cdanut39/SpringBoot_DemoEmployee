package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.NoResultsException;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.CommunityMapper;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.User;
import com.learning.spring.rest.employees.utils.RandomPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.learning.spring.rest.employees.utils.Constants.*;
import static com.learning.spring.rest.employees.utils.comparators.EmployeeComparators.getMap;
import static com.learning.spring.rest.employees.utils.comparators.EmployeeComparators.reversed;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    private UserRepo userRepo;
    private CommunityServiceImpl communityService;
    private CommunityMapper communityMapper;
    private MailServiceImpl mailService;
    private RoleService roleService;
    private UserMapper userMapper;


    @Autowired
    public EmployeeServiceImpl(UserRepo userRepo, CommunityServiceImpl communityService, MailServiceImpl mailService, RoleService roleService) {
        this.userRepo = userRepo;
        this.communityService = communityService;
        this.mailService = mailService;
        this.roleService = roleService;

        userMapper = new UserMapper();
        communityMapper = new CommunityMapper();
    }

    /**
     * POST
     */
    @Transactional
    @Override
    public EmployeeDTO save(EmployeeDTO employeeDto) throws UserAlreadyExistsException, CommunityNotFoundByNameException {
        Employee employeeToBeSaved;
        String randomPassword = RandomPassword.generatePassword(PASSWORD_LENGTH);
        String email = employeeDto.getEmail();
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(USER_EXISTS, email);
        } else {
            Community community = communityService.findByName(employeeDto.getCommunityName());
            employeeToBeSaved = userMapper.convertFromEmpDtoTOEmployee(employeeDto);
            employeeToBeSaved.setPassword(new BCryptPasswordEncoder().encode(randomPassword));
            employeeToBeSaved.setCommunity(community);
            employeeToBeSaved.setRoles(roleService.getEmpRoles());
        }
        Employee savedEmployee = userRepo.save(employeeToBeSaved);
//        new Thread(() -> mailService.sendEmail(savedEmployee.getEmail(), savedEmployee.getFirstName() + " " + savedEmployee.getLastName(), savedEmployee.getEmail(), randomPassword)).start();
        return userMapper.convertFromEmpTOEmployeeDTO(savedEmployee);
    }

    /**
     * GET
     */
    @Override
    public EmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = userRepo.findEmployeeById(id);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException(EMPLOYEE_404 + id, id);
        }
        return userMapper.convertFromEmpTOEmployeeDTO(employee.get());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> allEmployees = userRepo.findAllEmployees();
        return allEmployees.stream().map(userMapper::convertFromEmpTOEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesSortedByCriteria(String criteria, String direction) {

        List<Employee> employees = userRepo.findAllEmployees();
        List<Employee> sortedList;

        if (direction.equals("DESC")) {
            employees.sort(reversed(getMap().get(criteria)));
        } else employees.sort(getMap().get(criteria));
        sortedList = employees;
        return sortedList.stream().map(userMapper::convertFromEmpTOEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> searchEmployeeBy(String lastName, String communityName) throws NoResultsException {
        Employee emp = new Employee();
        emp.setLastName(lastName);
        BaseCommunityDTO communityDTO = communityService.searchByName(communityName);
        emp.setCommunity(communityMapper.convertFromBaseCommunityDtoToCommunity(communityDTO));
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<Employee> example = Example.of(emp, exampleMatcher);
        List<Employee> employees = userRepo.findAll(example);
        return employees.stream().map(userMapper::convertFromEmpTOEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public List<EmployeeDTO> getEmployeesWithPagination(int page, int size, String criteria) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(criteria));
        return userRepo.findAllEmployees(pageable).stream().map(emp -> userMapper.convertFromEmpTOEmployeeDTO(emp)).collect(Collectors.toList());
    }

    /**
     * PUT
     */
    @Override
    @Transactional
    public EmployeeDTO updateEmployee(int id, EmployeeDTO emp) throws EmployeeNotFoundException {

        Employee employeeToBeUpdated = userRepo.findEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_404 + id, id));
        employeeToBeUpdated.setLastName(emp.getLastName());
        employeeToBeUpdated.setPassword(new BCryptPasswordEncoder().encode(emp.getPassword()));
        employeeToBeUpdated.setPhoneNumber(emp.getPhoneNumber());
        userRepo.save(employeeToBeUpdated);
        EmployeeDTO employeeDTO = userMapper.convertFromEmpTOEmployeeDTO(employeeToBeUpdated);
        log.info("Details of employee with id:{} were successfully updated!", id);
        return employeeDTO;
    }


    @Override
    @Transactional
    public EmployeeDTO assignCommunity(int employeeId, BaseCommunityDTO baseCommunityDTO) throws EmployeeNotFoundException, CommunityNotFoundByNameException {
        Optional<Employee> employee = userRepo.findEmployeeById(employeeId);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException(EMPLOYEE_404 + employeeId, employeeId);
        }
        Employee emp = employee.get();
        Community community = communityService.findByName(baseCommunityDTO.getCommunityName());
        emp.setCommunity(community);
        Employee savedEmployee = userRepo.save(emp);
        return userMapper.convertFromEmpTOEmployeeDTO(savedEmployee);

    }

    /**
     * DELETE
     */
    @Override
    public void removeEmployee(int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = userRepo.findEmployeeById(id);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException("Couldn't delete. Employee with id=" + id + " doesn't exist", id);
        } else {
            Employee emp = employee.get();
            userRepo.delete(emp);
            log.info("Successfully removed employee with id={},{}", emp.getUserId(), emp.getFirstName());
        }
    }


}



