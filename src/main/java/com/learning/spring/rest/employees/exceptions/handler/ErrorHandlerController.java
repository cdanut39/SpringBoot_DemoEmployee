package com.learning.spring.rest.employees.exceptions.handler;

import com.learning.spring.rest.employees.exceptions.ExpiredTokenException;
import com.learning.spring.rest.employees.exceptions.InvalidPasswordException;
import com.learning.spring.rest.employees.exceptions.PasswordMismatchException;
import com.learning.spring.rest.employees.exceptions.custom.NoResultsException;
import com.learning.spring.rest.employees.exceptions.custom.community.*;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.employee.EmployeeNotValidException;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotValidException;
import com.learning.spring.rest.employees.exceptions.custom.project.ProjectNotValidException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundError(UserNotFoundException unfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(unfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

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

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleManagerNotFoundError(ManagerNotFoundException mnfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(mnfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(CommunityNotFoundByIdException.class)
    public ResponseEntity<ErrorResponse> handleCommunityNotFoundByIdError(CommunityNotFoundByIdException dnfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(dnfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommunityNotFoundByNameException.class)
    public ResponseEntity<ErrorResponse> handleCommunityNotFoundByNameError(CommunityNotFoundByNameException dnfe) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(dnfe.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommunityNotValidException.class)
    public ResponseEntity<ErrorResponseValidation> handleCommunityNotValidError(CommunityNotValidException dnve) {
        ErrorResponseValidation errorResp = new ErrorResponseValidation();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(dnve.getMessage());
        errorResp.setErrors(dnve.getFieldErrors());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommunityAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDeparmentAlreadyExistsError(CommunityAlreadyExistsException daee) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(daee.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DefaultCommunityCanNotBeRemovedException.class)
    public ResponseEntity<ErrorResponse> handleDefaultCommunityCanNotBeRemovedError(DefaultCommunityCanNotBeRemovedException daee) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.FORBIDDEN.value());
        errorResp.setErrorMessage(daee.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoResultsException.class)
    public ResponseEntity<ErrorResponse> handleNoResultFoundError(NoResultsException nre) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.OK.value());
        errorResp.setErrorMessage(nre.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.OK);
    }

    @ExceptionHandler(ProjectNotValidException.class)
    public ResponseEntity<ErrorResponseValidation> handleProjectNotValidError(ProjectNotValidException pnve) {
        ErrorResponseValidation errorResp = new ErrorResponseValidation();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(pnve.getMessage());
        errorResp.setErrors(pnve.getFieldErrors());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> handleExpiredPasswordTokenError(ExpiredTokenException epte) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.UNAUTHORIZED.value());
        errorResp.setErrorMessage(epte.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchError(PasswordMismatchException pme) {
        ErrorResponse errorResp = new ErrorResponse();
        errorResp.setReasonCode(HttpStatus.NOT_FOUND.value());
        errorResp.setErrorMessage(pme.getMessage());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordError(InvalidPasswordException ipe) {
        ErrorResponseValidation errorResp = new ErrorResponseValidation();
        errorResp.setReasonCode(HttpStatus.BAD_REQUEST.value());
        errorResp.setErrorMessage(ipe.getMessage());
        errorResp.setErrors(ipe.getFieldErrors());
        errorResp.setTimestamp(now());
        return new ResponseEntity<>(errorResp, HttpStatus.BAD_REQUEST);
    }
}
