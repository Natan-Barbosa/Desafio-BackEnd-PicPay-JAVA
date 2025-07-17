package com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.Case1;

import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletNotExistsException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoMapper;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GetWalletInfoServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    GetWalletInfoMapper getWalletInfoMapper;

    @InjectMocks
    GetWalletInfoService getWalletInfoService;

    @Test
    @DisplayName("Should Throw Exception When Not Found Wallet")
    void get() {
        String id = "1";
        Mockito.when(walletRepository.findById(id)).thenReturn(Optional.ofNullable(null));

        assertThrows(WalletNotExistsException.class, () -> getWalletInfoService.get(id));

        Mockito.verify(getWalletInfoMapper, Mockito.never()).mapper(ArgumentMatchers.any());
    }
}