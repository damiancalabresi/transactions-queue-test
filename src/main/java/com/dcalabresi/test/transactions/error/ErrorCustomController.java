package com.dcalabresi.test.transactions.error;

import com.dcalabresi.test.transactions.exception.ManagedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Created by damian on 9/18/16.
 */
@RestController
@ControllerAdvice
public class ErrorCustomController {

    @ExceptionHandler(ManagedException.class)
    public ResponseEntity<ErrorDto> handleManagedException(ManagedException ex) {
        return new ResponseEntity<ErrorDto>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationApiException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors()
                .stream().map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining(". "));
        return new ResponseEntity<ErrorDto>(new ErrorDto(message), HttpStatus.BAD_REQUEST);
    }

}
