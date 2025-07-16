package com.barbosa.desafiobackendpicpay.Exceptions;

import com.barbosa.desafiobackendpicpay.Utils.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WalletExceptions {

    @ExceptionHandler(WalletAlreadyExistsException.class)
    public ResponseEntity<ExceptionModel> WalletAlreadyExistsExceptionHandler(WalletAlreadyExistsException ex) {
        ExceptionModel badRequestMessage = ExceptionModel.builder()
                .title("BAD REQUEST")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .details(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity(badRequestMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidCPFException.class)
    public ResponseEntity<ExceptionModel> InvalidCPFExceptionHandler(InvalidCPFException ex) {
        ExceptionModel badRequestMessage = ExceptionModel.builder()
                .title("BAD REQUEST")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .details(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity(badRequestMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCNPJException.class)
    public ResponseEntity<ExceptionModel> InvalidCNPJExceptionHandler(InvalidCNPJException ex) {
        ExceptionModel badRequestMessage = ExceptionModel.builder()
                .title("BAD REQUEST")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .details(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity(badRequestMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionModel> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors()
                .forEach(error -> {
                    String errorTitle = error.getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(errorTitle, errorMessage);
                });
        ExceptionModel badRequestMessage = ExceptionModel.builder()
                .title("BAD REQUEST")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity(badRequestMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionModel> GenericExceptionHandler(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        ExceptionModel badRequestMessage = ExceptionModel.builder()
                .title("INTERNAL SERVER ERROR")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(ex.getMessage())
                .timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity(badRequestMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
