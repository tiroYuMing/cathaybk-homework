package com.cathaybk.newbiehomework.model.dto;

import java.util.Map;

public class BpiDto {

    private Map<String, BpiCurrencyDto> currencies;

    public Map<String, BpiCurrencyDto> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, BpiCurrencyDto> currencies) {
        this.currencies = currencies;
    }
}
