package com.barbosa.desafiobackendpicpay.Services.Create;

import com.barbosa.desafiobackendpicpay.Entities.WalletTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletCreateDto {

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String cpfOrcnpj;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private WalletTypeEnum walletType;
}
