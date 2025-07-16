package com.barbosa.desafiobackendpicpay.Exceptions.Wallet;

public class InvalidCNPJException extends RuntimeException {
    public InvalidCNPJException(String message) {
        super(message);
    }
}
