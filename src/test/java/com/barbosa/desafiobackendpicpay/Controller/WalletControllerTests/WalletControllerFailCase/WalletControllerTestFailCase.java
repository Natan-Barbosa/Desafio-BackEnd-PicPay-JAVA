package com.barbosa.desafiobackendpicpay.Controller.WalletControllerTests.WalletControllerFailCase;

import com.barbosa.desafiobackendpicpay.Controller.WalletController;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.Create.WalletCreateService;
import com.barbosa.desafiobackendpicpay.Services.WalletService.GetWalletInfo.GetWalletInfoService;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceDto;
import com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance.IncreaseBalanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WalletController.class)
class WalletControllerTestFailCase {

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
    @DisplayName("Should Throws Invalid Argument Exception")
    void create_Method() throws Exception {
        WalletCreateDto fakeInvalidInput = FakeData.createFakeInput();
        String request = objectMapper.writeValueAsString(fakeInvalidInput);

        mockMvc.perform(post("/wallet/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fullName").exists())
                .andExpect(jsonPath("$.errors.cpfOrcnpj").exists())
                .andExpect(jsonPath("$.errors.email").exists())
                .andExpect(jsonPath("$.errors.password").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("Should Invalid Argument Exception")
    void get_Method() throws Exception {
        String id = "";

        mockMvc.perform(get("/wallet/" + id))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("Should Increase Wallet By Id With Success")
    void increase_method() throws Exception {
        IncreaseBalanceDto fakeInput = FakeData.increaseFakeInput();
        String input = objectMapper.writeValueAsString(fakeInput);

        mockMvc.perform(put("/wallet/increase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.cpfOrcnpj").exists())
                .andExpect(jsonPath("$.errors.password").exists())
                .andExpect(jsonPath("$.errors.value").exists())
                .andDo(print());
    }
}