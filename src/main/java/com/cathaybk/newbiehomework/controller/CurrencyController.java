package com.cathaybk.newbiehomework.controller;

import org.springframework.web.bind.annotation.*;

@RestController("/api/currency")
public class CurrencyController {

    @PostMapping
    public void addCurrencyInfo() {
        // TODO
    }

    @GetMapping
    public void getAllCurrencyInfo() {
        // TODO
    }

    @GetMapping("/{currencyName}")
    public void getCurrencyInfo(@PathVariable String currencyName) {
        // TODO
    }

    @PutMapping("/{currencyName}")
    public void updateCurrencyInfo(@PathVariable String currencyName) {
        // TODO
    }

    @DeleteMapping("/{currencyName}")
    public void deleteCurrencyInfo(@PathVariable String currencyName) {
        // TODO
    }

}
