package com.cathaybk.newbiehomework.service;

import com.cathaybk.newbiehomework.model.dto.CurrencyDataDto;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDataDto> getCurrencies();

    CurrencyDataDto getCurrencyByName(String currencyName);

    void deleteCurrency(String currencyName);

    CurrencyDataDto updateCurrency(String currencyName, CurrencyDataDto data);

    CurrencyDataDto addCurrency(CurrencyDataDto data);
}
