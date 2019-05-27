package com.learning.spring.rest.employees.exceptionsHandler;

import com.learning.spring.rest.employees.exceptions.DepartmentNotFoundException;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.EmployeeNotValidException;
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

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundError(DepartmentNotFoundException enfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(enfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

}
