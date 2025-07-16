package com.barbosa.desafiobackendpicpay.Repository;

import com.barbosa.desafiobackendpicpay.Entities.Transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
}
