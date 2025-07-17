package com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.InvalidCredentialsException;
import com.barbosa.desafiobackendpicpay.Exceptions.Wallet.WalletNotExistsException;
import com.barbosa.desafiobackendpicpay.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncreaseBalanceService {

    @Autowired
    WalletRepository walletRepository;

    public String increase(IncreaseBalanceDto dto) {
        WalletEntity wallet = walletRepository.findBycpfOrcnpj(dto.getCpfOrcnpj())
                .orElseThrow(() -> new WalletNotExistsException("The Sended Wallet Not Exists, Verify The Sended Data"));
        if (!dto.getPassword().equals(wallet.getPassword())) {
            throw new InvalidCredentialsException("User Or Password Is Incorrect");
        }

        wallet.setBalance(wallet.getBalance().add(dto.getValue()));
        WalletEntity savedWallet = walletRepository.save(wallet);
        return "Deposited With Successful, You Current Balance is: "+savedWallet.getBalance();
    }

}
