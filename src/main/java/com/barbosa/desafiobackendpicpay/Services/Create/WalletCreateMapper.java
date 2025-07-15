package com.barbosa.desafiobackendpicpay.Services.Create;

import com.barbosa.desafiobackendpicpay.Entities.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletCreateMapper {

    public WalletEntity mapper(WalletCreateDto dto) {
        return WalletEntity.builder()
                .email(dto.getEmail())
                .cpfOrcnpj(dto.getCpfOrcnpj())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .walletType(dto.getWalletType()).build();
    }
}
