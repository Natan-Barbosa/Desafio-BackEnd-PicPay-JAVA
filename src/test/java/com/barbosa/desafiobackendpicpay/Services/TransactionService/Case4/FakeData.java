package com.barbosa.desafiobackendpicpay.Services.TransactionService.Case4;

import com.barbosa.desafiobackendpicpay.Entities.Transaction.TransactionEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionServiceDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public static WalletEntity fakeRepositoryData(){
        WalletEntity sender = senderFakeData();
        WalletEntity receiver = receiverFakeData();
        TransactionServiceDto dto = fakeDto();
        TransactionEntity transaction = fakeMapper(sender, receiver, dto);
        sender.setBalance(sender.getBalance().subtract(dto.getValue()));
        sender.getSentTransactions().add(transaction);

        receiver.setBalance(receiver.getBalance().add(dto.getValue()));
        receiver.getSentTransactions().add(transaction);
        return sender;
    }

    public static TransactionServiceDto fakeDto() {
        TransactionServiceDto fakeDto = TransactionServiceDto.builder()
                .senderId("FakeSenderId")
                .receiverId("FakeReceiverId")
                .value(BigDecimal.valueOf(1))
                .build();
        return fakeDto;
    }

    public static TransactionEntity fakeMapper(WalletEntity sender, WalletEntity receiver, TransactionServiceDto dto) {
        TransactionEntity transaction = TransactionEntity.builder()
                .value(dto.getValue())
                .timeStamp(LocalDateTime.now())
                .receiver(receiver)
                .sender(sender)
                .build();
        return transaction;
    }

}
