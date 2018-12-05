package com.cham.feignserver.domain;

import java.io.Serializable;

public class Trade implements Serializable {

    private String tradeId;
    private String tradeName;
    private int amount;

    public Trade(){}

    public Trade(String id, String name, int amount){
        this.tradeId = id;
        this.tradeName = name;
        this.amount = amount;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", tradeName='" + tradeName + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

}
