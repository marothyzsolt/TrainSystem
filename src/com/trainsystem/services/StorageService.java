package com.trainsystem.services;

import com.trainsystem.models.User;

public class StorageService {
    private static StorageService ourInstance = new StorageService();
    private User currentUser;

    public static StorageService getInstance() {
        return ourInstance;
    }

    private StorageService() { }

    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }
    public User getCurrentUser() { return currentUser; }
    public User user() { return currentUser; }
    public boolean isLogined() { return currentUser != null; }
}
