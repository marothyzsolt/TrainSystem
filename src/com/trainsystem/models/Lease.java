package com.trainsystem.models;

import org.json.simple.JSONObject;

public class Lease {
    private Long price;
    private String from;
    private String to;

    public Lease( Long _price, String _from, String _to) {
        this.price = _price;
        this.from = _from;
        this.to = _to;
    }

    public Lease(JSONObject lease) {
        price = (Long) lease.get("price");
        from = (String) lease.get("from");
        to = (String) lease.get("to");
    }

    public boolean isEqual( Lease lease) {
        if (
                price == lease.getPrice() &&
                from == lease.getFrom() &&
                to == lease.getTo()
        ) {
            return true;
        }
        return false;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void show() {
        System.out.println("Ár: " + price);
        System.out.println("Ettől: " + from);
        System.out.println("Eddig: " + to);
    }
}
