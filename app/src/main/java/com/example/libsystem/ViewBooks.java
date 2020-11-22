package com.example.libsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ViewBooks extends AppCompatActivity  {

    DatabaseHelper mydb;
    Button b1;
    Session session = null;
    ProgressDialog pDialog = null;
    Context context = null;
    String bookname="",author="",cat="",price="";
    String rec, subject, textMessage, firstname,lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewbooks_layout);//viewbooks_layout OR activity_view_books

        final ListView listView = findViewById(R.id.listView);
        b1=findViewById(R.id.btnvbback);
        mydb=new DatabaseHelper(this);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itHp = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(itHp);
            }
        });

        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = mydb.getListContents();
        if (data.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No Book Available :(",Toast.LENGTH_SHORT).show();
        }else
            {
                while (data.moveToNext())
                {
                    theList.add(data.getString(1));
                    ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                    listView.setAdapter(listAdapter);
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedFromList = (String) listView.getItemAtPosition(position);

                            Cursor cs = mydb.getBookDetails(selectedFromList);
                            if (cs.getCount()==0)
                            {
                                Toast.makeText(getApplicationContext(),"Sorry, No Record Found",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                while (cs.moveToNext())
                                {
                                    bookname=cs.getString(1);
                                    author=cs.getString(2);
                                    cat=cs.getString(3);
                                    price=cs.getString(4);
                                }
                            }
                            AlertDialog.Builder ab = new AlertDialog.Builder(ViewBooks.this);
                            ab.setTitle("Details");
                            ab.setMessage("Book Name : "+selectedFromList+"\n"+"Book Author : "+author+"\n"+"Book Category : "+cat+"\n"+"Book Price : "+price)
                                    .setCancelable(false)
                                    .setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            /*Toast.makeText(getApplicationContext(),"Purchase",Toast.LENGTH_SHORT).show();*/
                                            Bundle bundle = getIntent().getExtras();
                                            String message = bundle.getString("key");
                                            Cursor cs = mydb.getUserDetails(message);
                                            while (cs.moveToNext())
                                            {
                                                firstname=cs.getString(1);
                                                lastname=cs.getString(2);
                                                rec=cs.getString(4);
                                            }
                                            subject = "Book Purchased";
                                            textMessage = "Dear "+firstname+"\t"+lastname+"\n"+"\nYou purchased "+bookname+
                                                    "/nFollowing are the book details : "+
                                                    "/nBook Name : "+bookname+
                                                    "/nBook Author : "+author +
                                                    "/nCategory : "+cat+
                                                    "/nBook price : "+price;

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
                                            pDialog = ProgressDialog.show(ViewBooks.this, "Please Wait", "Processing...", true);
                                            ViewBooks.RetreiveFeedTask task = new ViewBooks.RetreiveFeedTask();
                                            task.execute();
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog ad = ab.create();
                            ad.show();
                            return true;
                        }
                    });

                }
            }

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
            Toast.makeText(getApplicationContext(),"Book Purchased..!!",Toast.LENGTH_SHORT).show();
        }
    }
}
