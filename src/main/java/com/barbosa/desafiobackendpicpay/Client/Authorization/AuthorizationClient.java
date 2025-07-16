package com.barbosa.desafiobackendpicpay.Client.Authorization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://util.devi.tools/api/v2/authorize",name = "authorizationClient")
public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationClientDto> authorization();
}
