package com.barbosa.desafiobackendpicpay.Entities.Transaction;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn(name = "receiver_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private WalletEntity receiver;

    @JoinColumn(name = "sender_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private WalletEntity sender;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timeStamp;

}
