package com.barbosa.desafiobackendpicpay.Services.WalletService.Create;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletCreateMapper {

    public WalletEntity mapper(WalletCreateDto dto) {
        return WalletEntity.builder()
                .email(dto.getEmail())
                .balance(BigDecimal.ZERO)
                .cpfOrcnpj(dto.getCpfOrcnpj())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .walletType(dto.getWalletType()).build();
    }
}
