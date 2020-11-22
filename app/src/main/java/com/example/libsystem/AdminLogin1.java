package com.example.libsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin1 extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login1);
        e1=findViewById(R.id.edaduname);
        e2=findViewById(R.id.edadpass);
        b1=findViewById(R.id.btnsubmit);
        b2=findViewById(R.id.btnback);
        b3=findViewById(R.id.btnclear);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname,pass;
                uname=e1.getText().toString();
                pass=e2.getText().toString();

                if(uname.equalsIgnoreCase("admin")&&pass.equalsIgnoreCase("admin"))
                {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(AdminLogin1.this,AdminOperations.class);
                    startActivity(it);
                }
                else
                    {
                        Toast.makeText(getApplicationContext(),"Sorry, Login Unsuccessful\nTry Again..!!",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AdminLogin1.this,HomeActivity.class);
                startActivity(it);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e2.setText("");
            }
        });
    }
}
