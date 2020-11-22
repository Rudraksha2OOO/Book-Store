package com.example.libsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class UserInfo extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7;
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1,b2,b3;
    DatabaseHelper mydb1;
    Session session = null;
    ProgressDialog pDialog = null;
    Context context = null;
    String rec, subject, textMessage, firstname,lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        t1=findViewById(R.id.tvuserid);
        t2=findViewById(R.id.tvfname);
        t3=findViewById(R.id.tvlname);
        t4=findViewById(R.id.tvcno);
        t5=findViewById(R.id.tvemail);
        t6=findViewById(R.id.tvuname);
        t7=findViewById(R.id.tvpasswd);
        e1=findViewById(R.id.eduserid);
        e2=findViewById(R.id.edfname);
        e3=findViewById(R.id.edlname);
        e4=findViewById(R.id.edcno);
        e5=findViewById(R.id.edemail);
        e6=findViewById(R.id.eduname);
        e7=findViewById(R.id.edpasswd);
        b1=findViewById(R.id.btnuback);
        b2=findViewById(R.id.btnusubmit);
        b3=findViewById(R.id.btnuclear);
        mydb1 = new DatabaseHelper(this);
        context = this;

        t1.setVisibility(View.INVISIBLE);
        e1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        t4.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        e5.setVisibility(View.INVISIBLE);
        t6.setVisibility(View.INVISIBLE);
        e6.setVisibility(View.INVISIBLE);
        t7.setVisibility(View.INVISIBLE);
        e7.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_info,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.searchuser:
                t1.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e2.setEnabled(false);
                t3.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
                e3.setEnabled(false);
                t4.setVisibility(View.VISIBLE);
                e4.setVisibility(View.VISIBLE);
                e4.setEnabled(false);
                t5.setVisibility(View.VISIBLE);
                e5.setVisibility(View.VISIBLE);
                e5.setEnabled(false);
                t6.setVisibility(View.VISIBLE);
                e6.setVisibility(View.VISIBLE);
                e6.setEnabled(false);
                t7.setVisibility(View.VISIBLE);
                e7.setVisibility(View.VISIBLE);
                e7.setEnabled(false);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cs = mydb1.searchusers(e1.getText().toString());
                        if (cs.getCount()==0)
                        {
                            Toast.makeText(getApplicationContext(),"Sorry, No Record Found",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            while (cs.moveToNext())
                            {
                                // e1.setText(cs.getString(0));
                                e2.setText(cs.getString(1));
                                e3.setText(cs.getString(2));
                                e4.setText(cs.getString(3));
                                e5.setText(cs.getString(4));
                                e6.setText(cs.getString(5));
                                e7.setText(cs.getString(6));
                            }
                        }
                    }
                });
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent itAdminOp = new Intent(getApplicationContext(),AdminOperations.class);
                        startActivity(itAdminOp);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        e6.setText("");
                        e7.setText("");
                    }
                });
                return true;
            case R.id.removeuser:
                t1.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                e1.setEnabled(true);
                t2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e2.setEnabled(false);
                t3.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
                e3.setEnabled(false);
                t4.setVisibility(View.VISIBLE);
                e4.setVisibility(View.VISIBLE);
                e4.setEnabled(false);
                t5.setVisibility(View.VISIBLE);
                e5.setVisibility(View.VISIBLE);
                e5.setEnabled(false);
                t6.setVisibility(View.VISIBLE);
                e6.setVisibility(View.VISIBLE);
                e6.setEnabled(false);
                t7.setVisibility(View.VISIBLE);
                e7.setVisibility(View.VISIBLE);
                e7.setEnabled(false);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int c = mydb1.deleteDatausers(e1.getText().toString());
                        if(c>0)
                        {
                            rec = e5.getText().toString();
                            firstname = e2.getText().toString();
                            lastname = e3.getText().toString();
                            subject = "Account Deleted";
                            textMessage = "Dear "+firstname+"\t"+lastname+"\n"+"\nYour profile was deleted";

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
                            pDialog = ProgressDialog.show(context, "Please Wait", "Deleting...", true);
                            UserInfo.RetreiveFeedTask task = new UserInfo.RetreiveFeedTask();
                            task.execute();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Sorry, Record Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent itAdminOp = new Intent(getApplicationContext(),AdminOperations.class);
                        startActivity(itAdminOp);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        e6.setText("");
                        e7.setText("");
                    }
                });
                return true;
            case R.id.viewalluser:
                t1.setVisibility(View.INVISIBLE);
                e1.setVisibility(View.INVISIBLE);
                t2.setVisibility(View.INVISIBLE);
                e2.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.INVISIBLE);
                e3.setVisibility(View.INVISIBLE);
                t4.setVisibility(View.INVISIBLE);
                e4.setVisibility(View.INVISIBLE);
                t5.setVisibility(View.INVISIBLE);
                e5.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                Cursor cr = mydb1.getAllDatausers();
                if (cr.getCount()==0){Toast.makeText(getApplicationContext(),"No Record Found..!!",Toast.LENGTH_SHORT).show();}
                else
                {
                    StringBuffer bf = new StringBuffer();
                    while (cr.moveToNext())
                    {
                        bf.append("User Id : "+cr.getString(0)+"\n");
                        bf.append("First Name : "+cr.getString(1)+"\n");
                        bf.append("Last Name : "+cr.getString(2)+"\n");
                        bf.append("Contact No. : "+cr.getString(3)+"\n");
                        bf.append("Email ID : "+cr.getString(4)+"\n");
                        bf.append("Username : "+cr.getString(5)+"\n");
                        bf.append("Password : "+cr.getString(6)+"\n");
                    }showMessage1("All Users : ",bf.toString());
                }
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent itAdminOp = new Intent(getApplicationContext(),AdminOperations.class);
                        startActivity(itAdminOp);
                    }
                });
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void showMessage1(String title,String message)
    {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setCancelable(true);
        ab.setTitle(title);
        ab.setMessage(message);
        ab.show();
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
            Toast.makeText(getApplicationContext(),"Account Deleted Successfully..!!",Toast.LENGTH_SHORT).show();
        }
    }
}
