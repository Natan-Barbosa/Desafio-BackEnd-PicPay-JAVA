package com.barbosa.desafiobackendpicpay.Services.AuthorizationService;

import com.barbosa.desafiobackendpicpay.Client.Authorization.AuthorizationClient;
import com.barbosa.desafiobackendpicpay.Exceptions.Transaction.AuthorizationException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {

    @Autowired
    private AuthorizationClient authorizationClient;

    public void authorize() {
        try {
            authorizationClient.authorization();
        } catch (FeignException.Forbidden ex) {
            throw new AuthorizationException("Transaction Not Authorized, You Can Try Later");
        } catch (FeignException ex) {
            throw new AuthorizationException("Transaction Not Authorized, You Can Try Later");
        }
    }

}
