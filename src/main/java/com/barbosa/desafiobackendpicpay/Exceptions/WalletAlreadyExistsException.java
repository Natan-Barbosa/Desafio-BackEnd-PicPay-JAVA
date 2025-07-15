package com.barbosa.desafiobackendpicpay.Exceptions;

public class WalletAlreadyExistsException extends RuntimeException {
    public WalletAlreadyExistsException(String message) {
        super(message);
    }
}
