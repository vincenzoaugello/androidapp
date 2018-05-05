package com.example.enzofisso.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttConn = findViewById(R.id.buttConnect);
        buttConn.setOnClickListener(c -> onClickConnect());
    }

    private void onClickConnect() {
        String ipAddr; int port;

        try {
            ipAddr = ((TextView)findViewById(R.id.editAddr)).getText().toString();
            port = Integer.valueOf(((TextView)findViewById(R.id.editPort)).getText().toString());
        } catch(NumberFormatException e) {
            ipAddr = "";
            port = -1;
        }
        if(port == -1)return;

        Socket sock = new MySocket(ipAddr, port).getSocket();

        if(sock != null) {
            Toast.makeText(getApplicationContext(),"Connessione avvenuta con successo", Toast.LENGTH_SHORT).show();
                /*Intent intConn = new Intent(this, ConnectActivity.class);
                intConn.putExtra("ipAddr", stIpAddr);
                intConn.putExtra("port", stPort);
                startActivity(intConn);*/
        }

        else Toast.makeText(getApplicationContext(),"Connessione fallita", Toast.LENGTH_SHORT).show();
    }

    class MySocket {
        Socket sock;

        MySocket(String ip, int port) {
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
            try {threadConn.join();}
            catch (InterruptedException e) {e.printStackTrace();}
        }

        Socket getSocket() {
            return sock;
        }
    }
}
