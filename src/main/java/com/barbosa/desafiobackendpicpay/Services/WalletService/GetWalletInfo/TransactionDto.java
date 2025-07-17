package com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {
    private String id;
    private String senderId;
    private String senderName;
    private String receiverId;
    private String receiverName;
    private BigDecimal value;
    private LocalDateTime timeStamp;
}
