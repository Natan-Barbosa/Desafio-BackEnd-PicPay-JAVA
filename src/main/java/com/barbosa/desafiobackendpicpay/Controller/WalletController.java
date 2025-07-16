package com.barbosa.desafiobackendpicpay.Controller;

import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletCreateService walletCreateService;


    @PostMapping
    public ResponseEntity<WalletEntity> create(@Valid @RequestBody WalletCreateDto dto) {
        WalletEntity serviceResponse = walletCreateService.create(dto);
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }

}
