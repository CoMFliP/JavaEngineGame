package com.comflip.engine.net;

public class ClientSession {
    private static String session = "null:null";

    public static void setSession(String session) {
        ClientSession.session = session;
    }
    public static String getUsername() {
        return ClientSession.session.split(":")[0];
    }

    public static String getSessionID() {
        return ClientSession.session.split(":")[1];
    }
}
