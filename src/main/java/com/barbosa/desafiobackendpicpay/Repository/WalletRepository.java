package com.barbosa.desafiobackendpicpay.Repository;

import com.barbosa.desafiobackendpicpay.Entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity,String> {
    Optional<WalletEntity> findBycpfOrcnpj(String cpfOrcnpj);
}
