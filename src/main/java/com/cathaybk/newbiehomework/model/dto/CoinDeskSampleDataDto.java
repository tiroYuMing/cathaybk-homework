package com.cathaybk.newbiehomework.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CoinDeskSampleDataDto {

    private List<CurrencyDto> currencies;
    private LocalDateTime updateAt;

    public List<CurrencyDto> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDto> currencies) {
        this.currencies = currencies;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
