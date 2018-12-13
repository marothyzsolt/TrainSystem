package com.trainsystem.views;

import com.trainsystem.models.CreditCard;
import com.trainsystem.services.StorageService;
import menusystem.MenuHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class CreditCardView {

    public static CreditCard getCard() {
        int cardNumber = Integer.valueOf(MenuHelper.getInstance().readLine("Kártyaszám: "));
        String expiry = MenuHelper.getInstance().readLine("Lejárat: ");
        int CCN = Integer.valueOf(MenuHelper.getInstance().readLine("CCN: "));
        String type = MenuHelper.getInstance().readLine("Típus: ");
        CreditCard creditCard = new CreditCard(cardNumber, expiry, CCN, type);
        return creditCard;
    }

    public static Long getCreditCardNumber() {
        Long cardNumber = Long.parseLong(MenuHelper.getInstance().readLine("Kártyaszám: "));
        return cardNumber;
    }

    public static void showCreditCards(ArrayList<CreditCard> cards) {
        if ( cards.size() != 0) {
            Iterator<CreditCard> iterator = cards.iterator();
            while (iterator.hasNext()) {
                CreditCard card = iterator.next();
                card.show();
            }
        } else {
            System.out.println("Nincs hozzáadva kártya");
        }
    }
}
