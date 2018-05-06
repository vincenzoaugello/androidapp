package com.example.enzofisso.androidapp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class MySocket {
    private static MySocket instance;
    private static Socket sock;

    public static MySocket getInstance() {
        if(instance == null)
            instance = new MySocket();
        return instance;
    }

    private MySocket() {
        sock = null;
    }

    public Socket getSocket() {
        return sock;
    }

    private boolean isNull() {
        return (sock == null);
    }

    public boolean isConnected() {
        return (!isNull() && sock.isConnected());
    }

    public int connect(String ip, int port) {
        if(isConnected())return -2;
        Thread threadConn = new Thread(() -> {
            try {
                SocketAddress sockaddr = new InetSocketAddress(ip, port);
                sock = new Socket();
                sock.connect(sockaddr, 5000);
            } catch (UnknownHostException e) {
                sock = null;
            } catch (SocketTimeoutException e) {
                sock = null;
            } catch (IOException e) {
                sock = null;
            }
        });
        threadConn.start();
        try {
            threadConn.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isConnected())return 0;
        return -1;
    }
}
