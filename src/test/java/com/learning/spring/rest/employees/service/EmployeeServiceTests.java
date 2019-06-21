package com.learning.spring.rest.employees.service;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.mappers.CommunityMapper;
import com.learning.spring.rest.employees.mappers.UserMapper;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.User;
import com.learning.spring.rest.employees.services.CommunityServiceImpl;
import com.learning.spring.rest.employees.services.EmployeeServiceImpl;
import com.learning.spring.rest.employees.utils.RolesUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class EmployeeServiceTests {


    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    private UserRepo userRepo;
    @Mock
    private CommunityServiceImpl communityService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private CommunityMapper communityMapper;

    private EmployeeDTO DTO;

    @Before
    public void setUp() {
        DTO = new EmployeeDTO(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "parola", "dan@sv.ro", RolesUtility.getEmployeeRoleTypes(),
                2500, true, LocalDate.now(), "Bench");
    }

    @Test
    public void getAllEmployeesTest() {
        Employee employee1 = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dan@sv.ro", "parola", RolesUtility.getEmployeeRoleTypes(),
                2500, true, LocalDate.now(), new Community(1, "ITC"), null, "ITC", null);

        Employee employee2 = new Employee(2, "Georgian", "CRISTEA",
                User.Gender.M, 12345L, "georgian@sv.ro", "parola", RolesUtility.getEmployeeRoleTypes(),
                3000, true, LocalDate.now(), new Community(2, "ABC"), null, "ITC", null);

        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        when(userRepo.findAllEmployees()).thenReturn(employeeList);
        when(userMapper.convertFromEmpTOEmployeeDTO(any(Employee.class))).thenReturn(new EmployeeDTO());
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        Assert.assertEquals(2, employees.size());

    }

    @Test
    public void getEmployeeByValidIdTest() throws EmployeeNotFoundException {
        Employee employee1 = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dan@sv.ro", "parola", RolesUtility.getEmployeeRoleTypes(),
                2500, true, LocalDate.now(), new Community(1, "ITC"), null, "ITC", null);

        when(userRepo.findEmployeeById(employee1.getUserId())).thenReturn(Optional.of(employee1));
        employeeService.getEmployeeById(1);
        Assert.assertEquals("dan@sv.ro", employee1.getEmail());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployeeByInvalidIdTest() throws EmployeeNotFoundException {
        when(userRepo.findEmployeeById(2)).thenReturn(Optional.empty());
        employeeService.getEmployeeById(2);
    }

    @Test
    public void saveEmployeeTest() throws UserAlreadyExistsException {
//        EmployeeDTO DTO = new EmployeeDTO(1, "Danut", "CRISTEA",
//                User.Gender.M, 12345L, "parola", "dan@sv.ro", RolesUtility.getEmployeeRoleTypes(),
//                2500, true, LocalDate.now(), "Bench");

        Employee employee = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dan@sv.ro", "parola", RolesUtility.getEmployeeRoleTypes(),
                2500, true, LocalDate.now(), new Community(1, "Bench"), null, "Bench", null);

        when(userRepo.findByEmail(employee.getEmail())).thenReturn(Optional.empty());
        when(userRepo.save(employee)).thenReturn(employee);
        when(userMapper.convertFromEmpToEmpDto(any(Employee.class))).thenReturn(DTO);
        when(userMapper.convertFromEmpDtoTOEmployee(any(EmployeeDTO.class))).thenReturn(employee);

        EmployeeDTO employeeDTO = employeeService.save(DTO);
        Assert.assertEquals(employeeDTO.getEmail(), DTO.getEmail());
    }


    @Test(expected = UserAlreadyExistsException.class)
    public void saveExistingEmployeeTest() throws UserAlreadyExistsException {
//        EmployeeDTO DTO = new EmployeeDTO(1, "Danut", "CRISTEA",
//                User.Gender.M, 12345L, "parola", "dan@sv.ro", RolesUtility.getEmployeeRoleTypes(),
//                2500, true, LocalDate.now(), "Bench");

        Employee employee = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dan@sv.ro", "parola", RolesUtility.getEmployeeRoleTypes(),
                2500, true, LocalDate.now(), new Community(1, "Bench"), null, "Bench", null);

        when(userRepo.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
        when(userRepo.save(employee)).thenReturn(employee);
        when(userMapper.convertFromEmpToEmpDto(any(Employee.class))).thenReturn(DTO);
        when(userMapper.convertFromEmpDtoTOEmployee(any(EmployeeDTO.class))).thenReturn(employee);

        EmployeeDTO employeeDTO = employeeService.save(DTO);
    }

    @Test
    public void deleteEmployeeTest() throws EmployeeNotFoundException {
        Employee employee = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dan@sv.ro", "parola", RolesUtility.getEmployeeRoleTypes(),
                2500, true, LocalDate.now(), new Community(1, "Bench"), null, "Bench", null);
        when(userRepo.findEmployeeById(employee.getUserId())).thenReturn(Optional.of(employee));
        employeeService.removeEmployee(employee.getUserId());
        verify(userRepo, times(1)).delete(employee);
    }

}
