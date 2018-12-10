package com.trainsystem.views;

import com.trainsystem.models.User;
import menusystem.MenuHelper;

import java.util.ArrayList;
import java.util.Map;

public class AdminView {


    public static Map<String, String> createUser()
    {
        String username = MenuHelper.getInstance().readLine("Felhasználónév: ");
        String password = MenuHelper.getInstance().readLine("Jelszó: ");

        int role;
        do {
            role = MenuHelper.getInstance().readInt("Szerepkör [1: customer; 2: worker; 3: admin]: ");
        } while (role < 1 || role > 3);

        return Map.of(
                "username", username,
                "password", password,
                "role", String.valueOf(role)

        );
    }


    public static void createUserSuccess() { System.out.println("Sikeres felhasználó létrehozás!"); }
    public static void createUserFailed() { System.out.println("Sikertelen felhasználó létrehozás! Ezzel a felhasználónévvel már létezik felhasználó!"); }

    public static int showUsersDelete(ArrayList<User> all) {
        UserView.showUsers(all);
        return MenuHelper.getInstance().readInt("Törölni kívánt ID: ");
    }

    public static void deleteUserSuccess()
    {
        System.out.println("Sikeres felhasználó törlés!");
    }
    public static void deleteUserFailed() { System.out.println("Ismeretlen felhasználó azonosító!"); }
}
