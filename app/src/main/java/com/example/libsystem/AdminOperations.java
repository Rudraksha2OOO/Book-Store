package com.example.libsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class AdminOperations extends AppCompatActivity {

    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_operations);
        b1=findViewById(R.id.btnbookinfo);
        b2=findViewById(R.id.btnuserinfo);
        b3=findViewById(R.id.bback);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbookinfo = new Intent(getApplicationContext(),BookInfo.class);
                startActivity(intentbookinfo);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 = new Intent(AdminOperations.this,UserInfo.class);
                startActivity(it2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it3 = new Intent(AdminOperations.this,HomeActivity.class);
                startActivity(it3);
            }
        });

    }
}
