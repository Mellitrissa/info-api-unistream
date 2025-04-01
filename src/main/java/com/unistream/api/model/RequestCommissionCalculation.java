package com.unistream.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestCommissionCalculation {
    public int senderBankId;
    public Object recipientBankId;
    public String countryCode;
    public String acceptedCurrency;
    public String withdrawCurrency;
    public double amount;
    public Object operationType;
    public Object amountType;
    public Object feeCurrency;
    public Object values;

    public RequestCommissionCalculation(int senderBankId, String countryCode, String acceptedCurrency,
                                        String withdrawCurrency, double amount, Object operationType) {
        this.senderBankId = senderBankId;
        this.countryCode = countryCode;
        this.acceptedCurrency = acceptedCurrency;
        this.withdrawCurrency = withdrawCurrency;
        this.amount = amount;
        this.operationType = operationType;
    }
}
