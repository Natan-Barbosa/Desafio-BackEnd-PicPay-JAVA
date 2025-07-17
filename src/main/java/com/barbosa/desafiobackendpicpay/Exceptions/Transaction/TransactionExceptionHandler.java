package com.barbosa.desafiobackendpicpay.Exceptions.Transaction;

import com.barbosa.desafiobackendpicpay.Utils.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ExceptionModel> AuthorizationExceptionHandler(AuthorizationException ex) {
        ExceptionModel badRequest = ExceptionModel.builder()
                .title("BAD REQUEST")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .details(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalTransactionException.class)
    public ResponseEntity<ExceptionModel> IllegalTransactionExceptionHandler(IllegalTransactionException ex) {
        ExceptionModel badRequest = ExceptionModel.builder()
                .title("BAD REQUEST")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .details(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

}
