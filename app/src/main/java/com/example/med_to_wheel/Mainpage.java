package com.example.med_to_wheel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainpage extends AppCompatActivity {
    Button highs,medium,lows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        highs=(Button)findViewById(R.id.high);
        medium=(Button)findViewById(R.id.med);
        lows=(Button)findViewById(R.id.low);
        highs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainpage.this,MapsActivity.class);
                i.putExtra("value","critical High situation Ambulance required");
                startActivity(i);

            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainpage.this,MapsActivity.class);
                i.putExtra("value","critical medium Risk");
                startActivity(i);

            }
        });
        lows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mainpage.this,MapsActivity.class);
                i.putExtra("value","Low situation");
                startActivity(i);

            }
        });
    }
}
