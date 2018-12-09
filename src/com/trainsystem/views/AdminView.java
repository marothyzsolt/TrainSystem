package com.trainsystem.views;

import com.trainsystem.models.User;
import menusystem.MenuHelper;

import java.util.ArrayList;
import java.util.Map;

public class AdminView {


    public static Map<String, String> createUser()
    {
        String username = MenuHelper.readLine("Felhasználónév: ");
        String password = MenuHelper.readLine("Jelszó: ");

        int role;
        do {
            role = MenuHelper.readInt("Szerepkör [1: customer; 2: worker; 3: admin]: ");
        } while (role < 1 || role > 3);

        return Map.of(
                "username", username,
                "password", password,
                "role", String.valueOf(role)

        );
    }


    public static void createUserSuccess()
    {
        System.out.println("Sikeres felhasználó létrehozás!");
    }

    public static void showUsers(ArrayList<User> all) {
        all.forEach(UserView::showUser);
    }
    public static int showUsersDelete(ArrayList<User> all) {
        showUsers(all);
        return MenuHelper.readInt("Törölni kívánt ID: ");
    }

    public static void deleteUserSuccess()
    {
        System.out.println("Sikeres felhasználó törlés!");
    }
    public static void deleteUserFailed() { System.out.println("Ismeretlen felhasználó azonosító!"); }
}
