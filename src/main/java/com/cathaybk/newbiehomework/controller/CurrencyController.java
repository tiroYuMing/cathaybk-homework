package com.cathaybk.newbiehomework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Tag(name = "addCurrencyInfo")
    @Operation(summary = "新增貨幣資訊")
    @PostMapping
    public void addCurrencyInfo() {
        // TODO
    }

    @Tag(name = "getAllCurrencyInfo")
    @Operation(summary = "取得所有貨幣資訊")
    @GetMapping
    public void getAllCurrencyInfo() {
        // TODO
    }

    @Tag(name = "getCurrencyInfo")
    @Operation(summary = "取得單一貨幣資訊")
    @GetMapping("/{currencyName}")
    public void getCurrencyInfo(@PathVariable String currencyName) {
        // TODO
    }

    @Tag(name = "updateCurrencyInfo")
    @Operation(summary = "更新貨幣資訊")
    @PutMapping("/{currencyName}")
    public void updateCurrencyInfo(@PathVariable String currencyName) {
        // TODO
    }

    @Tag(name = "deleteCurrencyInfo")
    @Operation(summary = "刪除貨幣資訊")
    @DeleteMapping("/{currencyName}")
    public void deleteCurrencyInfo(@PathVariable String currencyName) {
        // TODO
    }

}
