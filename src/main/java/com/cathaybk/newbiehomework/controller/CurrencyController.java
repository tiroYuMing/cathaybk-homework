package com.cathaybk.newbiehomework.controller;

import com.cathaybk.newbiehomework.model.dto.CurrencyDataDto;
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
    public CurrencyDataDto addCurrencyInfo(CurrencyDataDto data) {
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
    @GetMapping("/{currencyName}")
    public ResponseEntity<CurrencyDataDto> getCurrencyInfo(@PathVariable String currencyName) {
        return ResponseEntity.ok(currencyService.getCurrencyByName(currencyName));
    }

    @Tag(name = "updateCurrencyInfo")
    @Operation(summary = "更新貨幣資訊")
    @PutMapping("/{currencyName}")
    public ResponseEntity<CurrencyDataDto> updateCurrencyInfo(@PathVariable String currencyName, CurrencyDataDto data) {
        return ResponseEntity.ok(currencyService.updateCurrency(currencyName, data));
    }

    @Tag(name = "deleteCurrencyInfo")
    @Operation(summary = "刪除貨幣資訊")
    @DeleteMapping("/{currencyName}")
    public ResponseEntity<Void> deleteCurrencyInfo(@PathVariable String currencyName) {
        currencyService.deleteCurrency(currencyName);
        return ResponseEntity.ok().build();
    }

}
