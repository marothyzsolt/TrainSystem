package com.trainsystem.views;

import com.trainsystem.models.CreditCard;
import com.trainsystem.services.StorageService;
import menusystem.MenuHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class CreditCardView {

    public static CreditCard getCard() {
        Long cardNumber = Long.parseLong(MenuHelper.readLine("Kártyaszám: "));
        String expiry = MenuHelper.readLine("Lejárat: ");
        Long CCN = Long.parseLong(MenuHelper.readLine("CCN: "));
        String type = MenuHelper.readLine("Típus: ");
        CreditCard creditCard = new CreditCard(cardNumber, expiry, CCN, type);
        return creditCard;
    }

    public static Long getCreditCardNumber() {
        Long cardNumber = Long.parseLong(MenuHelper.readLine("Kártyaszám: "));
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
