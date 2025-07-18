package com.barbosa.desafiobackendpicpay.Controller.WalletControllerTests.WalletControllerFailCase;

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
                .fullName("")
                .password("")
                .email("")
                .cpfOrcnpj("")
                .walletType(WalletTypeEnum.USER)
                .build();
        return input;
    }


    public static IncreaseBalanceDto increaseFakeInput() {
        return IncreaseBalanceDto.builder()
                .cpfOrcnpj("")
                .password("")
                .value(new BigDecimal(0))
                .build();
    }

}
