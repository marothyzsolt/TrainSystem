package com.trainsystem.controllers;

import com.trainsystem.models.User;
import com.trainsystem.views.AdminView;

import java.util.Map;
import java.util.Objects;

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


    public static void deleteUser() {
        int deleteId = AdminView.showUsersDelete(User.all());
        User user = User.make(User.find("users", deleteId).first());
        if(Objects.isNull(user))
            AdminView.deleteUserFailed();
        else {
            user.delete();
            AdminView.deleteUserSuccess();
        }
    }

    public static void showUsers() {
        AdminView.showUsers(User.all());
    }
}
