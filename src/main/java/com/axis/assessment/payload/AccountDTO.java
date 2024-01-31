package com.axis.assessment.payload;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter
public class AccountDTO {
    private Long id;

    @NotBlank(message = "Account username must not be empty or null")
    private String username;

    @NotBlank(message = "Account password must not be empty or null")
    private String password;

    private BigDecimal balance;

    public void setAccountId(Long id) {
        this.id = id;
    }
}