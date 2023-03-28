package com.switchfully.order.domain.models.user;

public class UserCredentials {

    private final String username;
    private final String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
}