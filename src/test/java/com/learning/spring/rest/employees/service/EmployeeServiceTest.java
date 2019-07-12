package com.learning.spring.rest.employees.service;

import com.learning.spring.rest.employees.dao.UserRepo;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.custom.NoResultsException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Role;
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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static com.learning.spring.rest.employees.model.User.Gender.M;
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
        DTO = builder.setUserId(1).setFirstName("Danut").setCommunityName("QA").setLastName("Cristea").
                setEmail("dancristea@sv.ro").setPassword("parola").setPhoneNumber("333333333").setSex(M).build();
    }

    @Test
    public void getAllEmployeesTest() {


        Employee employee1 = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        Employee employee2 = Employee.builder().userId(2).firstName("Georgian").lastName("Cristea").email("georgiancristea@sv.ro").sex(M).
                phoneNumber("45646575786").community(new Community(2, "QA")).build();


        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        when(userRepo.findAllEmployees()).thenReturn(employeeList);
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        assertThat("List size is different", employees.size(), is(2));
        assertThat("Different email address ", employees.get(0).getEmail(), is(employee1.getEmail()));


    }

    @Test
    public void getEmployeeByValidIdTest() throws EmployeeNotFoundException {
        Employee employee1 = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        when(userRepo.findEmployeeById(employee1.getUserId())).thenReturn(Optional.of(employee1));
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(1);
        assertEquals("dancristea@sv.ro", employeeDTO.getEmail());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployeeByInvalidIdTest() throws EmployeeNotFoundException {
        when(userRepo.findEmployeeById(2)).thenReturn(Optional.empty());
        employeeService.getEmployeeById(2);
    }

    @Test
    public void saveEmployeeTest() throws UserAlreadyExistsException, CommunityNotFoundByNameException {
        Employee employee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();
        Role EMPLOYEE_ROLE = new Role(Role.RoleEnum.EMPLOYEE.name());
        Set<Role> employeeRoles = new HashSet<>();
        employeeRoles.add(EMPLOYEE_ROLE);

        when(userRepo.findByEmail(employee.getEmail())).thenReturn(Optional.empty());
        when(userRepo.save(any(Employee.class))).thenReturn(employee);
        when(roleService.getEmpRoles()).thenReturn(employeeRoles);
        when(communityService.findByName(anyString())).thenReturn(employee.getCommunity());
        EmployeeDTO employeeDTO = employeeService.save(DTO);
        assertEquals("dancristea@sv.ro", employeeDTO.getEmail());
    }


    @Test(expected = UserAlreadyExistsException.class)
    public void saveExistingEmployeeTest() throws UserAlreadyExistsException, CommunityNotFoundByNameException {

        Employee employee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        when(userRepo.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
        when(userRepo.save(employee)).thenReturn(employee);
        employeeService.save(DTO);
    }

    @Test
    public void deleteEmployeeTest() throws EmployeeNotFoundException {
        Employee employee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        when(userRepo.findEmployeeById(employee.getUserId())).thenReturn(Optional.of(employee));
        employeeService.removeEmployee(employee.getUserId());
        verify(userRepo, times(1)).delete(employee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void deleteEmployeeInvalidIdTest() throws EmployeeNotFoundException {
        when(userRepo.findEmployeeById(1)).thenReturn(Optional.empty());
        employeeService.removeEmployee(1);
    }

    @Test
    public void getEmployeesSortedASCByCriteriaASCTest() {

        Employee employee1 = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        Employee employee2 = Employee.builder().userId(2).firstName("Georgian").lastName("Cristea").email("georgiancristea@sv.ro").sex(M).
                phoneNumber("45646575786").community(new Community(2, "QA")).build();

        List<Employee> employeeList = Arrays.asList(employee1, employee2);

        when(userRepo.findAllEmployees()).thenReturn(employeeList);
        List<EmployeeDTO> employees = employeeService.getEmployeesSortedByCriteria("firstName", "ASC");
        assertEquals("Danut", employees.get(0).getFirstName());
    }

    @Test
    public void getEmployeesSortedDESCByCriteriaTest() {

        Employee employee1 = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        Employee employee2 = Employee.builder().userId(2).firstName("Georgian").lastName("Cristea").email("georgiancristea@sv.ro").sex(M).
                phoneNumber("45646575786").community(new Community(2, "QA")).build();

        List<Employee> employeeList = Arrays.asList(employee1, employee2);

        when(userRepo.findAllEmployees()).thenReturn(employeeList);
        List<EmployeeDTO> employees = employeeService.getEmployeesSortedByCriteria("firstName", "DESC");
        assertEquals("Georgian", employees.get(0).getFirstName());
    }

    @Test
    public void searchEmployeeByTest() throws NoResultsException {

        Employee employee1 = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        Employee employee2 = Employee.builder().userId(3).firstName("Stefan").lastName("Cristea").email("stefancristea@sv.ro").sex(M).
                phoneNumber("45646575786").community(new Community(2, "QA")).build();

        BaseCommunityDTO communityDTO = new BaseCommunityDTO("QA");
        when(communityService.searchByName(anyString())).thenReturn(communityDTO);
        when(userRepo.findAll(any(Example.class))).thenReturn(Arrays.asList(employee1, employee2));
        List<EmployeeDTO> employeeDTOS = employeeService.searchEmployeeBy("Cristea", "QA");
        assertEquals(employeeDTOS.size(), 2);
        assertThat(employeeDTOS.get(0).getFirstName(), is("Danut"));
        assertThat(employeeDTOS.get(1).getFirstName(), is("Stefan"));

    }

    @Test
    public void getEmployeesWithPaginationTest() {
        Employee employee1 = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();

        Employee employee2 = Employee.builder().userId(2).firstName("Georgian").lastName("Cristea").email("georgiancristea@sv.ro").sex(M).
                phoneNumber("45646575786").community(new Community(2, "QA")).build();

        Employee employee3 = Employee.builder().userId(3).firstName("Stefan").lastName("Cristea").email("stefancristea@sv.ro").sex(M).
                phoneNumber("45646575786").community(new Community(2, "QA")).build();


        when(userRepo.findAllEmployees(any(Pageable.class))).thenReturn(Arrays.asList(employee1, employee2, employee3));
        List<EmployeeDTO> employeeDTOList = employeeService.getEmployeesWithPagination(0, 4, "first_name");
        assertEquals(employeeDTOList.size(), 3);
        assertEquals("Danut", employeeDTOList.get(0).getFirstName());
    }

    @Test
    public void updateEmployeeTest() throws EmployeeNotFoundException {
        Employee oldEmployee = Employee.builder().userId(1).firstName("Danut").lastName("Bugan").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();
        Employee newEmployee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("333333333").community(new Community(2, "QA")).build();

        when(userRepo.findEmployeeById(anyInt())).thenReturn(Optional.of(oldEmployee));
        when(userRepo.save(any(Employee.class))).thenReturn(newEmployee);
        EmployeeDTO employeeDTO = employeeService.updateEmployee(DTO.getUserId(), DTO);
        assertEquals("Cristea", employeeDTO.getLastName());
        assertEquals(newEmployee.getPhoneNumber(), employeeDTO.getPhoneNumber());
    }

    @Test
    public void assignCommunityTest() throws CommunityNotFoundByNameException, EmployeeNotFoundException {
        Employee oldEmployee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();
        Employee newEmployee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "HR")).build();
        when(userRepo.findEmployeeById(anyInt())).thenReturn(Optional.of(oldEmployee));
        Community community = new Community(1, "HR");
        when(communityService.findByName(anyString())).thenReturn(community);
        when(userRepo.save(any(Employee.class))).thenReturn(newEmployee);
        EmployeeDTO employeeDTO = employeeService.assignCommunity(1, new BaseCommunityDTO("HR"));
        assertEquals("HR", employeeDTO.getCommunityName());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void assignCommunityEmployeeNotFoundTest() throws CommunityNotFoundByNameException, EmployeeNotFoundException {
        Employee oldEmployee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "QA")).build();
        Employee newEmployee = Employee.builder().userId(1).firstName("Danut").lastName("Cristea").email("dancristea@sv.ro").sex(M).
                phoneNumber("123456789").community(new Community(2, "HR")).build();
        when(userRepo.findEmployeeById(anyInt())).thenReturn(Optional.empty());
        employeeService.assignCommunity(1, new BaseCommunityDTO("HR"));

    }
}
