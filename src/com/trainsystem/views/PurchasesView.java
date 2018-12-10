package com.trainsystem.views;

import com.trainsystem.helpers.Pair;
import com.trainsystem.models.CreditCard;
import com.trainsystem.models.Lease;
import com.trainsystem.models.Purchase;
import menusystem.MenuHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class PurchasesView {
    public static void showPurchases(ArrayList<Purchase> purchases) {
        if ( purchases.size() != 0) {
            Iterator<Purchase> iterator = purchases.iterator();
            while (iterator.hasNext()) {
                Purchase purchase = iterator.next();
                System.out.println("Bérlet: ");
                purchase.getLease().show();
                System.out.println("Bankkártya: ");
                purchase.getCreditCard().show();
            }
        } else {
            System.out.println("Nincs vásárlási előzmény.");
        }
    }
    public static Pair<Lease, CreditCard> purchase(JSONObject db, ArrayList<CreditCard> creditCards) {
        if ( creditCards.size() != 0) {
            ArrayList<Lease> leases = new ArrayList<>();
            JSONArray jsonLeases = (JSONArray) db.get("leases");
            for ( int i = 0; i < jsonLeases.size(); i++) {
                Lease lease = new Lease((JSONObject) jsonLeases.get(i));
                leases.add(lease);
            }
            Iterator<Lease> leaseit = leases.iterator();
            int i = 0;
            while (leaseit.hasNext()) {
                Lease lease = leaseit.next();
                System.out.println(i + ".Bérlet: ");
                lease.show();
                i++;
            }

            Iterator<CreditCard> iterator = creditCards.iterator();
            i = 0;
            while (iterator.hasNext()) {
                CreditCard creditCard = iterator.next();
                System.out.println(i + ".Bankkártya: ");
                creditCard.show();
                i++;
            }

            int numberOfLease = Integer.parseInt(MenuHelper.readLine("Bérlet száma: "));
            int numberOfCard = Integer.parseInt(MenuHelper.readLine(("Bankkártya sorszáma: ")));
            Pair<Lease, CreditCard> pair = new Pair<>(leases.get(numberOfLease), creditCards.get(numberOfCard));
            return pair;
        } else {
            System.out.println("Adjon hozzá bankkártyát!");
            return null;
        }
    }

    public static void showError() {
        System.out.println("Sikertelen vásárlás. Próbálja újra.");
    }

    public static void showSuccess() {
        System.out.println("Sikeres vásárlás.");
    }
}
