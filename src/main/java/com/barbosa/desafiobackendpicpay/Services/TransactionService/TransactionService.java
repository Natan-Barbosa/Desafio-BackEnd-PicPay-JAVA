package com.barbosa.desafiobackendpicpay.Services.TransactionService;

import com.barbosa.desafiobackendpicpay.Entities.Transaction.TransactionEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.Transaction.IllegalTransactionException;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletNotExistsException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.AuthorizationService.AuthorizationService;
import com.barbosa.desafiobackendpicpay.Services.NotificationService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class TransactionService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    TransactionMapper transactionMapper;

    public String executeTransaction(TransactionServiceDto dto) {
        WalletEntity sender = walletRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new WalletNotExistsException("The Sender Wallet Does Not Exists !"));
        WalletEntity receiver = walletRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new WalletNotExistsException("The Receiver Wallet Does Not Exists !"));


        if (sender.isSeller()) {
            throw new IllegalTransactionException("Is Not Permit The Seller Send Value To Anybody");
        }

        if (!sender.walletHasSufficientBalanceToTransaction(dto.getValue())) {
            throw new IllegalTransactionException("You Don't Have Sufficient Money To Transaction, " +
                    "You Current Balance Is: " + sender.getBalance());
        }

        TransactionEntity transaction = transactionMapper.mapper(sender, receiver, dto);

        sender.decreaseBalance(dto.getValue());
        sender.getSentTransactions().add(transaction);

        receiver.increaseBalance(dto.getValue());
        receiver.getSentTransactions().add(transaction);

        WalletEntity savedSender = walletRepository.save(sender);
        walletRepository.save(receiver);

        authorizationService.authorize();

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                CompletableFuture.runAsync(() -> notificationService.sendNotification());
            }
        });
        return "Send With Successful. You current Balance Is: " + savedSender.getBalance();
    }
}
