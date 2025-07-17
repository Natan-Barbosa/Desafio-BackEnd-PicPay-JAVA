package com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.Case3;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class IncreaseBalanceServiceTest {

    @Mock
    WalletRepository walletRepository;

    @InjectMocks
    IncreaseBalanceService increaseBalanceService;

    @Test
    @DisplayName("Should Increase With Success")
    void increase() {
        try (MockedStatic<TransactionSynchronizationManager> mockTransaction =
                     Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            IncreaseBalanceDto fakeDto = FakeData.fakeDto();
            WalletEntity fakeRepository = FakeData.fakeRepository();

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);

            Mockito.when(walletRepository.findBycpfOrcnpj(fakeDto.getCpfOrcnpj()))
                    .thenReturn(Optional.ofNullable(fakeRepository));

            Mockito.when(walletRepository.save(fakeRepository)).thenReturn(fakeRepository);

            String serviceResponse = increaseBalanceService.increase(fakeDto);
            assertNotNull(serviceResponse);

            Mockito.verify(walletRepository).save(fakeRepository);

        }

    }
}