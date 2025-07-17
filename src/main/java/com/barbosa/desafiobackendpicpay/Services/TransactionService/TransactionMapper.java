package com.barbosa.desafiobackendpicpay.Services.TransactionService;

import com.barbosa.desafiobackendpicpay.Entities.Transaction.TransactionEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public TransactionEntity mapper(WalletEntity sender, WalletEntity receiver, TransactionServiceDto dto) {
        TransactionEntity transaction = TransactionEntity.builder()
                .value(dto.getValue())
                .timeStamp(LocalDateTime.now())
                .receiver(receiver)
                .sender(sender)
                .build();
        return transaction;
    }
}
