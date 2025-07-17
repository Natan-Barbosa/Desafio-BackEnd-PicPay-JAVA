package com.barbosa.desafiobackendpicpay.Services.WalletService.Create.Case3;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;

import java.math.BigDecimal;

public class FakeData {

    public static WalletCreateDto fakeDto() {
        WalletCreateDto fakeDto = WalletCreateDto.builder()
                .cpfOrcnpj("123456789")
                .walletType(WalletTypeEnum.SELLER)
                .email("user@test.com.br")
                .password("12345678")
                .fullName("Fake Full Name")
                .build();
        return fakeDto;
    }

    public static WalletEntity fakeRepository() {
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

    public static WalletEntity fakeMapper(WalletCreateDto dto) {
        return WalletEntity.builder()
                .email(dto.getEmail())
                .balance(BigDecimal.ZERO)
                .cpfOrcnpj(dto.getCpfOrcnpj())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .walletType(dto.getWalletType()).build();
    }

}
