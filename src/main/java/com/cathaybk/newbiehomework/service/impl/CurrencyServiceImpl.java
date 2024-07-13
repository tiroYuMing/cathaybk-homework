package com.cathaybk.newbiehomework.service.impl;

import com.cathaybk.newbiehomework.dao.CfgCurrencyInfoDao;
import com.cathaybk.newbiehomework.dao.CurExchangeRateDao;
import com.cathaybk.newbiehomework.dao.MapCurrencyDescDao;
import com.cathaybk.newbiehomework.model.dto.CurrencyDataDto;
import com.cathaybk.newbiehomework.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CfgCurrencyInfoDao cfgCurrencyInfoDao;
    @Autowired
    private MapCurrencyDescDao mapCurrencyDesc;
    @Autowired
    private CurExchangeRateDao curExchangeRate;

    @Override
    public List<CurrencyDataDto> getCurrencies() {
        return cfgCurrencyInfoDao.findAllData();
    }

    @Override
    public CurrencyDataDto getCurrencyByName(String currencyName) {
        return null;
    }

    @Override
    public void deleteCurrency(String currencyName) {

    }

    @Override
    public CurrencyDataDto updateCurrency(String currencyName, CurrencyDataDto data) {
        return null;
    }

    @Override
    public CurrencyDataDto addCurrency(CurrencyDataDto data) {
        return null;
    }
}
