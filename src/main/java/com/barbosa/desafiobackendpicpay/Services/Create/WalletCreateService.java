package com.barbosa.desafiobackendpicpay.Services.Create;

import com.barbosa.desafiobackendpicpay.Entities.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.WalletAlreadyExistsException;
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
