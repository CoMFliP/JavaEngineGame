package com.comflip.engine.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ClientSocket {
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

    public static HashMap<String, String> decodeResponse(String response) {
        HashMap<String, String> mapResponse = new HashMap<>();

        for (String line : response.split("&")) {
            mapResponse.put(line.split("=")[0], line.split("=")[1]);
        }

        return mapResponse;
    }
}
