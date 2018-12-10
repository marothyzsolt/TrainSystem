package com.trainsystem.models;

import com.trainsystem.db.DbJsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Costumer extends User {
    private User user;
    private ArrayList<CreditCard> creditCards;
    private ArrayList<Purchase> purchases;

    public Costumer(DbJsonObject jsonObject) {
        super(jsonObject);
        user = new User(jsonObject);
        if (jsonObject.get("creditCards") != null) {
            ArrayList<CreditCard> cards = new ArrayList<>();
            JSONArray jsonCards = (JSONArray) jsonObject.get("creditCards");
            for ( int i = 0; i < jsonCards.size(); i++) {
                CreditCard card = new CreditCard((JSONObject) jsonCards.get(i));
                cards.add(card);
            }
            creditCards = cards;
        } else {
            creditCards = new ArrayList<>();
        }
        if (jsonObject.get("purchases") != null) {
            ArrayList<Purchase> purchases = new ArrayList<>();
            JSONArray jsonPurchases = (JSONArray) jsonObject.get("purchases");
            for ( int i = 0; i < jsonPurchases.size(); i++) {
                Purchase purchase = new Purchase((JSONObject) jsonPurchases.get(i));
                purchases.add(purchase);
            }
            this.purchases = purchases;
        } else {
            purchases = new ArrayList<>();
        }
    }

    public void addCreditCard( CreditCard cardNumber) {
        creditCards.add( cardNumber);
    }

    public void removeCreditCard( Long cardNumber) {
        ArrayList<CreditCard> tempCards = new ArrayList<>();
        Iterator<CreditCard> iterator = creditCards.iterator();
        while ( iterator.hasNext()) {
            CreditCard tempCreditCard = iterator.next();
            if ( tempCreditCard.getCardNumber() != cardNumber) {
                tempCards.add(tempCreditCard);
            }
        }
        creditCards = tempCards;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public void store() {
        store("users");
    }
}
