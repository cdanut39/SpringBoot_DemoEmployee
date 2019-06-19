package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.learning.spring.rest.employees.utils.Constants.USER_EXISTS;
import static com.learning.spring.rest.employees.utils.comparators.EmployeeComparators.getMap;
import static com.learning.spring.rest.employees.utils.comparators.EmployeeComparators.reversed;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private UserRepo userRepo;
    private CommunityServiceImpl communityService;
    private UserMapper userMapper;

    @Autowired
    public EmployeeServiceImpl(UserRepo userRepo, CommunityServiceImpl communityService, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.communityService = communityService;
        this.userMapper = userMapper;
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = userRepo.findEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id=" + id, id);
        }
        employee.setCommunityName(employee);
        //        logger.info("Information for employee with id=" + id + ": Name={}, Salary={}", employee.getName(), employee.getSalary());
        return userMapper.convertFromEmpTOEmployeeDTO(employee);
    }


    @Transactional
    @Override
    public EmployeeDTO save(EmployeeDTO employeeDto) throws UserAlreadyExistsException {
        Employee employeeToBeSaved;
        String email = employeeDto.getEmail();
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(USER_EXISTS, email);
        } else {
            employeeToBeSaved = userMapper.convertFromEmpDtoTOEmployee(employeeDto);
        }
        Employee savedEmployee = userRepo.save(employeeToBeSaved);
        return userMapper.convertFromEmpToEmpDto(savedEmployee);
    }

    //    @Override
//    public EmployeePUTResponse_DTO updateEmployee(int id, EmployeePUTReq_DTO emp) throws EmployeeNotFoundException {
//
//        Employee employeeToBeUpdated = userRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
////        employeeToBeUpdated.setName(emp.getName());
//        employeeToBeUpdated.setSalary(emp.getSalary());
//        employeeToBeUpdated.setBonus(emp.isBonus());
//        userRepo.save(employeeToBeUpdated);
//        EmployeePUTResponse_DTO baseEmployeeDTO = userMapper.convertFromEmpToEmpPutResponseDto(employeeToBeUpdated);
//        logger.info("Details of employee with id:{} were successfully updated!", id);
//        return baseEmployeeDTO;
//    }
//
    @Override
    public EmployeeDTO assignCommunity(int employeeId, BaseCommunityDTO baseCommunityDTO) throws EmployeeNotFoundException, CommunityNotFoundByNameException {
        Employee employee = userRepo.findEmployeeById(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id=" + employeeId, employeeId);
        }
        Community community = communityService.findByName(baseCommunityDTO);
        employee.setCommunity(community);
        Employee savedEmployee = userRepo.save(employee);
        return userMapper.convertFromEmpTOEmployeeDTO(savedEmployee);

    }

    @Override
    public void removeEmployee(int id) throws EmployeeNotFoundException {
        Employee employee = userRepo.findEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Couldn't delete. Employee with id=" + id + " doesn't exist", id);
        } else {
            userRepo.delete(employee);
            logger.info("Successfully removed employee with id={},{}", employee.getUserId(), employee.getFirstName());
        }
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
    public List<EmployeeDTO> searchEmployeeBy(String lastName, String communityName) {
        Employee emp = new Employee();
        emp.setLastName(lastName);
        Community community = communityService.findByName(communityName);
        emp.setCommunity(community);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<Employee> example = Example.of(emp, exampleMatcher);
        List<Employee> employees = userRepo.findAll(example);
        return employees.stream().map(userMapper::convertFromEmpTOEmployeeDTO).collect(Collectors.toList());
    }

}

