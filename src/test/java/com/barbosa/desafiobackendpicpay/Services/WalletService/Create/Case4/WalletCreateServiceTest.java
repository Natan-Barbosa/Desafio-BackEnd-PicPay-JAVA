package com.barbosa.desafiobackendpicpay.Services.WalletService.Create.Case4;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateMapper;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class WalletCreateServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    WalletCreateMapper walletCreateMapper;

    @InjectMocks
    WalletCreateService walletCreateService;

    @Test
    @DisplayName("Should Create Wallet With Success")
    void create() {
        try (MockedStatic<TransactionSynchronizationManager>
                     mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            WalletCreateDto fakeDto = FakeData.fakeDto();
            WalletEntity fakeMapper = FakeData.fakeMapper(fakeDto);

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(walletRepository.findBycpfOrcnpj(fakeDto.getCpfOrcnpj()))
                    .thenReturn(Optional.ofNullable(null));
            Mockito.when(walletCreateMapper.mapper(fakeDto)).thenReturn(fakeMapper);
            Mockito.when(walletRepository.save(fakeMapper)).thenReturn(fakeMapper);

            WalletEntity serviceResponse = walletCreateService.create(fakeDto);
            assertNotNull(serviceResponse);
            assertEquals(serviceResponse.getId(), fakeMapper.getId());

            Mockito.verify(walletRepository).findBycpfOrcnpj(fakeDto.getCpfOrcnpj());
            Mockito.verify(walletCreateMapper).mapper(fakeDto);
            Mockito.verify(walletRepository).save(fakeMapper);

        }
    }
}