package com.unistream.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseCommissionCalculation {
    public Object message;
    public ArrayList<Fee> fees;
}
