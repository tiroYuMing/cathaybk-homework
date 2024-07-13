package com.cathaybk.newbiehomework.dao;

import com.cathaybk.newbiehomework.model.dto.CurrencyDataDto;
import com.cathaybk.newbiehomework.model.entity.CfgCurrencyInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CfgCurrencyInfoDao extends CrudRepository<CfgCurrencyInfo, Long> {

    @Query(value = "SELECT info.CURRENCY_CODE as currencyCode," +
            "              desc.CURRENCY_NAME as currencyName," +
            "              rate.EXCHANGE_RATE as exchangeRate," +
            "              rate.UPDATED_AT as updatedAt" +
            "         FROM CFG_CURRENCY_INFO info" +
            "   INNER JOIN MAP_CURRENCY_DESC desc" +
            "           ON info.NO = desc.CURRENCY_NO" +
            "   INNER JOIN CUR_EXCHANGE_RATE rate" +
            "           ON info.NO = rate.CURRENCY_NO", nativeQuery = true)
    public List<CurrencyDataDto> findAllData();

    @Query(value = "SELECT info.CURRENCY_CODE as currencyCode," +
            "              desc.CURRENCY_NAME as currencyName," +
            "              rate.EXCHANGE_RATE as exchangeRate," +
            "              rate.UPDATED_AT as updatedAt" +
            "         FROM CFG_CURRENCY_INFO info" +
            "   INNER JOIN MAP_CURRENCY_DESC desc" +
            "           ON info.NO = desc.CURRENCY_NO" +
            "   INNER JOIN CUR_EXCHANGE_RATE rate" +
            "           ON info.NO = rate.CURRENCY_NO" +
            "        WHERE info.CURRENCY_CODE = :currencyCode", nativeQuery = true)
    CurrencyDataDto findDataByCode(@Param("currencyCode") String currencyCode);

    @Modifying
    void deleteByCurrencyCode(String currencyCode);

    CfgCurrencyInfo findOneByCurrencyCode(String currencyCode);
}
