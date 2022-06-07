package com.comflip.engine.net;

import java.util.HashMap;

public class MatchSession {
    private static String host = "";
    private static String guest = "";
    private static String idMatch = "";

    private static final HashMap<String, String> players = new HashMap<>();

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        players.put("white", host);
        MatchSession.host = host;
    }

    public static String getGuest() {
        return guest;
    }

    public static void setGuest(String guest) {
        players.put("black", guest);
        MatchSession.guest = guest;
    }

    public static HashMap<String, String> getPlayers(){
        return players;
    }

    public static String getIdMatch() {
        return idMatch;
    }

    public static void setIdMatch(String idMatch) {
        MatchSession.idMatch = idMatch;
    }
}
