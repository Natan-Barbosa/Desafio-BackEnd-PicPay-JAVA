package com.barbosa.desafiobackendpicpay.Services.TransactionService.Case3;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionServiceDto;

import java.math.BigDecimal;

public class FakeData {

    public static WalletEntity receiverFakeData() {
        WalletEntity build = WalletEntity.builder()
                .id("FakeReceiverId")
                .balance(new BigDecimal(100))
                .cpfOrcnpj("01234567890987")
                .email("fakereceiver@test.com")
                .fullName("Fake Receiver")
                .password("12345678")
                .walletType(WalletTypeEnum.SELLER)
                .build();
        return build;
    }

    public static WalletEntity senderFakeData() {
        WalletEntity build = WalletEntity.builder()
                .id("FakeSenderId")
                .balance(new BigDecimal(100))
                .cpfOrcnpj("01234567890")
                .email("fakesender@test.com")
                .fullName("Fake Sender")
                .password("12345678")
                .walletType(WalletTypeEnum.USER)
                .build();
        return build;
    }

    public static TransactionServiceDto fakeDto() {
        TransactionServiceDto fakeDto = TransactionServiceDto.builder()
                .senderId("FakeSenderId")
                .receiverId("FakeReceiverId")
                .value(BigDecimal.valueOf(1000))
                .build();
        return fakeDto;
    }

}
