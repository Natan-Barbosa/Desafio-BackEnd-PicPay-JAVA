package com.barbosa.desafiobackendpicpay.Exceptions.Wallet;

public class WalletNotExistsException extends RuntimeException {
    public WalletNotExistsException(String message) {
        super(message);
    }
}
