package com.axis.assessment.payload;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long id;
    private Long accountId;
    private Double amount;
}
