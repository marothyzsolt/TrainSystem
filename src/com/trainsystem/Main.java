package com.trainsystem;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.controllers.RouteController;
import com.trainsystem.db.DatabaseConnection;
import com.trainsystem.db.Query;
import com.trainsystem.models.BaseModel;
import com.trainsystem.models.Route;
import com.trainsystem.models.SystemInfo;
import com.trainsystem.models.User;
import com.trainsystem.services.StorageService;
import menusystem.MenuController;
import menusystem.menus.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws ParseException {

        //Route r = Route.make(Route.find("routes", 1).first());
        //System.out.println(r);

        ///User u = User.make(User.find("users", 4).first());
       // u.delete("users", 4);
      //  System.out.println(u);

        //SystemInfo systemInfo = SystemInfo.getInstance();
        //systemInfo.setKmPrice(10);
        //systemInfo.store();
        //System.out.println(o);//

        //RouteController.createTimeTable();


        //RouteController.createTimeTable();

        //RouteController.deleteTimeTable();


        while(true) {
            if(StorageService.getInstance().user() == null)
                MenuController.create(new MainMenu()).execute();
            else if(StorageService.getInstance().user().isUser()) // USER
                MenuController.create(new UserMainMenu()).execute();
            else if(StorageService.getInstance().user().isWorker()) // WORKER
                MenuController.create(new WorkerMainMenu()).execute();
            else if(StorageService.getInstance().user().isAdmin()) // ADMIN
                MenuController.create(new AdminMainMenu()).execute();
        }

        //DatabaseConnection.getInstance();

       // Query q = new Query("users");

        /** Összetett feltételes lekérdezés OR **/
        //q.where(Filter.filter(Criteria.where("username").is("user")).or(Criteria.where("username").is("worker")));
        //ArrayList<User> user = User.make(q.all());

        /** Összetett feltételes lekérdezés AND **/
        /** A két megoldás közül, én inkább az elsőt javaslom **/
        //q.where(Filter.filter(Criteria.where("username").is("user").and("password").is("testpass"))); // ez is helyes
        //q.where(Filter.filter(Criteria.where("username").is("user")).and(Criteria.where("password").is("testpass"))); // ez is helyes
        //ArrayList<User> user = User.make(q.all());

        /**  Szimpla lekrédezés **/
        //q.where(Filter.filter(Criteria.where("username").is("user")));
        //User user = User.make(q.first());

        /**  Szimpla lekrédezés egy soros **/
        //User user = User.make(BaseModel.where("users", "username", "user")).get(0);
        //User user2 = User.make(BaseModel.where("users", "username", "wapor")).get(0);

        /** USER létrehozás */
        //User user = new User("wapor", "asd123", "admin");
        //user.store();

        /** USER TÖRLÉS */ // 4-es az id (nem a sorszám, hanem az id a users táblában a json fájlban!!!!
        //BaseModel.delete("users", 4);

        //System.out.println(user);
        //System.out.println(user2);
    }
}
