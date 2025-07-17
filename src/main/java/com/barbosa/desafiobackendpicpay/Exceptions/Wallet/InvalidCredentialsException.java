package com.barbosa.desafiobackendpicpay.Exceptions.Wallet;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
