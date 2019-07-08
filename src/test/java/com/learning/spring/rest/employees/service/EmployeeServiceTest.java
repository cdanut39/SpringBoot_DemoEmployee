package com.learning.spring.rest.employees.service;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Role;
import com.learning.spring.rest.employees.model.User;
import com.learning.spring.rest.employees.services.CommunityServiceImpl;
import com.learning.spring.rest.employees.services.EmployeeServiceImpl;
import com.learning.spring.rest.employees.services.MailServiceImpl;
import com.learning.spring.rest.employees.services.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private CommunityServiceImpl communityService;

    @Mock
    private MailServiceImpl mailService;
    @Mock
    private RoleService roleService;

    private EmployeeDTO DTO;

    @Before
    public void setUp() {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();
        DTO = builder.setUserId(1).setFirstName("Danut").setCommunityName("QA").setLastName("Cristea").setEmail("dancristea@sv.ro").
                setPhoneNumber(123456789).setSex(User.Gender.M).build();
    }

    @Test
    public void getAllEmployeesTest() {
        Employee employee1 = new Employee(1, "Danut", "Cristea",
                User.Gender.M, 123456789L, "dancristea@sv.ro", "parola",
                LocalDate.now(), new Community(1, "QA"), null, "ITC", null);

        Employee employee2 = new Employee(2, "Georgian", "Cristea",
                User.Gender.M, 12345L, "georgian@sv.ro", "parola",
                LocalDate.now(), new Community(2, "ABC"), null, "ITC", null);

        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        when(userRepo.findAllEmployees()).thenReturn(employeeList);
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        assertThat("List size is different", employees.size(), is(2));
        assertThat("Different email address ", employees.get(0).getEmail(), is(employee1.getEmail()));


    }

    @Test
    public void getEmployeeByValidIdTest() throws EmployeeNotFoundException {
        Employee employee1 = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dancristea@sv.ro", "parola",
                LocalDate.now(), new Community(1, "ITC"), null, "ITC", null);

        when(userRepo.findEmployeeById(employee1.getUserId())).thenReturn(Optional.of(employee1));
        employeeService.getEmployeeById(1);
        assertEquals("dancristea@sv.ro", employee1.getEmail());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployeeByInvalidIdTest() throws EmployeeNotFoundException {
        when(userRepo.findEmployeeById(2)).thenReturn(Optional.empty());
        employeeService.getEmployeeById(2);
    }

    @Test
    public void saveEmployeeTest() throws UserAlreadyExistsException, CommunityNotFoundByNameException {
        Employee employee = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dancristea@sv.ro", "parola",
                LocalDate.now(), new Community(1, "Bench"), null, "Bench", null);
        Role EMPLOYEE_ROLE = new Role(Role.RoleEnum.EMPLOYEE.name());
        Set<Role> employeeRoles = new HashSet<>();
        employeeRoles.add(EMPLOYEE_ROLE);

        when(userRepo.findByEmail(employee.getEmail())).thenReturn(Optional.empty());
        when(userRepo.save(any(Employee.class))).thenReturn(employee);
        when(roleService.getEmpRoles()).thenReturn(employeeRoles);
        when(communityService.findByName(anyString())).thenReturn(employee.getCommunity());
        EmployeeDTO employeeDTO = employeeService.save(DTO);
        assertEquals(employeeDTO.getEmail(), DTO.getEmail());
    }


    @Test(expected = UserAlreadyExistsException.class)
    public void saveExistingEmployeeTest() throws UserAlreadyExistsException, CommunityNotFoundByNameException {

        Employee employee = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dancristea@sv.ro", "parola",
                LocalDate.now(), new Community(1, "Bench"), null, "Bench", null);

        when(userRepo.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
        when(userRepo.save(employee)).thenReturn(employee);

        EmployeeDTO employeeDTO = employeeService.save(DTO);
    }

    @Test
    public void deleteEmployeeTest() throws EmployeeNotFoundException {
        Employee employee = new Employee(1, "Danut", "CRISTEA",
                User.Gender.M, 12345L, "dancristea@sv.ro", "parola",
                LocalDate.now(), new Community(1, "Bench"), null, "Bench", null);
        when(userRepo.findEmployeeById(employee.getUserId())).thenReturn(Optional.of(employee));
        employeeService.removeEmployee(employee.getUserId());
        verify(userRepo, times(1)).delete(employee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void deleteEmployeeInvalidIdTest() throws EmployeeNotFoundException {
        when(userRepo.findEmployeeById(1)).thenReturn(Optional.empty());
        employeeService.removeEmployee(1);
    }

}
