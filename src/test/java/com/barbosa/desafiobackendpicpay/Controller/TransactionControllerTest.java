package com.barbosa.desafiobackendpicpay.Controller;

import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionService;
import com.barbosa.desafiobackendpicpay.Services.TransactionService.TransactionServiceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @MockBean
    TransactionService transactionService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Should Return Success When Transfer Is Okay")
    void transfer_Case_1() throws Exception {
        TransactionServiceDto input = TransactionServiceDto.builder()
                .receiverId("FakeReceiverId")
                .senderId("FakeSenderId")
                .value(new BigDecimal(100)).build();

        String request = objectMapper.writeValueAsString(input);

        String output = "Fake Return";

        Mockito.when(transactionService.executeTransaction(Mockito.any(TransactionServiceDto.class)))
                .thenReturn(output);

        mockMvc.perform(post("/transfer")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(output))
                .andDo(print());
    }

    @Test
    @DisplayName("Should Return Exceptions When Fields Is Not Correct")
    void transfer_Case_2() throws Exception {
        TransactionServiceDto input = TransactionServiceDto.builder()
                .receiverId("")
                .senderId("")
                .value(new BigDecimal(0))
                .build();

        String invalidRequest = objectMapper.writeValueAsString(input);

        String output = "Fake Return";

        Mockito.when(transactionService.executeTransaction(Mockito.any(TransactionServiceDto.class)))
                .thenReturn(output);

        mockMvc.perform(post("/transfer")
                        .content(invalidRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.senderId").exists())
                .andExpect(jsonPath("$.errors.receiverId").exists())
                .andExpect(jsonPath("$.errors.value").exists())
                .andDo(print());
    }
}