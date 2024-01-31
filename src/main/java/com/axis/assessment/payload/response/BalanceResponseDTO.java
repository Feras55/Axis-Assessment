package com.axis.assessment.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BalanceResponseDTO {
    private BigDecimal balance;
}
