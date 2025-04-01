package com.unistream.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fee {
    public Object name;
    public double acceptedAmount;
    public double acceptedTransferAmount;
    public String acceptedCurrency;
    public double withdrawAmount;
    public String withdrawCurrency;
    public double rate;
    public double acceptedTotalFee;
    public String acceptedTotalFeeCurrency;
}
