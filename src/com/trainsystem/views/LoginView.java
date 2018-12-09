package com.trainsystem.views;

import com.trainsystem.helpers.Pair;
import com.trainsystem.models.User;
import menusystem.MenuHelper;

public class LoginView {
    private User model;

    public LoginView(User model) {
        this.model = model;
    }

    public static Pair<String, String> login()
    {
        String username = MenuHelper.readLine("Felhasználónév: ");
        String password = MenuHelper.readLine("Jelszó: ");

        Pair<String, String> pair = new Pair<>(username, password);

        return pair;
    }

    public static void loginError()
    {
        System.out.println("Hibás bejelentkezési adatok!");
    }

    public void loginSuccess()
    {
        System.out.println("Sikeres bejelentkezés, "+model.getUsername()+"!");
    }

    public void logoutSuccess() {
        System.out.println("Sikeres kijelentkezés, "+model.getUsername()+"!");
    }
}
