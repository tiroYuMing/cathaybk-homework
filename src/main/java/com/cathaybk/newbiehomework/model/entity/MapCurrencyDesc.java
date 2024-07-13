package com.cathaybk.newbiehomework.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "MAP_CURRENCY_DESC")
public class MapCurrencyDesc {

    @Id
    @Column(name = "CURRENCY_NO")
    private Long currencyNo;

    @Column(name = "CURRENCY_NAME")
    private String currencyName;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    public Long getCurrencyNo() {
        return currencyNo;
    }

    public void setCurrencyNo(Long currencyNo) {
        this.currencyNo = currencyNo;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
