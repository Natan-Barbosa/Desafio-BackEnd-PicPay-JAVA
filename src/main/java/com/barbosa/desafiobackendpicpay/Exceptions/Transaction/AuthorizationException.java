package com.barbosa.desafiobackendpicpay.Exceptions.Transaction;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
