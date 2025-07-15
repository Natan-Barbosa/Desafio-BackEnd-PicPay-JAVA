package com.barbosa.desafiobackendpicpay.Exceptions;

public class InvalidCNPJException extends RuntimeException {
    public InvalidCNPJException(String message) {
        super(message);
    }
}
