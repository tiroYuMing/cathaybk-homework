package com.cathaybk.newbiehomework.controller;

import com.cathaybk.newbiehomework.model.dto.CoinDeskFullDataDto;
import com.cathaybk.newbiehomework.model.dto.CoinDeskSampleDataDto;
import com.cathaybk.newbiehomework.model.dto.CurrencyDataDto;
import com.cathaybk.newbiehomework.model.dto.CurrencyDto;
import com.cathaybk.newbiehomework.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Tag(name = "addCurrencyInfo")
    @Operation(summary = "新增貨幣資訊")
    @PostMapping
    public CurrencyDataDto addCurrencyInfo(CurrencyDto data) {
        return currencyService.addCurrency(data);
    }

    @Tag(name = "getAllCurrencyInfo")
    @Operation(summary = "取得所有貨幣資訊")
    @GetMapping
    public ResponseEntity<List<CurrencyDataDto>> getAllCurrencyInfo() {
        return ResponseEntity.ok(currencyService.getCurrencies());
    }

    @Tag(name = "getCurrencyInfo")
    @Operation(summary = "取得單一貨幣資訊")
    @GetMapping("/{currencyCode}")
    public ResponseEntity<CurrencyDataDto> getCurrencyInfo(@PathVariable String currencyCode) {
        return ResponseEntity.ok(currencyService.getCurrencyByCode(currencyCode));
    }

    @Tag(name = "updateCurrencyInfo")
    @Operation(summary = "更新貨幣資訊")
    @PutMapping("/{currencyCode}")
    public ResponseEntity<CurrencyDataDto> updateCurrencyInfo(@PathVariable String currencyCode, CurrencyDto data) {
        return ResponseEntity.ok(currencyService.updateCurrency(currencyCode, data));
    }

    @Tag(name = "deleteCurrencyInfo")
    @Operation(summary = "刪除貨幣資訊")
    @DeleteMapping("/{currencyCode}")
    public ResponseEntity<Void> deleteCurrencyInfo(@PathVariable String currencyCode) {
        currencyService.deleteCurrency(currencyCode);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "getFullCoinData")
    @Operation(summary = "呼叫 coindesk API 取得完整資訊")
    @GetMapping("getFullCoinData")
    public ResponseEntity<CoinDeskFullDataDto> getFullCoinData() throws Exception {
        return ResponseEntity.ok(currencyService.getFullCoinData());
    }

    @Tag(name = "getSampleCoinData")
    @Operation(summary = "呼叫 coindesk API 取得基本資訊")
    @GetMapping("getSampleCoinData")
    public ResponseEntity<CoinDeskSampleDataDto> getSampleCoinData() throws Exception {
        return ResponseEntity.ok(currencyService.getSampleCoinData());
    }

}
