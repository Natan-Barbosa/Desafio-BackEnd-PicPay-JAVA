package com.barbosa.desafiobackendpicpay.Services.WalletService.IncreaseBalance;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class IncreaseBalanceDto {
    @NotEmpty(message = "Field Cannot Be Empty")
    private String cpfOrcnpj;

    @NotEmpty(message = "Field Cannot Be Empty")
    private String password;

    @Min(value = 1, message = "The Minimum value is 1,Please insert a valid value")
    private BigDecimal value;
}
