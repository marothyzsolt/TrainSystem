package com.trainsystem.controllers;

import com.trainsystem.helpers.Pair;
import com.trainsystem.models.Route;
import com.trainsystem.models.SystemInfo;
import com.trainsystem.models.Time;
import com.trainsystem.models.User;
import com.trainsystem.views.RouteView;

import java.util.Map;

public class RouteController {
    private Route model;
    private RouteView view;

    public RouteController(Route model, RouteView view) {
        this.model = model;
        this.view = view;
    }

    public static void listRoutes() {
        RouteView.listRoutes(Route.all());
    }

    private static RouteController selectRoute()
    {
        int id = RouteView.getRoute();
        Route route = Route.make(Route.find("routes", id).first());
        if(route == null)
            return null;
        else
            return new RouteController(route, new RouteView(route));
    }

    public static void listRouteTimeTable() {
        listRoutes();
        RouteController routeController = selectRoute();
        if(routeController != null)
        {
            routeController.view.showRoute();
            routeController.view.showTimeTable();
        } else RouteView.noRouteExistsError();
    }

    public static void createRoute() {
        Map<String, String> routeData = RouteView.createRoute();
        Route route = Route.whereFromTo(routeData.get("from"), routeData.get("to"));
        if(route != null)
            RouteView.createRouteFailed();
        else {
            new Route(routeData.get("from"), routeData.get("to"), Integer.parseInt(routeData.get("distance"))).store();
            RouteView.createRouteSuccess();
        }
    }

    public static void createTimeTable() {
        listRoutes();
        //RouteController routeController = selectRoute();
        RouteController routeController = new RouteController(Route.make(Route.find("routes", 3).first()), new RouteView(null));
        if(routeController.model != null) {
            //Map<String, String> timeTableData = routeController.view.createTimeTable();
            //Time time = new Time(timeTableData.get("start"), timeTableData.get("arrive"));
            Time time = new Time("2018-01-01 00:00", "2018-01-01 01:00");
            System.out.println(time);

            routeController.model.addTime(time);

        } else RouteView.noRouteExistsError();
    }
}
