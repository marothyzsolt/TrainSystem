package com.trainsystem.controllers;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.helpers.Pair;
import com.trainsystem.models.User;
import com.trainsystem.services.StorageService;
import com.trainsystem.views.AdminView;
import com.trainsystem.views.LoginView;

import java.util.Map;

public class UserController {


    public static void createUser()
    {
        Map<String, String> userData = AdminView.createUser();
        String role = "";
        switch (Integer.valueOf(userData.get("role")))
        {
            case 1: role = "customer"; break;
            case 2: role = "worker"; break;
            case 3: role = "admin"; break;
        }

        new User(userData.get("username"), userData.get("password"), role).store();

        AdminView.createUserSuccess();
    }


}
