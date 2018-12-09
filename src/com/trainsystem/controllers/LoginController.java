package com.trainsystem.controllers;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.helpers.Pair;
import com.trainsystem.models.User;
import com.trainsystem.services.StorageService;
import com.trainsystem.views.LoginView;

public class LoginController {
    protected final User model;
    protected final LoginView view;

    public LoginController(User model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    public static LoginController loginUser()
    {
        Pair<String, String> pair = LoginView.login();
        String username = pair.getLeft();
        String password = pair.getRight();

        User user = User.make(User.where("users", Filter.filter(Criteria.where("username").is(username).and("password").is(password))).first());
        if(user != null) {
            LoginController loginController = new LoginController(user, new LoginView(user));
            StorageService.getInstance().setCurrentUser(user);
            loginController.view.loginSuccess();
            return loginController;
        }
        else
            LoginView.loginError();

        return null;
    }

    public static void logout() {
        LoginView loginView = new LoginView(StorageService.getInstance().user());
        loginView.logoutSuccess();

        StorageService.getInstance().setCurrentUser(null);
    }
}
