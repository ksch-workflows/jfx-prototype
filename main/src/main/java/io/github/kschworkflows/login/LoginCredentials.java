package io.github.kschworkflows.login;

class LoginCredentials {

    private static LoginCredentials instance;

    private String userName;

    private String password;

    private String openMRSUrl;

    protected LoginCredentials() {
        openMRSUrl = "https://ksch/openmrs";
        userName = "superman";
        password = "Admin123";
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
