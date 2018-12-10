package com.trainsystem.models;

import org.json.simple.JSONObject;

import java.util.Date;

public class Purchase {
    private Lease lease;
    private CreditCard creditCard;
    private Date timestamp;

    public Purchase(Lease lease, CreditCard card, Date date) {
        this.lease = lease;
        this.creditCard = card;
        this.timestamp = date;
    }

    public Purchase(Lease lease, CreditCard card) {
        this.lease = lease;
        this.creditCard = card;
        this.timestamp = new Date();
    }

    public Purchase(JSONObject purchase) {
        JSONObject jsonLease = (JSONObject) purchase.get("lease");
        Lease lease = new Lease(jsonLease);
        JSONObject jsonCard = (JSONObject) purchase.get("creditCard");
        CreditCard creditCard = new CreditCard(jsonCard);
        this.lease = lease;
        this.creditCard = creditCard;
        this.timestamp = new Date();
    }

    public Lease getLease() {
        return lease;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
