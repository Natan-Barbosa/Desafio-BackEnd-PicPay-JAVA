package com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.Case2;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.TransactionDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FakeData {

    public static WalletEntity fakeRepository() {
        WalletEntity build = WalletEntity.builder()
                .id("FakeID")
                .cpfOrcnpj("12345678901")
                .walletType(WalletTypeEnum.USER)
                .email("user@test.com.br")
                .password("12345678")
                .fullName("Fake Full Name")
                .receivedTransactions(new ArrayList<>())
                .sentTransactions(new ArrayList<>())
                .build();
        return build;
    }

    public static GetWalletInfoDto fakeMapper(WalletEntity wallet) {
        GetWalletInfoDto build = GetWalletInfoDto.builder()
                .id(wallet.getId())
                .fullName(wallet.getFullName())
                .cpfOrcnpj(wallet.getCpfOrcnpj())
                .email(wallet.getEmail())
                .balance(wallet.getBalance())
                .walletType(wallet.getWalletType())
                .sentTransactions(wallet.getSentTransactions().stream()
                        .map(t -> TransactionDto.builder()
                                .id(t.getId())
                                .senderId(t.getSender().getId())
                                .senderName(t.getSender().getFullName())
                                .receiverId(t.getReceiver().getId())
                                .receiverName(t.getReceiver().getFullName())
                                .value(t.getValue())
                                .timeStamp(t.getTimeStamp())
                                .build())
                        .collect(Collectors.toList()))
                .receivedTransactions(wallet.getReceivedTransactions().stream()
                        .map(t -> TransactionDto.builder()
                                .id(t.getId())
                                .senderId(t.getSender().getId())
                                .senderName(t.getSender().getFullName())
                                .receiverId(t.getReceiver().getId())
                                .receiverName(t.getReceiver().getFullName())
                                .value(t.getValue())
                                .timeStamp(t.getTimeStamp())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        return build;
    }
}
