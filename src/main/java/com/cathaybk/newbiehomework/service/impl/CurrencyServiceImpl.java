package com.cathaybk.newbiehomework.service.impl;

import com.cathaybk.newbiehomework.dao.CfgCurrencyInfoDao;
import com.cathaybk.newbiehomework.dao.CurExchangeRateDao;
import com.cathaybk.newbiehomework.dao.MapCurrencyDescDao;
import com.cathaybk.newbiehomework.middleware.CoinDeskMware;
import com.cathaybk.newbiehomework.model.dto.*;
import com.cathaybk.newbiehomework.model.entity.CfgCurrencyInfo;
import com.cathaybk.newbiehomework.model.entity.CurExchangeRate;
import com.cathaybk.newbiehomework.model.entity.MapCurrencyDesc;
import com.cathaybk.newbiehomework.service.CurrencyService;
import com.cathaybk.newbiehomework.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CfgCurrencyInfoDao cfgCurrencyInfoDao;
    @Autowired
    private MapCurrencyDescDao mapCurrencyDesc;
    @Autowired
    private CurExchangeRateDao curExchangeRate;
    @Autowired
    private MapCurrencyDescDao mapCurrencyDescDao;
    @Autowired
    private CoinDeskMware coinDeskMware;

    @Override
    public List<CurrencyDataDto> getCurrencies() {
        return cfgCurrencyInfoDao.findAllData();
    }

    @Override
    public CurrencyDataDto getCurrencyByCode(String currencyCode) {
        return cfgCurrencyInfoDao.findDataByCode(CommonUtil.convertFullWidthToHalfWidth(currencyCode).toUpperCase());
    }

    @Override
    public void deleteCurrency(String currencyCode) {
        if (StringUtils.isBlank(currencyCode)) {
            throw new IllegalArgumentException("currencyName 為必填參數");
        }
        CfgCurrencyInfo currencyInfo = cfgCurrencyInfoDao.findOneByCurrencyCode(
                CommonUtil.convertFullWidthToHalfWidth(currencyCode).toUpperCase());
        if (currencyInfo == null) {
            throw new IllegalArgumentException("查無貨幣資訊");
        }
        MapCurrencyDesc currencyDesc = mapCurrencyDescDao.findById(currencyInfo.getNo()).orElse(null);
        if (currencyDesc == null) {
            throw new IllegalArgumentException("查無貨幣注釋資訊");
        }
        CurExchangeRate rate = curExchangeRate.findById(currencyInfo.getNo()).orElse(null);
        if (rate == null) {
            throw new IllegalArgumentException("查無貨幣費率資訊");
        }

        cfgCurrencyInfoDao.deleteByCurrencyCode(currencyInfo.getCurrencyCode());
        mapCurrencyDescDao.deleteByCurrencyNo(currencyDesc.getCurrencyNo());
        curExchangeRate.deleteByCurrencyNo(rate.getCurrencyNo());
    }

    @Override
    public CurrencyDataDto updateCurrency(String currencyCode, CurrencyDto data) {

        if (StringUtils.isBlank(currencyCode) || data == null) {
            throw new IllegalArgumentException("currency 與 data 為必填參數");
        }
        CfgCurrencyInfo currencyInfo = cfgCurrencyInfoDao.findOneByCurrencyCode(
                CommonUtil.convertFullWidthToHalfWidth(currencyCode).toUpperCase());
        if (currencyInfo == null) {
            throw new IllegalArgumentException("查無貨幣資訊");
        }
        MapCurrencyDesc currencyDesc = mapCurrencyDescDao.findById(currencyInfo.getNo()).orElse(null);
        if (currencyDesc == null) {
            throw new IllegalArgumentException("查無貨幣注釋資訊");
        }
        CurExchangeRate rate = curExchangeRate.findById(currencyInfo.getNo()).orElse(null);
        if (rate == null) {
            throw new IllegalArgumentException("查無貨幣費率資訊");
        }

        LocalDateTime now = LocalDateTime.now();
        String user = "manson";

        currencyInfo.setCurrencyCode(data.getCurrencyCode());
        currencyInfo.setUpdateUser(user);
        currencyInfo.setUpdateAt(now);
        cfgCurrencyInfoDao.save(currencyInfo);

        currencyDesc.setCurrencyName(data.getCurrencyName());
        currencyDesc.setUpdateUser(user);
        currencyDesc.setUpdateAt(now);
        mapCurrencyDescDao.save(currencyDesc);

        rate.setExchangeRate(data.getExchangeRate());
        rate.setUpdateUser(user);
        rate.setUpdateAt(now);
        curExchangeRate.save(rate);

        return cfgCurrencyInfoDao.findDataByCode(CommonUtil.convertFullWidthToHalfWidth(data.getCurrencyCode()).toUpperCase());
    }

    @Override
    public CurrencyDataDto addCurrency(CurrencyDto data) {

        if (data == null) {
            throw new IllegalArgumentException("data 為必填參數");
        }
        CfgCurrencyInfo currentInfo = cfgCurrencyInfoDao.findOneByCurrencyCode(
                CommonUtil.convertFullWidthToHalfWidth(data.getCurrencyCode()).toUpperCase());
        if (currentInfo != null) {
            throw new IllegalArgumentException("貨幣資訊重複");
        }

        LocalDateTime now = LocalDateTime.now();
        String user = "manson";

        CfgCurrencyInfo currencyInfo = new CfgCurrencyInfo();
        currencyInfo.setCurrencyCode(data.getCurrencyCode());
        currencyInfo.setCreateUser(user);
        currencyInfo.setCreatedAt(now);
        currencyInfo.setUpdateUser(user);
        currencyInfo.setUpdateAt(now);
        CfgCurrencyInfo newInfo = cfgCurrencyInfoDao.save(currencyInfo);

        MapCurrencyDesc currencyDesc = new MapCurrencyDesc();
        currencyDesc.setCurrencyNo(newInfo.getNo());
        currencyDesc.setCreateUser(user);
        currencyDesc.setCreatedAt(now);
        currencyDesc.setCurrencyName(data.getCurrencyName());
        currencyDesc.setUpdateUser(user);
        currencyDesc.setUpdateAt(now);
        mapCurrencyDescDao.save(currencyDesc);

        CurExchangeRate rate = new CurExchangeRate();
        rate.setCurrencyNo(newInfo.getNo());
        rate.setCreateUser(user);
        rate.setCreatedAt(now);
        rate.setExchangeRate(data.getExchangeRate());
        rate.setUpdateUser(user);
        rate.setUpdateAt(now);
        curExchangeRate.save(rate);

        return cfgCurrencyInfoDao.findDataByCode(currencyInfo.getCurrencyCode());
    }

    @Override
    public CoinDeskFullDataDto getFullCoinData() throws Exception {
        return coinDeskMware.callCoinDeskApi();
    }

    @Override
    public CoinDeskSampleDataDto getSampleCoinData() throws Exception {
        CoinDeskFullDataDto fullData = coinDeskMware.callCoinDeskApi();

        TimeDataDto timeData = fullData.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        ZonedDateTime gmtZonedDateTime = ZonedDateTime.parse(timeData.getUpdatedISO(), formatter);
        // 轉換為台北時區
        ZonedDateTime taipeiZonedDateTime = gmtZonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Taipei"));
        DateTimeFormatter taipeiFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String taipeiDateTime = taipeiZonedDateTime.format(taipeiFormatter);

        CoinDeskSampleDataDto result = new CoinDeskSampleDataDto();
        result.setUpdateAt(taipeiDateTime);

        List<CurrencyDto> currencies = fullData.getBpi().getCurrencies().values().stream().map(value -> {
            CurrencyDto currency = new CurrencyDto();
            currency.setCurrencyCode(value.getCode());
            currency.setCurrencyName(value.getDescription());
            currency.setExchangeRate(value.getRateFloat());
            return currency;
        }).collect(Collectors.toList());
        result.setCurrencies(currencies);

        return result;
    }
}
