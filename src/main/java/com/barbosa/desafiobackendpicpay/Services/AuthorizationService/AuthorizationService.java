package com.barbosa.desafiobackendpicpay.Services.AuthorizationService;

import com.barbosa.desafiobackendpicpay.Client.Authorization.AuthorizationClient;
import com.barbosa.desafiobackendpicpay.Client.Authorization.AuthorizationClientDto;
import com.barbosa.desafiobackendpicpay.Exceptions.Transaction.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {

    @Autowired
    private AuthorizationClient authorizationClient;

    public void authorize() {
        ResponseEntity<AuthorizationClientDto> authorization = authorizationClient.authorization();
        if (authorization.getStatusCode().is4xxClientError()) {
            throw new AuthorizationException("Transaction Not Authorized");
        }
    }

}
