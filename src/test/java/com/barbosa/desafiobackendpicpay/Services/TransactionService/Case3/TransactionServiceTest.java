package com.barbosa.desafiobackendpicpay.Services.TransactionService.Case3;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.Transaction.IllegalTransactionException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.AuthorizationService.AuthorizationService;
import com.barbosa.desafiobackendpicpay.Services.NotificationService.NotificationService;
import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionMapper;
import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionService;
import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionServiceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    AuthorizationService authorizationService;

    @Mock
    NotificationService notificationService;

    @Mock
    TransactionMapper transactionMapper;

    @InjectMocks
    TransactionService transactionService;

    @Test
    @DisplayName("Should Throws IllegalTransactionException Because The Sent Value Is More Than Balance")
    void executeTransaction() {
        TransactionServiceDto fakeDto = FakeData.fakeDto();
        WalletEntity senderFakeData = FakeData.senderFakeData();
        WalletEntity receiverFakeData = FakeData.receiverFakeData();

        try (MockedStatic<TransactionSynchronizationManager> mockTransaction =
                     Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);

            Mockito.when(walletRepository.findById(fakeDto.getSenderId()))
                    .thenReturn(Optional.ofNullable(senderFakeData));
            Mockito.when(walletRepository.findById(fakeDto.getReceiverId()))
                    .thenReturn(Optional.ofNullable(receiverFakeData));

            assertThrows(IllegalTransactionException.class, () -> transactionService.executeTransaction(fakeDto));

            Mockito.verify(walletRepository, Mockito.times(2)).findById(ArgumentMatchers.any());
            Mockito.verify(authorizationService,Mockito.never()).authorize();
            Mockito.verify(transactionMapper,Mockito.never()).mapper(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
            Mockito.verify(walletRepository, Mockito.never()).save(ArgumentMatchers.any());
            mockTransaction.verify(() ->
                    TransactionSynchronizationManager.registerSynchronization(ArgumentMatchers.any()), Mockito.never());

        }


    }
}