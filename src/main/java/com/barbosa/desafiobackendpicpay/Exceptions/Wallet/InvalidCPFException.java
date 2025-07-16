package com.barbosa.desafiobackendpicpay.Exceptions.Wallet;

public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String message) {
        super(message);
    }
}
