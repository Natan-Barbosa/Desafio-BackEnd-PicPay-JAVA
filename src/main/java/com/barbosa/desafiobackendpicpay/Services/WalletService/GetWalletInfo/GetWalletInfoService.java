package com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletNotExistsException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetWalletInfoService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    GetWalletInfoMapper getWalletInfoMapper;

    public GetWalletInfoDto get(String cpfOrcnpj) {
        WalletEntity wallet = walletRepository.findById(cpfOrcnpj)
                .orElseThrow(() -> new WalletNotExistsException("The Informed Wallet Don't Exists, Verify The Sent Data"));
        GetWalletInfoDto mapper = getWalletInfoMapper.mapper(wallet);
        return mapper;
    }
}
