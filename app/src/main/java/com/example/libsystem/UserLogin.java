package com.example.libsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserLogin extends AppCompatActivity {

    EditText e1,e2,eup1,eup2,eup3,eup4,eup5,eup6,eup7;
    Button b1,b2,b3;
    DatabaseHelper mydb4;
    ProgressDialog pDialog = null;
    Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mydb4 = new DatabaseHelper(this);
        context = this;
        e1 = findViewById(R.id.eduluname);
        e2 = findViewById(R.id.edulpass);
        eup1=findViewById(R.id.eduid);
        eup2=findViewById(R.id.edufirstname);
        eup3=findViewById(R.id.edulastname);
        eup4=findViewById(R.id.educontactno);
        eup5=findViewById(R.id.eduemailid);
        eup6=findViewById(R.id.eduuname);
        eup7=findViewById(R.id.edupasswd);
        b1 = findViewById(R.id.btnulsubmit);
        b2 = findViewById(R.id.btnulback);
        b3 = findViewById(R.id.btnulclear);
        pDialog=new ProgressDialog(UserLogin.this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs = mydb4.searchuser(e1.getText().toString(),e2.getText().toString());
                if (cs.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"Sorry, No Record Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    while (cs.moveToNext())
                    {
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        pDialog.setTitle("Please Wait");
                        pDialog.setMessage("Processing..");
                        pDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.dismiss();
                                Intent itview = new Intent(UserLogin.this,ViewBooks.class);
                                itview.putExtra("key",e1.getText().toString());
                                startActivity(itview);
                                finish();
                            }
                        },1000);
                       /* Intent myIntent = new Intent(UserLogin.this, SecondActivity.class);
                        myIntent.putExtra("key", e1.getText().toString());
                        startActivity(myIntent);*/
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itHp = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(itHp);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e2.setText("");
                e1.setText("");
            }
        });

    }

}

