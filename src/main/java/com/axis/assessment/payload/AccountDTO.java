package com.axis.assessment.payload;


import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private Double balance;
}