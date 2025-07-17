package com.barbosa.desafiobackendpicpay.Services.WalletService.Create.Case1;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;

public class FakeData {

    public static WalletCreateDto fakeDto(){
        WalletCreateDto fakeDto = WalletCreateDto.builder()
                .cpfOrcnpj("12345678901")
                .walletType(WalletTypeEnum.USER)
                .email("user@test.com.br")
                .password("12345678")
                .fullName("Fake Full Name")
                .build();
        return fakeDto;
    }

    public static WalletEntity fakeRepository(){
        WalletEntity build = WalletEntity.builder()
                .id("FakeID")
                .cpfOrcnpj("12345678901")
                .walletType(WalletTypeEnum.USER)
                .email("user@test.com.br")
                .password("12345678")
                .fullName("Fake Full Name")
                .build();
        return build;
    }

}
