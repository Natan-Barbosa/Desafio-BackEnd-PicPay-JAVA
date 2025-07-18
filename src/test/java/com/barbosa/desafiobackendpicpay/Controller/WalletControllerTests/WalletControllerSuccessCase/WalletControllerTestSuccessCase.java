package com.barbosa.desafiobackendpicpay.Controller.WalletControllerTests.WalletControllerSuccessCase;

import com.barbosa.desafiobackendpicpay.Controller.WalletController;
import com.barbosa.desafiobackendpicpay.Entities.Wallet.WalletEntity;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateService;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoService;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WalletController.class)
class WalletControllerTestSuccessCase {

    @MockBean
    WalletCreateService walletCreateService;

    @MockBean
    IncreaseBalanceService increaseBalanceService;

    @MockBean
    GetWalletInfoService getWalletInfoService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Should Create Wallet With Success")
    void create_Method() throws Exception {
        WalletCreateDto fakeInput = FakeData.createFakeInput();
        String request = objectMapper.writeValueAsString(fakeInput);

        WalletEntity fakeOutput = FakeData.createFakeOutput(fakeInput);
        String jsonOutput = objectMapper.writeValueAsString(fakeOutput);

        Mockito.when(walletCreateService.create(Mockito.any())).thenReturn(fakeOutput);

        mockMvc.perform(post("/wallet/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonOutput))
                .andDo(print());
    }

    @Test
    @DisplayName("Should Get Wallet By Id With Success")
    void get_Method() throws Exception {
        String id = "999";
        GetWalletInfoDto fakeOutput = FakeData.getFakeOutput();
        String output = objectMapper.writeValueAsString(fakeOutput);

        Mockito.when(getWalletInfoService.get(Mockito.any(String.class))).thenReturn(fakeOutput);

        mockMvc.perform(get("/wallet/"+id))
                .andExpect(status().isOk())
                .andExpect(content().json(output))
                .andDo(print());
    }

    @Test
    @DisplayName("Should Increase Wallet By Id With Success")
    void increase_method() throws Exception {
        IncreaseBalanceDto fakeInput = FakeData.increaseFakeInput();
        String input = objectMapper.writeValueAsString(fakeInput);
        String output = "Fake Output";

        Mockito.when(increaseBalanceService.increase(Mockito.any())).thenReturn(output);

        mockMvc.perform(put("/wallet/increase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isCreated())
                .andExpect(content().string(output))
                .andDo(print());
    }
}