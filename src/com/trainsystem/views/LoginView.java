package com.trainsystem.views;

import com.trainsystem.helpers.Pair;
import menusystem.MenuHelper;

public class LoginView {
    public static Pair<String, String> login()
    {
        String username = MenuHelper.readLine("Felhasználónév: ");
        String password = MenuHelper.readLine("Jelszó: ");

        Pair<String, String> pair = new Pair<>(username, password);

        return pair;
    }
}
