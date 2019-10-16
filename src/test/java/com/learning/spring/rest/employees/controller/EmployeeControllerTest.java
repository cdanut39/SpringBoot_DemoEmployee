package com.learning.spring.rest.employees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.handler.ErrorHandlerController;
import com.learning.spring.rest.employees.services.EmployeeServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.*;

import static com.learning.spring.rest.employees.model.User.Gender.M;
import static com.learning.spring.rest.employees.utils.Constants.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.Every.everyItem;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    EmployeeController employeeController;

    private MockMvc mockMvc;

    private EmployeeDTO employeeDTO1;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).setControllerAdvice(new ErrorHandlerController()).build();
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();
        employeeDTO1 = builder.setUserId(1).setFirstName("Danut").setLastName("Cristea").setEmail("dancristea@sv.ro").setSex(M).
                setPhoneNumber("0722 000 000").setCommunityName("QA").setStartDate(LocalDate.parse("2020-06-25")).build();
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        when(employeeService.getUserById(employeeDTO1.getUserId())).thenReturn(employeeDTO1);
        mockMvc.perform(get("/employee/{id}", employeeDTO1.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email", is(employeeDTO1.getEmail())));
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();
        EmployeeDTO employeeDTO2 = builder.setUserId(2).setFirstName("Georgian").setCommunityName("Java").setLastName("Cristea").setEmail("georgiancristea@sv.ro").build();

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employeeDTO1, employeeDTO2));
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].email", is(employeeDTO1.getEmail())))
                .andExpect(jsonPath("[1].email", is(employeeDTO2.getEmail())));
    }

    @Test
    public void addEmployeeTest() throws Exception {

        when(employeeService.registerEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO1);
        mockMvc.perform(post("/register/employee")
                .contentType("application/json")
                .content(asJsonString(employeeDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", is(EMPLOYEE_ADDED)));
    }

    @Test
    public void addEmployeeInvalidData() throws Exception {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();
        EmployeeDTO employeeDTO = builder.setUserId(1).setFirstName("Danut").setCommunityName("QA").setLastName("Cristea").
                setPhoneNumber("+40767915086").build();
        mockMvc.perform(post("/register/employee")
                .contentType("application/json")
                .content(asJsonString(employeeDTO)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("errorMessage", is(EMPLOYEE_NOT_VALID)));
    }

    @Test
    public void searchEmployeesTest() throws Exception {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();
        EmployeeDTO employeeDTO2 = builder.setUserId(2).setFirstName("Georgian").setLastName("Cristea").
                setEmail("georgiancristea@sv.ro").setSex(M).setPhoneNumber("123456789").setCommunityName("QA")
                .setStartDate(LocalDate.parse("2018-11-26")).build();
        when(employeeService.searchEmployeeBy(anyString(), anyString())).thenReturn(Arrays.asList(employeeDTO1, employeeDTO2));
        mockMvc.perform(get("/search")
                .contentType("application/json")
                .param("lastname", "Cristea")
                .param("community", "QA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].lastName", everyItem(is("Cristea"))))
                .andExpect(jsonPath("$[*].communityName", everyItem(is("QA"))));
    }

    @Test
    public void searchEmployeesNoResultTest() throws Exception {

        when(employeeService.searchEmployeeBy(anyString(), anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/search")
                .contentType("application/json")
                .param("lastname", "Cristea")
                .param("community", "QA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("errorMessage", is(NO_RESULTS)));
    }


    @Test
    public void getEmployeesWithPaginationTest() throws Exception {
        int page = 0;
        int size = 4;
        String criteria = "last_name";

        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();
        EmployeeDTO employeeDTO2 = builder.setUserId(2).setFirstName("Georgian").setLastName("Cristea").
                setEmail("georgiancristea@sv.ro").setSex(M).setPhoneNumber("123456789").setCommunityName("QA")
                .setStartDate(LocalDate.parse("2018-11-26")).build();

        EmployeeDTO employeeDTO3 = builder.setUserId(2).setFirstName("Stefan").setLastName("Badalache").
                setEmail("stefanbadalache@sv.ro").setSex(M).setPhoneNumber("123456789").setCommunityName("QA")
                .setStartDate(LocalDate.parse("2017-06-15")).build();
        List<EmployeeDTO> allEmployees = Arrays.asList(employeeDTO3, employeeDTO2, employeeDTO1);
        HashMap<String, Object> pages = new LinkedHashMap<>();
        pages.put("totalPages", 1);
        pages.put("totalRecords", 3);
        pages.put("currentPage", 0);
        pages.put("employees", allEmployees);
        when(employeeService.getEmployeesWithPagination(page, size, criteria))
                .thenReturn(pages);
        mockMvc.perform(get("/getEmployees")
                .contentType("application/json")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", criteria))
                .andExpect(status().isOk())
                .andExpect(jsonPath("employees", hasSize(allEmployees.size())))
                .andExpect(jsonPath("totalPages", is(1)))
                .andExpect(jsonPath("totalRecords", is(3)))
                .andExpect(jsonPath("employees.[0].firstName", is("Stefan")));
    }

    @Test
    public void getEmployeesOrderByCriteriaTest() throws Exception {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();
        EmployeeDTO employeeDTO2 = builder.setUserId(2).setFirstName("Georgian").setLastName("Cristea").
                setEmail("georgiancristea@sv.ro").setSex(M).setPhoneNumber("123456789").setCommunityName("QA")
                .setStartDate(LocalDate.parse("2018-11-26")).build();

        EmployeeDTO employeeDTO3 = builder.setUserId(2).setFirstName("Stefan").setLastName("Badalache").
                setEmail("stefanbadalache@sv.ro").setSex(M).setPhoneNumber("123456789").setCommunityName("QA")
                .setStartDate(LocalDate.parse("2017-06-15")).build();
        List<EmployeeDTO> allEmployees = Arrays.asList(employeeDTO1, employeeDTO2, employeeDTO3);
        when(employeeService.getEmployeesSortedByCriteria(anyString(), anyString())).thenReturn(allEmployees);
        mockMvc.perform(get("/employees/orderBy/{criteria}/{direction}", "firstName", "DESC")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].firstName", Matchers.contains("Danut", "Georgian", "Stefan")));
    }

    @Test
    public void updateEmployeeTest() throws Exception {

        when(employeeService.updateEmployee(anyInt(), any(EmployeeDTO.class))).thenReturn(employeeDTO1);
        mockMvc.perform(put("/employee/update/{id}", 1)
                .contentType("application/json")
                .content(asJsonString(employeeDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", is(EMPLOYEE_MODIFIED)));
    }

    @Test
//    @DisplayName(value = "email is missing")
    public void updateEmployeeInvalidDataTest() throws Exception {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();

        EmployeeDTO modifiedEmployee = builder.setUserId(1).setFirstName("Georgian").setLastName("Cristea")
                .setSex(M).setPhoneNumber("1212121212").setCommunityName("QA")
                .setStartDate(LocalDate.parse("2018-11-26")).build();
        when(employeeService.updateEmployee(anyInt(), any(EmployeeDTO.class))).thenReturn(modifiedEmployee);
        mockMvc.perform(put("/employee/update/{id}", 1)
                .contentType("application/json")
                .content(asJsonString(modifiedEmployee)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("errorMessage", is(EMPLOYEE_NOT_VALID)));
    }

    @Test
    public void assignCommunityTest() throws Exception {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();

        EmployeeDTO employeeDTO = builder.setUserId(1).setFirstName("Danut").setLastName("Cristea")
                .setEmail("dancristea@sv.ro").setSex(M).setPhoneNumber("123456789").
                        setCommunityName("QA").setStartDate(LocalDate.parse("2020-06-25")).build();

        when(employeeService.assignCommunity(anyInt(), any(BaseCommunityDTO.class))).thenReturn(employeeDTO);
        mockMvc.perform(put("/employee/{empID}/setCommunity", 1)
                .contentType("application/json")
                .content(asJsonString(new BaseCommunityDTO("HR"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", is(COMMUNITY_ASSIGNED)));
    }

    @Test
//    @DisplayName(value = "Invalid community name. At least 2 uppercase letters must be provided")
    public void assignCommunityNotValidTest() throws Exception {
        EmployeeDTO.EmployeeDTOBuilder builder = new EmployeeDTO.EmployeeDTOBuilder();

        EmployeeDTO employeeDTO = builder.setUserId(1).setFirstName("Danut").setLastName("Cristea")
                .setEmail("dancristea@sv.ro").setSex(M).setPhoneNumber("123456789").
                        setCommunityName("QA").setStartDate(LocalDate.parse("2020-06-25")).build();

        when(employeeService.assignCommunity(anyInt(), any(BaseCommunityDTO.class))).thenReturn(employeeDTO);
        mockMvc.perform(put("/employee/{empID}/setCommunity", 1)
                .contentType("application/json")
                .content(asJsonString(new BaseCommunityDTO("H"))))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("errorMessage", is(COMMUNITY_NOT_VALID)));
    }

    @Test
    public void removeEmployeeTest() throws Exception {
        doNothing().when(employeeService).removeUser(anyInt());
        mockMvc.perform(delete("/employee/{id}", 1)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", is(EMPLOYEE_REMOVED)));

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error during serialization of DTO");

        }
    }
}
