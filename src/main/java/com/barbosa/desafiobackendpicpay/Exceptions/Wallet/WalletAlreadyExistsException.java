package com.barbosa.desafiobackendpicpay.Exceptions.Wallet;

public class WalletAlreadyExistsException extends RuntimeException {
    public WalletAlreadyExistsException(String message) {
        super(message);
    }
}
