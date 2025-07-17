package com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.Case2;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceDto;

import java.math.BigDecimal;

public class FakeData {

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

    public static IncreaseBalanceDto fakeDto(){
        IncreaseBalanceDto dto = IncreaseBalanceDto.builder()
                .cpfOrcnpj("12345678901")
                .value(new BigDecimal(100))
                .password("1234567")
                .build();
        return dto;
    }

}
