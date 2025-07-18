package com.barbosa.desafiobackendpicpay.Controller.WalletControllerTests.WalletControllerSuccessCase;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class FakeData {

    public static WalletCreateDto createFakeInput() {
        WalletCreateDto input = WalletCreateDto.builder()
                .fullName("Fake Full Name")
                .password("Fake Password123")
                .email("fakeemail@test.com")
                .cpfOrcnpj("12345678901")
                .walletType(WalletTypeEnum.USER)
                .build();
        return input;
    }

    public static WalletEntity createFakeOutput(WalletCreateDto input) {
        WalletEntity output = WalletEntity.builder()
                .id(UUID.randomUUID().toString())
                .walletType(input.getWalletType())
                .cpfOrcnpj(input.getCpfOrcnpj())
                .email(input.getEmail())
                .password(input.getPassword())
                .sentTransactions(new ArrayList<>())
                .receivedTransactions(new ArrayList<>())
                .balance(new BigDecimal(100))
                .fullName(input.getFullName())
                .build();
        return output;
    }

    public static GetWalletInfoDto getFakeOutput() {
        return GetWalletInfoDto.builder()
                .id(UUID.randomUUID().toString())
                .walletType(WalletTypeEnum.USER)
                .cpfOrcnpj("12345678901")
                .email("testemail@test.com.br")
                .sentTransactions(new ArrayList<>())
                .receivedTransactions(new ArrayList<>())
                .balance(new BigDecimal(100))
                .fullName("Fake Full Name")
                .build();
    }

    public static IncreaseBalanceDto increaseFakeInput() {
        return IncreaseBalanceDto.builder()
                .cpfOrcnpj("12345678901")
                .password("12345678")
                .value(new BigDecimal(100))
                .build();
    }

}
