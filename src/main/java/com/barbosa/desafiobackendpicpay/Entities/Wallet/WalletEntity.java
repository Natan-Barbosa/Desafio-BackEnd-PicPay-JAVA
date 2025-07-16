package com.barbosa.desafiobackendpicpay.Entities.Wallet;

import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.InvalidCNPJException;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.InvalidCPFException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@Getter
@Setter
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

    public static WalletEntityBuilder builder() {
        return new WalletEntityBuilder();
    }

    public static class WalletEntityBuilder {
        private String id;
        private String fullName;
        private String cpfOrcnpj;
        private String email;
        private BigDecimal balance = BigDecimal.ZERO;
        private String password;
        private WalletTypeEnum walletType;

        public WalletEntityBuilder id(String id) {
            this.id = id;
            return this;
        }

        public WalletEntityBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public WalletEntityBuilder cpfOrcnpj(String cpfOrcnpj) {
            this.cpfOrcnpj = cpfOrcnpj;
            return this;
        }

        public WalletEntityBuilder email(String email) {
            this.email = email;
            return this;
        }

        public WalletEntityBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public WalletEntityBuilder password(String password) {
            this.password = password;
            return this;
        }

        public WalletEntityBuilder walletType(WalletTypeEnum walletType) {
            this.walletType = walletType;
            return this;
        }

        public WalletEntity build() {
            // Validação antes de construir
            validateCpfCnpj();
            return new WalletEntity(id, fullName, cpfOrcnpj, email, balance, password, walletType);
        }

        private void validateCpfCnpj() {
            if (walletType == WalletTypeEnum.USER && cpfOrcnpj.length() != 11) {
                throw new InvalidCPFException("Invalid CPF, please Verify the sended Data");
            }
            if (walletType == WalletTypeEnum.SELLER && cpfOrcnpj.length() != 14) {
                throw new InvalidCNPJException("Invalid CNPJ, please Verify the sended Data");
            }
        }
    }
}
