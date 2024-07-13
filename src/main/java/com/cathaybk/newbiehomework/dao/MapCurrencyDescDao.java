package com.cathaybk.newbiehomework.dao;

import com.cathaybk.newbiehomework.model.entity.CurExchangeRate;
import com.cathaybk.newbiehomework.model.entity.MapCurrencyDesc;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapCurrencyDescDao extends CrudRepository<MapCurrencyDesc, Long> {

    @Modifying
    void deleteByCurrencyNo(Long currencyNo);
}
