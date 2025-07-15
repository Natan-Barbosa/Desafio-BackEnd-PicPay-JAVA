package com.barbosa.desafiobackendpicpay.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionModel {
    private String title;
    private Integer statusCode;
    private String details;
    private LocalDateTime timeStamp;
    private Map<String, String> errors;
}
