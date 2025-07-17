package com.barbosa.desafiobackendpicpay.Services.WalletService.Create.Case2;

import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.InvalidCPFException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateMapper;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class WalletCreateServiceTest {

    @Mock
    WalletRepository walletRepository;

    WalletCreateMapper walletCreateMapper = new WalletCreateMapper();

    @InjectMocks
    WalletCreateService walletCreateService;

    @Test
    @DisplayName("Should Throw Invalid CPF Exception When Cpf Has Invalid Length")
    void create() {
        WalletCreateDto fakeDto = FakeData.fakeDto();

        assertThrows(InvalidCPFException.class, () -> {
            walletCreateMapper.mapper(fakeDto);
        });

        Mockito.verify(walletRepository, never()).save(any());
    }
}