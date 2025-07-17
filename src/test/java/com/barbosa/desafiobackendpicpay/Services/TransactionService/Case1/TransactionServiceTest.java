package com.barbosa.desafiobackendpicpay.Services.TransactionService.Case1;

import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletNotExistsException;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    AuthorizationService authorizationService;

    @Mock
    TransactionMapper transactionMapper;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    TransactionService transactionService;

    @Test
    @DisplayName("Should Throws WalletNotExistsException Because Repository Is Null")
    void executeTransaction() {
        TransactionServiceDto fakeDto = TransactionServiceDto.builder()
                .senderId("FakeSenderId")
                .receiverId("FakeReceiverId")
                .value(BigDecimal.valueOf(100))
                .build();

        try (MockedStatic<TransactionSynchronizationManager> mockTransaction =
                     Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);

            Mockito.when(walletRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(null));

            assertThrows(WalletNotExistsException.class, () -> transactionService.executeTransaction(fakeDto));

            Mockito.verify(walletRepository).findById(ArgumentMatchers.any());
            Mockito.verify(authorizationService, Mockito.never()).authorize();
            Mockito.verify(transactionMapper, Mockito.never())
                    .mapper(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
            Mockito.verify(walletRepository, Mockito.never()).save(ArgumentMatchers.any());
            mockTransaction.verify(() ->
                    TransactionSynchronizationManager.registerSynchronization(ArgumentMatchers.any()), Mockito.never());

        }


    }
}