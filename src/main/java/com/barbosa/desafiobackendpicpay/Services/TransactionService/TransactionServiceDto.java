package com.barbosa.desafiobackendpicpay.Services.TransactionService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionServiceDto {

    @NotEmpty(message = "Field Cannot Be Empty")
    private String senderId;

    @NotEmpty(message = "Field Cannot Be Empty")
    private String receiverId;

    @Min(value = 1, message = "The Minimum Value Is 1, Send a Valid Value")
    private BigDecimal value;
}
