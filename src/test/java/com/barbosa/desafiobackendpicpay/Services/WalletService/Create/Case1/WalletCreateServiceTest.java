package com.barbosa.desafiobackendpicpay.Services.WalletService.Create.Case1;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletAlreadyExistsException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateMapper;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WalletCreateServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    WalletCreateMapper walletCreateMapper;

    @InjectMocks
    WalletCreateService walletCreateService;

    @Test
    @DisplayName("Should Throw WalletAlreadyExistsException Because User Already Exists")
    void create() {
        try (MockedStatic<TransactionSynchronizationManager>
                     mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            WalletCreateDto fakeDto = FakeData.fakeDto();
            WalletEntity fakeRepository = FakeData.fakeRepository();

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(walletRepository.findBycpfOrcnpj(fakeDto.getCpfOrcnpj()))
                    .thenReturn(Optional.ofNullable(fakeRepository));

            assertThrows(WalletAlreadyExistsException.class, () -> walletCreateService.create(fakeDto));

            Mockito.verify(walletRepository).findBycpfOrcnpj(fakeDto.getCpfOrcnpj());
            Mockito.verify(walletCreateMapper, Mockito.never()).mapper(fakeDto);
            Mockito.verify(walletRepository, Mockito.never()).save(ArgumentMatchers.any());

        }
    }
}