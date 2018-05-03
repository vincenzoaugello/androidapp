package com.example.enzofisso.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        Intent intConn = getIntent();
        String stIpAddr = intConn.getStringExtra("ipAddr");
        String stPort = intConn.getStringExtra("port");

        MakeConnection(stIpAddr, Integer.valueOf(stPort));
    }

    private void MakeConnection(String ip, int port) {
        Socket servSock = null;
        try {
            servSock = new Socket(ip, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
