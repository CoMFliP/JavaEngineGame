package com.comflip.engine.net;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ClientSoket {
    private Socket clientSocket;
    private BufferedWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.write(msg);
        out.newLine();
        out.flush();

        String response;
        if ((response = in.readLine()) != null) {
            return response;
        } else {
            return null;
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static HashMap<String, String> decodeReponse(String reponse) {
        HashMap<String, String> mapReponse = new HashMap<>();

        for (String line : reponse.split("&")) {
            mapReponse.put(line.split("=")[0], line.split("=")[1]);
        }

        return mapReponse;
    }
}
