package com.barbosa.desafiobackendpicpay.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "cpf_or_cnpj", nullable = false, unique = true)
    private String cpfOrcnpj;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "wallet_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletTypeEnum walletType;
}
