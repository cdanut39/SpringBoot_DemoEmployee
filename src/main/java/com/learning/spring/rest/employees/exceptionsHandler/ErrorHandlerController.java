package com.learning.spring.rest.employees.exceptionsHandler;

import com.learning.spring.rest.employees.exceptions.department.*;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotValidException;
import com.learning.spring.rest.employees.exceptions.manager.ManagerNotValidException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.user.UserNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundError(EmployeeNotFoundException enfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(enfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotValidException.class)
    public ResponseEntity<ErrorResponseValidation> handleEmployeeNotValidError(EmployeeNotValidException enve) {
        ErrorResponseValidation errorResp = new ErrorResponseValidation();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(enve.getMessage());
        errorResp.setErrors(enve.getFieldErrors());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManagerNotValidException.class)
    public ResponseEntity<ErrorResponseValidation> handleManagerNotValidError(ManagerNotValidException mnve) {
        ErrorResponseValidation errorResp = new ErrorResponseValidation();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(mnve.getMessage());
        errorResp.setErrors(mnve.getFieldErrors());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotValidException.class)
    public ResponseEntity<ErrorResponseValidation> handleUserNotValidError(UserNotValidException unve) {
        ErrorResponseValidation errorResp = new ErrorResponseValidation();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(unve.getMessage());
        errorResp.setErrors(unve.getFieldErrors());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsError(UserAlreadyExistsException uaee) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(uaee.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundByIdException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundByIdError(DepartmentNotFoundByIdException dnfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(dnfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentNotFoundByNameException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundByNameError(DepartmentNotFoundByNameException dnfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(dnfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepartmentNotValidException.class)
    public ResponseEntity<ErrorResponseValidation> handleDepartmentNotValidError(DepartmentNotValidException dnve) {
        ErrorResponseValidation errorResp = new ErrorResponseValidation();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(dnve.getMessage());
        errorResp.setErrors(dnve.getFieldErrors());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDeparmentAlreadyExistsError(DepartmentAlreadyExistsException daee) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(daee.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DefaultDepartmentCanNotBeRemovedException.class)
    public ResponseEntity<ErrorResponse> handleDefaultDepartmentCanNotBeRemovedError(DefaultDepartmentCanNotBeRemovedException daee) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.FORBIDDEN.value());
        errorResp.setErrorMessage(daee.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.FORBIDDEN);
    }

}
