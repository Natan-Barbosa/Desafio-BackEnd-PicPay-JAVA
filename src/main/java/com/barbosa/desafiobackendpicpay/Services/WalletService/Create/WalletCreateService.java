package com.barbosa.desafiobackendpicpay.Services.WalletService.Create;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletAlreadyExistsException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletCreateService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    WalletCreateMapper walletCreateMapper;

    public WalletEntity create(WalletCreateDto dto) {
        Optional<WalletEntity> walletExists = walletRepository.findBycpfOrcnpj(dto.getCpfOrcnpj());
        if (walletExists.isPresent()) {
            throw new WalletAlreadyExistsException("Wallet Already Exists In Database!");
        }
        WalletEntity mapper = walletCreateMapper.mapper(dto);
        WalletEntity savedWallet = walletRepository.save(mapper);
        return savedWallet;
    }
}
