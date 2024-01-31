package com.axis.assessment.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDTO {
    private Long id;

    @NotEmpty
    @Size(min = 5, message = "Account Username should have at least 5 characters")
    private String username;

    @NotEmpty
    @Size(min = 5, message = "Account Password should be at least 10 characters")
    private String password;

    private Double balance;
}