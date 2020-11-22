package com.example.libsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserRegisteration extends AppCompatActivity {

    EditText e2,e3,e4,e5,e6,e7;
    Button b1,b2,b3;
    DatabaseHelper mydb2;
    Session session = null;
    ProgressDialog pDialog = null;
    Context context = null;
    String rec, subject, textMessage, firstname,lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registeration);

        context = this;
        e2=findViewById(R.id.edfirstname);
        e3=findViewById(R.id.edlastname);
        e4=findViewById(R.id.edcontactno);
        e5=findViewById(R.id.edemailid);
        e6=findViewById(R.id.edusername);
        e7=findViewById(R.id.edpassword);
        b1=findViewById(R.id.btnurback);
        b2=findViewById(R.id.btnursubmit);
        b3=findViewById(R.id.btnurclear);
        mydb2 = new DatabaseHelper(this);

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
                if (e2.length() !=0 && e6.length() !=0 && e7.length() !=0)
                {
                    boolean chkuser = mydb2.insertDatausers(e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),
                            e5.getText().toString(),e6.getText().toString(),e7.getText().toString());
                    if(chkuser==true)
                    {
                        rec = e5.getText().toString();
                        firstname = e2.getText().toString();
                        lastname = e3.getText().toString();
                        subject = "Registration Succesfull";

                        textMessage = "Dear "+firstname+"\t"+lastname+"\n"+"\nYour have successfully registered in the Book Store Application."
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
                        pDialog = ProgressDialog.show(context, "Please Wait", "Registering...", true);
                        RetreiveFeedTask task = new RetreiveFeedTask();
                        task.execute();
                    }
                    else
                    {Toast.makeText(getApplicationContext(),"Sorry, Not Registered",Toast.LENGTH_SHORT).show();}
                }
                else
                    {Toast.makeText(getApplicationContext(),"Please insert valid data..!!",Toast.LENGTH_SHORT).show();}


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
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
            Toast.makeText(getApplicationContext(),"Registration Successful..!!",Toast.LENGTH_SHORT).show();
        }
    }
}
/*Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{e5.getText().toString()});//reciever
                        i.putExtra(Intent.EXTRA_SUBJECT, "Registration Successfull");
                        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(UserRegisteration.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }*/
