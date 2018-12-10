package com.trainsystem.models;

import org.json.simple.JSONObject;

public class CreditCard {
    private Long CCN;
    private Long cardNumber;
    private String expiry;
    private String type;

    public CreditCard( Long cardNumber, String expiry, Long CCN, String type) {
        this.CCN = CCN;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.type = type;
    }

    public CreditCard(JSONObject card) {
        this.CCN = (Long) card.get("CCN");
        this.cardNumber = (Long) card.get("cardNumber");
        this.expiry = (String) card.get("expiry");
        this.type = (String) card.get("type");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getCCN() {
        return CCN;
    }

    public void setCCN(Long CCN) {
        this.CCN = CCN;
    }

    public void show() {
        System.out.println("Kártyaszám: " + cardNumber);
        System.out.println("Lejárat: " + expiry);
        System.out.println("CCN: " + CCN);
        System.out.println("Típus: " + type);
    }
}
