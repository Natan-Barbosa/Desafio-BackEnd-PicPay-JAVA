package com.barbosa.desafiobackendpicpay.Controller;

import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionService;
import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionServiceDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("transfer")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody @Valid TransactionServiceDto dto) {
        String serviceResponse = transactionService.executeTransaction(dto);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

}
