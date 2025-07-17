package com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.Case2;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoMapper;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetWalletInfoServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    GetWalletInfoMapper getWalletInfoMapper;

    @InjectMocks
    GetWalletInfoService getWalletInfoService;

    @Test
    @DisplayName("Should Get Wallet With Success")
    void get() {
        String id = "FakeID";
        WalletEntity fakeRepository = FakeData.fakeRepository();
        GetWalletInfoDto fakeMapper = FakeData.fakeMapper(fakeRepository);

        Mockito.when(walletRepository.findById(id)).thenReturn(Optional.ofNullable(fakeRepository));
        Mockito.when(getWalletInfoMapper.mapper(fakeRepository)).thenReturn(fakeMapper);

        GetWalletInfoDto serviceResponse = getWalletInfoService.get(id);

        assertNotNull(serviceResponse);
        assertEquals(id, serviceResponse.getId());

        Mockito.verify(walletRepository).findById(id);
        Mockito.verify(getWalletInfoMapper).mapper(fakeRepository);
    }
}