package com.trainsystem.views;

import com.trainsystem.helpers.Pair;
import com.trainsystem.models.Route;
import com.trainsystem.models.SystemInfo;
import com.trainsystem.models.Time;
import com.trainsystem.models.User;
import menusystem.MenuHelper;

import java.util.ArrayList;
import java.util.Map;

public class RouteView {
    private Route model;

    public RouteView(Route model) {
        this.model = model;
    }

    public static void listRoutes(ArrayList<Route> all) {
        all.forEach(RouteView::showRoute);
    }

    public static void showRoute(Route route) {
        System.out.println("ID: " + route.getId() + " " + route.getFrom() + " -> " + route.getTo() + " ("+route.getDistance()+" km)" + " ("+route.getTimeTableCount()+" db időpont)");
    }

    public static int getRoute() {
        return MenuHelper.getInstance().readInt("Adja meg az útvonal azonosítóját: ");
    }

    public void showRoute()
    {
        System.out.println("-- " + model.getFrom() + " - " + model.getTo() + " --");
        System.out.println("-- " + model.getDistance() + " [ "+model.getPrice()+" Ft ] " + " --");
    }

    public void showTimeTable()
    {
        for (Time time : model.getTimes()) {
            System.out.println("\t " + time.getStartFormat() + " - " + time.getArriveFormat());
        }
    }

    public static Map<String, String> createRoute()
    {
        System.out.println("--- Új útvonal hozzáadása ---");
        String from = MenuHelper.getInstance().readLine("Indulási helyszín: ");
        String to = MenuHelper.getInstance().readLine("Érkezési helyszín: ");
        String distance = String.valueOf(MenuHelper.getInstance().readInt("Távolság (km): "));
        return Map.of(
                "from", from,
                "to", to,
                "distance", distance
        );
    }

    public static void createRouteFailed() { System.out.println("Már létezik ezzel a helyszínekkel útvonal!"); }
    public static void createRouteSuccess() { System.out.println("Sikeres útvonal hozzáadás!"); }

    public Map<String, String> createTimeTable()
    {
        System.out.println("--- Új indulás-érkezés hozzáadása ---");
        showRoute();
        String start = MenuHelper.getInstance().readLine("Indulási időpont: ");
        String arrive = MenuHelper.getInstance().readLine("Érkezési időpont: ");
        return Map.of(
                "start", start,
                "arrive", arrive
        );
    }

    public static void noRouteExistsError()
    {
        System.out.println("A megadott útvonal nem létezik!");
    }
}
