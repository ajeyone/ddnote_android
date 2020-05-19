package com.ajeyone.daydaynote;

public class LoginManager {
    private static boolean login = false;

    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean isLogin) {
        login = isLogin;
    }

    public static String getUserName() {
        return login ? "ajeyone" : "";
    }
}
