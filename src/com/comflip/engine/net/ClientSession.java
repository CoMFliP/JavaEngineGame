package com.comflip.engine.net;

public class ClientSession {
    private static String session = "null:null";
    private static boolean isSession = false;

    private static String colorChecker = "";

    public static void setSession(String session) {
        ClientSession.session = session;
        isSession = true;
    }

    public static String getUsername() {
        return ClientSession.session.split(":")[0];
    }

    public static String getSessionID() {
        return ClientSession.session.split(":")[1];
    }

    public static boolean isSesson() {
        return isSession;
    }

    public static String getColorChecker() {
        return colorChecker;
    }

    public static void setColorChecker(String colorChecker) {
        ClientSession.colorChecker = colorChecker;
    }
}
