package com.trainsystem.services;

import com.trainsystem.models.Costumer;
import com.trainsystem.models.User;

public class StorageService {
    private static StorageService ourInstance = new StorageService();
    private User currentUser;
    private Costumer currentCostumer;

    public static StorageService getInstance() {
        return ourInstance;
    }

    private StorageService() { }

    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }
    public User getCurrentUser() { return currentUser; }
    public User user() { return currentUser; }
    public boolean isLogined() { return currentUser != null; }
    public Costumer costumer() { return currentCostumer; }
    public void setCurrentCostumer(Costumer currentCostumer) { this.currentCostumer = currentCostumer; }
}
