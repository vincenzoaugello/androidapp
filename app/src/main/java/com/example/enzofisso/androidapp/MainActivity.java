package com.example.enzofisso.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        if(port == -1 || ipAddr.equals("")) {
            Toast.makeText(getApplicationContext(), "Dati non validi", Toast.LENGTH_SHORT).show();
            return;
        }

        MySocket sock = MySocket.getInstance();
        sock.connect(ipAddr, port);

        if(sock.isConnected()) {
            Toast.makeText(getApplicationContext(),"Connessione avvenuta con successo", Toast.LENGTH_SHORT).show();
            Intent intConn = new Intent(this, ConnectActivity.class);
            startActivity(intConn);
        }

        else Toast.makeText(getApplicationContext(),"Connessione fallita", Toast.LENGTH_SHORT).show();
    }
}
