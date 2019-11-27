package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.PasswordDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.ExpiredTokenException;
import com.learning.spring.rest.employees.exceptions.InvalidPasswordException;
import com.learning.spring.rest.employees.exceptions.PasswordMismatchException;
import com.learning.spring.rest.employees.exceptions.custom.NoResultsException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.custom.community.CommunityNotValidException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotValidException;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserNotFoundException;
import com.learning.spring.rest.employees.exceptions.handler.ValidationError;
import com.learning.spring.rest.employees.services.EmployeeServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

import static com.learning.spring.rest.employees.utils.BindingResultErrors.getErrors;
import static com.learning.spring.rest.employees.utils.Constants.*;

@RestController
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * POST
     */
    @ApiOperation(
            value = "Add a new employee",
            notes = "Can be called only by users with ADMIN or MANAGER roles."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = EMPLOYEE_ADDED),
            @ApiResponse(code = 400, message = EMPLOYEE_NOT_VALID),
            @ApiResponse(code = 404, message = EMPLOYEE_404)
    })
    @CrossOrigin
    @PostMapping("/register/employee")
    public ResponseEntity<Response> addEmployee(@Valid @RequestBody EmployeeDTO employee, BindingResult result) throws EmployeeNotValidException, UserAlreadyExistsException, CommunityNotFoundByNameException {
        Response response = new Response();
        if (result.hasErrors()) {
            List<ValidationError> errors = getErrors(result);
            logger.error("Invalid data for adding new employee");
            throw new EmployeeNotValidException(EMPLOYEE_NOT_VALID, errors);
        }
        employeeService.registerEmployee(employee);
        response.setMessage(EMPLOYEE_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * PATCH
     */
    @PatchMapping(value = "/password")
    public ResponseEntity<Response> setPassword(@RequestParam(value = "token") String token, @Valid @RequestBody PasswordDTO passwordDTO, BindingResult result) throws ExpiredTokenException, PasswordMismatchException, EmployeeNotFoundException, InvalidPasswordException {
        Response response = new Response();
        if (result.hasErrors()) {
            List<ValidationError> errors = getErrors(result);
            logger.error(INVALID_PASSWORD);
            throw new InvalidPasswordException(INVALID_PASSWORD, errors);
        }
        employeeService.setEmployeePassword(token, passwordDTO);
        response.setMessage(PASSWORD_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<UserDTO> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        EmployeeDTO getEmployee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<EmployeeDTO>> searchEmployees(@RequestParam(value = "lastname") String lastname, @RequestParam(value = "community", required = false) String community) throws NoResultsException {
        List<EmployeeDTO> employeeDTOList = employeeService.searchEmployeeBy(lastname, community);
        if (employeeDTOList.isEmpty()) {
            throw new NoResultsException(NO_RESULTS);
        }
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/getEmployees")
    public ResponseEntity<HashMap<String, Object>> getEmployeesWithPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                              @RequestParam(value = "size", defaultValue = "5") int size,
                                                                              @RequestParam(value = "sortBy", defaultValue = "first_name") String criteria) {
        return new ResponseEntity<>(employeeService.getEmployeesWithPagination(page, size, criteria), HttpStatus.OK);
    }

    @GetMapping(value = "/employees/orderBy/{criteria}/{direction}")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@PathVariable("criteria") String criteria, @PathVariable("direction") String direction) {
        return new ResponseEntity<>(employeeService.getEmployeesSortedByCriteria(criteria, direction), HttpStatus.OK);
    }


    /**
     * PUT
     */
    @PutMapping("/employee/update/{id}")
    public ResponseEntity<Response> updateEmployee(@PathVariable("id") int id, @Valid @RequestBody EmployeeDTO employee, BindingResult result) throws EmployeeNotValidException, EmployeeNotFoundException {
        Response response = new Response();
        if (result.hasErrors()) {
            List<ValidationError> errors = getErrors(result);
            logger.error("Invalid data for adding new employee");
            throw new EmployeeNotValidException("Employee data not valid", errors);
        }
        employeeService.updateEmployee(id, employee);
        response.setMessage(EMPLOYEE_MODIFIED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/employee/{empID}/setCommunity")
    public ResponseEntity<Response> assignCommunity(@PathVariable("empID") int empId, @Valid @RequestBody BaseCommunityDTO community, BindingResult result) throws EmployeeNotFoundException, CommunityNotFoundByNameException, CommunityNotValidException {
        Response response = new Response();
        if (result.hasErrors()) {
            List<ValidationError> errors = getErrors(result);
            logger.error("Invalid data for adding new community");
            throw new CommunityNotValidException(COMMUNITY_NOT_VALID, errors);
        }
        employeeService.assignCommunity(empId, community);
        response.setMessage(COMMUNITY_ASSIGNED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * DELETE
     */
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        Response response = new Response();
        employeeService.removeEmployee(id);
        response.setMessage(EMPLOYEE_REMOVED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
