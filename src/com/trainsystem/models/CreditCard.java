package com.trainsystem.models;

import com.trainsystem.db.DbJsonObject;
import org.json.simple.JSONObject;

import java.util.Map;

public class CreditCard extends BaseModel {
    private int CCN;
    private int cardNumber;
    private String expiry;
    private String type;

    public CreditCard( int cardNumber, String expiry, int CCN, String type) {
        this.CCN = CCN;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.type = type;
    }

    public CreditCard(DbJsonObject card) {
        this.id = card.getInt("id");
        this.CCN = card.getInt("CCN");
        this.cardNumber = card.getInt("cardNumber");
        this.expiry = card.getString("expiry");
        this.type = card.getString("type");
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

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCCN() {
        return CCN;
    }

    public void setCCN(int CCN) {
        this.CCN = CCN;
    }

    public void show() {
        System.out.println("Kártyaszám: " + cardNumber);
        System.out.println("Lejárat: " + expiry);
        System.out.println("CCN: " + CCN);
        System.out.println("Típus: " + type);
    }

    @Override
    protected Map<String, String> insert(int id) {
        return null;
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }
}
