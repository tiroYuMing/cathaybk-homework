package com.cathaybk.newbiehomework.middleware;

import com.cathaybk.newbiehomework.config.SysConfig;
import com.cathaybk.newbiehomework.model.dto.BpiCurrencyDto;
import com.cathaybk.newbiehomework.model.dto.BpiDto;
import com.cathaybk.newbiehomework.model.dto.CoinDeskFullDataDto;
import com.cathaybk.newbiehomework.model.dto.TimeDataDto;
import com.cathaybk.newbiehomework.util.HttpUtil;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class CoinDeskMware {

    public CoinDeskFullDataDto callCoinDeskApi() throws Exception {
        JSONObject response = HttpUtil.doGet(SysConfig.COIN_DESK_API_URL);

        JSONObject timeObject = response.getJSONObject("time");
        TimeDataDto timeData = new TimeDataDto();
        timeData.setUpdated(timeObject.getString("updated"));
        timeData.setUpdatedISO(timeObject.getString("updatedISO"));
        timeData.setUpdateduk(timeObject.getString("updateduk"));

        JSONObject bpiObject = response.getJSONObject("bpi");
        Map<String, BpiCurrencyDto> currencyMap = new HashMap<>();
        Iterator<String> keys = bpiObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject currencyObject = bpiObject.getJSONObject(key);

            BpiCurrencyDto currency = new BpiCurrencyDto();
            currency.setCode(currencyObject.getString("code"));
            currency.setSymbol(StringEscapeUtils.unescapeHtml4(currencyObject.getString("symbol")));
            currency.setRate(currencyObject.getString("rate"));
            currency.setDescription(currencyObject.getString("description"));
            currency.setRateFloat(currencyObject.getBigDecimal("rate_float"));

            currencyMap.put(key, currency);
        }
        BpiDto bpiData = new BpiDto();
        bpiData.setCurrencies(currencyMap);

        CoinDeskFullDataDto fullDto = new CoinDeskFullDataDto();
        fullDto.setTime(timeData);
        fullDto.setDisclaimer(response.getString("disclaimer"));
        fullDto.setChartName(response.getString("chartName"));
        fullDto.setBpi(bpiData);

        return fullDto;
    }
}
