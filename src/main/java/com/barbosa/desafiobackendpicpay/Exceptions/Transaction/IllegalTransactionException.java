package com.barbosa.desafiobackendpicpay.Exceptions.Transaction;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String message) {
        super(message);
    }
}
