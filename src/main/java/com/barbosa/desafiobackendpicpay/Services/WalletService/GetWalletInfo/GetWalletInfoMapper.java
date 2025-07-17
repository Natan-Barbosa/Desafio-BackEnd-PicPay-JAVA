package com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GetWalletInfoMapper {
    public GetWalletInfoDto mapper(WalletEntity wallet) {
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
