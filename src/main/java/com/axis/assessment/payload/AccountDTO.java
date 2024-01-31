package com.axis.assessment.payload;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDTO {
    private Long id;

    @NotBlank(message = "Account username must not be empty or null")
    private String username;

    @NotBlank(message = "Account password must not be empty or null")
    private String password;

    private Double balance;
}