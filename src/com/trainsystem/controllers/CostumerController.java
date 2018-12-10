package com.trainsystem.controllers;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.helpers.Pair;
import com.trainsystem.models.*;
import com.trainsystem.services.StorageService;
import com.trainsystem.views.CreditCardView;
import com.trainsystem.views.PurchasesView;
import com.trainsystem.views.SearchView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class CostumerController {
    private Costumer costumer;
    private JSONObject db;

    public CostumerController() {
        costumer = StorageService.getInstance().costumer();
        db = DatabaseConnection.getInstance().getDatabase();
    }
    public void addCreditCard() {
        CreditCard creditCard = CreditCardView.getCard();
        costumer.addCreditCard(creditCard);
    }

    public void removeCreditCard() {
        Long cardNumber = CreditCardView.getCreditCardNumber();
        costumer.removeCreditCard(cardNumber);
    }

    public void getCreditCards() {
        CreditCardView.showCreditCards(costumer.getCreditCards());
    }

    public void purchase() {
        Pair<Lease, CreditCard> pair = PurchasesView.purchase(db,costumer.getCreditCards());
        if ( pair != null) {
            Purchase purchase = new Purchase(pair.getLeft(), pair.getRight());
            costumer.addPurchase(purchase);
            PurchasesView.showSuccess();
        } else {
            PurchasesView.showError();
        }
    }

    public void purchases() {
        PurchasesView.showPurchases(costumer.getPurchases());
    }

    public static void search() {
        Pair<String, String> route = SearchView.getRoute();
        JSONObject jsonRoute = Route.where("routes", Filter.filter(Criteria.where("from").is(route.getLeft()).and("to").is(route.getRight()))).first();
        if ( jsonRoute != null) {
            JSONArray jsonTimes = (JSONArray) jsonRoute.get("times");
            ArrayList<Time> times = Time.make(jsonTimes);
            SearchView.showRoute(route, times);
        } else {
            SearchView.showError();
        }
    }
}
