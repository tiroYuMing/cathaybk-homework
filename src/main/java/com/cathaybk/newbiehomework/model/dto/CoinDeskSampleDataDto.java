package com.cathaybk.newbiehomework.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CoinDeskSampleDataDto {

    private List<CurrencyDto> currencies;
    private String updateAt;

    public List<CurrencyDto> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDto> currencies) {
        this.currencies = currencies;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
