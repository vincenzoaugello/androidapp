package com.example.enzofisso.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttConn = (Button) findViewById(R.id.buttConnect);
        buttConn.setOnClickListener(cl -> {
            Intent intConn = new Intent(this, ConnectActivity.class);
            String stIpAddr = ((TextView)findViewById(R.id.editAddr)).getText().toString();
            String stPort = ((TextView)findViewById(R.id.editPort)).getText().toString();
            intConn.putExtra("ipAddr", stIpAddr);
            intConn.putExtra("port", stPort);
            startActivity(intConn);
        });
    }
}
