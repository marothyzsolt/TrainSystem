package com.trainsystem.views;

import com.trainsystem.helpers.Pair;
import com.trainsystem.models.User;
import menusystem.MenuHelper;

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

}
