package io.github.kschworkflows.services;

public class LoginCredentials {

    private static LoginCredentials instance;

    private String userName;

    private String password;

    private String openMRSUrl;

    protected LoginCredentials() {

    }

    public static LoginCredentials getInstance() {
        if (instance == null) {
            instance = new LoginCredentials();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenMRSUrl() {
        return openMRSUrl;
    }

    public void setOpenMRSUrl(String openMRSUrl) {
        this.openMRSUrl = openMRSUrl;
    }
}
