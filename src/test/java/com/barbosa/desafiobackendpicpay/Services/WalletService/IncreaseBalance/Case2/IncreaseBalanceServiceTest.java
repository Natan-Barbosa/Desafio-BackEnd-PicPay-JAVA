package com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.Case2;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.InvalidCredentialsException;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletNotExistsException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceService;
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
class IncreaseBalanceServiceTest {

    @Mock
    WalletRepository walletRepository;

    @InjectMocks
    IncreaseBalanceService increaseBalanceService;

    @Test
    @DisplayName("Should Throw InvalidCredentialsException")
    void increase() {
        try (MockedStatic<TransactionSynchronizationManager> mockTransaction =
                     Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            IncreaseBalanceDto fakeDto = FakeData.fakeDto();
            WalletEntity fakeRepository = FakeData.fakeRepository();

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);

            Mockito.when(walletRepository.findBycpfOrcnpj(fakeDto.getCpfOrcnpj()))
                    .thenReturn(Optional.ofNullable(fakeRepository));

            assertThrows(InvalidCredentialsException.class, () -> increaseBalanceService.increase(fakeDto));

            Mockito.verify(walletRepository, Mockito.never()).save(ArgumentMatchers.any());

        }

    }
}