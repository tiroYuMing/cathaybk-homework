package com.cathaybk.newbiehomework.dao;

import com.cathaybk.newbiehomework.model.dto.CurrencyDataDto;
import com.cathaybk.newbiehomework.model.entity.CurExchangeRate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurExchangeRateDao extends CrudRepository<CurExchangeRate, Long> {

    @Modifying
    void deleteByCurrencyNo(Long currencyNo);
}
