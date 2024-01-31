package com.axis.assessment.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO {
    private Long id;
    private Long accountId;

    @NotEmpty(message = "Amount can't be Null or Empty")
    private BigDecimal amount;
}
