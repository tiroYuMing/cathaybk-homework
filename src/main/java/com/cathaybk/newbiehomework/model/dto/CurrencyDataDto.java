package com.cathaybk.newbiehomework.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CurrencyDataDto {

    String getCurrencyCode();
    String getCurrencyName();
    BigDecimal getExchangeRate();
    LocalDateTime getUpdatedAt();
}
