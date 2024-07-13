package com.cathaybk.newbiehomework.model.dto;

import com.cathaybk.newbiehomework.model.entity.CfgCurrencyInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrencyDto {

    private String currencyCode;
    private String currencyName;
    private BigDecimal exchangeRate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
