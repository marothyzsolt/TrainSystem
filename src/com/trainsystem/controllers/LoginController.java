package com.trainsystem.controllers;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.trainsystem.helpers.Pair;
import com.trainsystem.models.User;
import com.trainsystem.views.LoginView;

public class LoginController {
    protected final User model;
    protected final LoginView view;

    public LoginController(User model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    public static void loginUser()
    {
        Pair<String, String> pair = LoginView.login();
        String username = pair.getLeft();
        String password = pair.getRight();

        System.out.println(username);
        System.out.println(password);

        User user = User.make(User.where("users", Filter.filter(Criteria.where("username").is(username).and("password").is(password))).first());
        System.out.println(user);
    }
}
