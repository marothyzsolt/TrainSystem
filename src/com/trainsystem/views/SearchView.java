package com.trainsystem.views;

import com.trainsystem.helpers.Pair;
import com.trainsystem.models.Time;
import menusystem.MenuHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchView {
    public static Pair<String, String> getRoute() {
        String from = MenuHelper.readLine("Honnan: ");
        String to = MenuHelper.readLine("Hova: ");
        Pair<String, String> pair = new Pair<>(from, to);
        return pair;
    }

    public static void showRoute(Pair<String, String> route, ArrayList<Time> times) {
        System.out.println(route.getLeft() + " felől " + route.getRight() + " fele:");
        Iterator<Time> iterator = times.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Time time = iterator.next();
            System.out.println(i + ". " + time.getStartFormat() + " - " + time.getArriveFormat());
            i++;
        }
    }

    public static void showError() {
        System.out.println("Nincs ilyen útvonal.");
    }
}
