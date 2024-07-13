package com.cathaybk.newbiehomework.model.dto;

import java.util.List;

public class CoinDeskFullDataDto {

    private TimeDataDto time;
    private String disclaimer;
    private String chartName;
    private BpiDto bpi;

    public TimeDataDto getTime() {
        return time;
    }

    public void setTime(TimeDataDto time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public BpiDto getBpi() {
        return bpi;
    }

    public void setBpi(BpiDto bpi) {
        this.bpi = bpi;
    }
}
