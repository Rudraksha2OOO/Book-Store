package com.example.libsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserProfile extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1,b2,b3;
    TextView t1,t2,t3,t4,t5;
    DatabaseHelper mydb3;
    Session session = null;
    ProgressDialog pDialog = null;
    Context context = null;
    String rec, subject, textMessage, firstname,lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        context = this;
        t1=findViewById(R.id.tuid);
        t2=findViewById(R.id.tufirstname);
        t3=findViewById(R.id.tulastname);
        t4=findViewById(R.id.tucontactno);
        t5=findViewById(R.id.tuemailid);
        e1=findViewById(R.id.eduid);
        e2=findViewById(R.id.edufirstname);
        e3=findViewById(R.id.edulastname);
        e4=findViewById(R.id.educontactno);
        e5=findViewById(R.id.eduemailid);
        e6=findViewById(R.id.eduuname);
        e7=findViewById(R.id.edupasswd);
        b1=findViewById(R.id.btnupback);
        b2=findViewById(R.id.btnupsubmit);
        b3=findViewById(R.id.btnupclear);
        mydb3 = new DatabaseHelper(this);

        t1.setVisibility(View.INVISIBLE);
        e1.setVisibility(View.INVISIBLE);
        e1.setEnabled(false);
        t2.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        t4.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        e5.setVisibility(View.INVISIBLE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itHp = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(itHp);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean chkup = mydb3.updateDatausers(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),
                        e5.getText().toString(),e6.getText().toString(),e7.getText().toString());
                if(chkup==true)
                {
                    rec = e5.getText().toString();
                    firstname = e2.getText().toString();
                    lastname = e3.getText().toString();
                    subject = "Profile Updated";
                    textMessage = "Dear "+firstname+"\t"+lastname+"\n"+"\nYour profile was updated successfully"
                            +"\nFor further Details, visit our application.";

                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");

                    session = Session.getDefaultInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {

                            return new PasswordAuthentication("infoprojectemail2@gmail.com", "#cec@mvlu");

                        }
                    });
                    pDialog = ProgressDialog.show(context, "Please Wait", "Updating...", true);
                    UserProfile.RetreiveFeedTask task = new UserProfile.RetreiveFeedTask();
                    task.execute();
                }
                else
                {Toast.makeText(getApplicationContext(),"Sorry, Profile Not Updated",Toast.LENGTH_SHORT).show();}
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs = mydb3.searchuser(e6.getText().toString(),e7.getText().toString());
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
                        t1.setVisibility(View.VISIBLE);
                        e1.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                        e2.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);
                        e3.setVisibility(View.VISIBLE);
                        t4.setVisibility(View.VISIBLE);
                        e4.setVisibility(View.VISIBLE);
                        t5.setVisibility(View.VISIBLE);
                        e5.setVisibility(View.VISIBLE);
                        e1.setText(cs.getString(0));
                        e2.setText(cs.getString(1));
                        e3.setText(cs.getString(2));
                        e4.setText(cs.getString(3));
                        e5.setText(cs.getString(4));
                        e6.setText(cs.getString(5));
                        e7.setText(cs.getString(6));
                        b3.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }
    class RetreiveFeedTask extends AsyncTask<String,Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("infoprojectemail2@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage,"text/html; charset=utf-8");
                Transport.send(message);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
            e7.setText("");
            Toast.makeText(getApplicationContext(),"Profile Updated Successfully..!!",Toast.LENGTH_SHORT).show();
        }
    }
}
