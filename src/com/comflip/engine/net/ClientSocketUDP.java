package com.comflip.engine.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class ClientSocketUDP {
    private DatagramSocket clientSocket;
    private final byte[] buf = new byte[256];

    private String ip;
    private int port;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new DatagramSocket();
        this.ip = ip;
        this.port = port;
    }

    public String sendMessage(String msg) throws IOException {
        clientSocket.setSoTimeout(10000);

        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName(this.ip), this.port);
        clientSocket.send(packet);

        packet = new DatagramPacket(buf, buf.length);
        clientSocket.receive(packet);

        return new String(packet.getData(), 0, packet.getLength());
    }

    public void stopConnection() throws IOException {
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
