package com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetWalletInfoDto {
    private String id;
    private String fullName;
    private String cpfOrcnpj;
    private String email;
    private BigDecimal balance = BigDecimal.ZERO;
    private WalletTypeEnum walletType;
    private List<TransactionDto> sentTransactions = new ArrayList<>();
    private List<TransactionDto> receivedTransactions = new ArrayList<>();
}
