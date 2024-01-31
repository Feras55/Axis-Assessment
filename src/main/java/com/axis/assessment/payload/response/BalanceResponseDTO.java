package com.axis.assessment.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BalanceResponseDTO {
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    private Double balance;
}
