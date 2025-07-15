package com.barbosa.desafiobackendpicpay.Exceptions;

public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String message) {
        super(message);
    }
}
