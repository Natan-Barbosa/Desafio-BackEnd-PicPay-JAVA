package com.barbosa.desafiobackendpicpay.Entities.Wallet;

public enum WalletTypeEnum {
    USER(1,"user"),
    SELLER(2,"seller");

    WalletTypeEnum(Integer id, String userType) {
        this.id = id;
        this.userType = userType;
    }

    private Integer id;
    private String userType;
}
