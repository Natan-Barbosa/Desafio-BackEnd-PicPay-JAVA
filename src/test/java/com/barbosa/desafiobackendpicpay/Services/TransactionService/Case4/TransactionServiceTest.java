package com.barbosa.desafiobackendpicpay.Services.TransactionService.Case4;

import com.barbosa.desafiobackendpicpay.Entities.Transaction.TransactionEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @DisplayName("Should Make Transaction With Success")
    void executeTransaction() {
        TransactionServiceDto fakeDto = FakeData.fakeDto();
        WalletEntity senderFakeData = FakeData.senderFakeData();
        WalletEntity receiverFakeData = FakeData.receiverFakeData();
        TransactionEntity fakeMapper = FakeData.fakeMapper(senderFakeData, receiverFakeData, fakeDto);
        WalletEntity fakeRepositoryData = FakeData.fakeRepositoryData();

        try (MockedStatic<TransactionSynchronizationManager> mockTransaction =
                     Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);

            Mockito.when(walletRepository.findById(fakeDto.getSenderId()))
                    .thenReturn(Optional.ofNullable(senderFakeData));

            Mockito.when(walletRepository.findById(fakeDto.getReceiverId()))
                    .thenReturn(Optional.ofNullable(receiverFakeData));

            Mockito.doNothing().when(authorizationService).authorize();

            Mockito.when(transactionMapper.mapper(senderFakeData, receiverFakeData, fakeDto)).thenReturn(fakeMapper);

            Mockito.when(walletRepository.save(senderFakeData)).thenReturn(fakeRepositoryData);

            String serviceResponse = transactionService.executeTransaction(fakeDto);

            assertNotNull(serviceResponse);
            assertEquals("Send With Successful. You current Balance Is: " + senderFakeData.getBalance(), serviceResponse);

            Mockito.verify(walletRepository, Mockito.times(2)).findById(ArgumentMatchers.any());
            Mockito.verify(authorizationService).authorize();
            Mockito.verify(transactionMapper).mapper(senderFakeData, receiverFakeData, fakeDto);
            Mockito.verify(walletRepository,Mockito.times(2)).save(ArgumentMatchers.any());
            mockTransaction.verify(() -> TransactionSynchronizationManager.registerSynchronization(ArgumentMatchers.any()));

        }


    }
}