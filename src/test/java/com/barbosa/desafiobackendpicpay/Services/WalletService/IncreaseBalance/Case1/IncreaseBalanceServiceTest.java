package com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.Case1;

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
    @DisplayName("Should Throw WalletNotExistsException")
    void increase() {
        try (MockedStatic<TransactionSynchronizationManager> mockTransaction =
                     Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            IncreaseBalanceDto dto = IncreaseBalanceDto.builder()
                    .cpfOrcnpj("12345678901")
                    .value(new BigDecimal(100))
                    .password("12345678")
                    .build();

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);

            Mockito.when(walletRepository.findBycpfOrcnpj(dto.getCpfOrcnpj()))
                    .thenReturn(Optional.ofNullable(null));

            assertThrows(WalletNotExistsException.class, () -> increaseBalanceService.increase(dto));

            Mockito.verify(walletRepository, Mockito.never()).save(ArgumentMatchers.any());

        }

    }
}