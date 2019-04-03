package com.example.med_to_wheel;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class finaldatabase extends AppCompatActivity {

    HashMap<String,String>map1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaldatabase);
        map1=new HashMap<>();
        open();
       // TextView tv=(TextView)findViewById(R.id.textview);
        ImageView iv=(ImageView)findViewById(R.id.imageView);
       FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference myref = database.getReference("message");

        Bundle bundle=getIntent().getExtras();
        String fin=bundle.getString("datass");
        String[] fin1=fin.split("=");
        String str1=fin1[0];
        String loc=fin1[1];
        Random rand=new Random();
        map1.put("condition",fin1[1]);
        map1.put("Location",fin1[0]);
        settermethods ss=new settermethods(fin1[0],fin1[1]);
        myref.push().setValue(map1);
      //  tv.setText("\t\tcondition="+fin1[1]+"\n\t\t"+"Location="+fin1[0]);
        Toast.makeText(getApplicationContext(),"data send in Firebase",Toast.LENGTH_SHORT).show();




    }
    public void open()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(finaldatabase.this,R.style.AppTheme));
        builder.setMessage("Ambulance will arrive shortly");
        builder.setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("tag","ss");
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
