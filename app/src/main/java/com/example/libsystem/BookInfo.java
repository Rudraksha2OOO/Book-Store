package com.example.libsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BookInfo extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;
    EditText e1,e2,e3,e4,e5,e8;
    Button b1,b2,b3;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        t1=findViewById(R.id.tvbookid);
        t2=findViewById(R.id.tvbookname);
        t3=findViewById(R.id.tvauthor);
        t4=findViewById(R.id.tvcategory);
        t5=findViewById(R.id.tvprice);
        e1=findViewById(R.id.edbookid);
        e2=findViewById(R.id.edbookname);
        e3=findViewById(R.id.edauthor);
        e4=findViewById(R.id.edcategory);
        e5=findViewById(R.id.edprice);
        b1=findViewById(R.id.btnbback);
        b2=findViewById(R.id.btnbsubmit);
        b3=findViewById(R.id.btnbclear);
        mydb = new DatabaseHelper(this);


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
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.book_info,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.addbook:
                t1.setVisibility(View.INVISIBLE);
                e1.setVisibility(View.INVISIBLE);
                t2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                e4.setVisibility(View.VISIBLE);
                t5.setVisibility(View.VISIBLE);
                e5.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean chk = mydb.insertDatabooks(e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),
                                e5.getText().toString());
                        if(chk==true)
                        {Toast.makeText(getApplicationContext(),"Record Inserted Successfully",Toast.LENGTH_SHORT).show();}
                        else
                            {Toast.makeText(getApplicationContext(),"Sorry, Record Not Inserted",Toast.LENGTH_SHORT).show();}
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
                    }
                });
                return true;
            case R.id.searchbook:
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
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cs = mydb.searchbooks(e1.getText().toString());
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
                    }
                });
                return true;
            case R.id.editbook:
                t1.setVisibility(View.INVISIBLE);
                e1.setVisibility(View.INVISIBLE);
                t2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e2.setEnabled(true);
                t3.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
                e3.setEnabled(true);
                t4.setVisibility(View.VISIBLE);
                e4.setVisibility(View.VISIBLE);
                e4.setEnabled(true);
                t5.setVisibility(View.VISIBLE);
                e5.setVisibility(View.VISIBLE);
                e5.setEnabled(true);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean ch = mydb.updateDatabooks(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),
                                e5.getText().toString());
                        if(ch==true)
                        {Toast.makeText(getApplicationContext(),"Record Updated Successfully",Toast.LENGTH_SHORT).show();}
                        else
                        {Toast.makeText(getApplicationContext(),"Sorry, Record Not Updated",Toast.LENGTH_SHORT).show();}
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
                    }
                });
                return true;
            case R.id.removebook:
                t1.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.INVISIBLE);
                e2.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.INVISIBLE);
                e3.setVisibility(View.INVISIBLE);
                t4.setVisibility(View.INVISIBLE);
                e4.setVisibility(View.INVISIBLE);
                t5.setVisibility(View.INVISIBLE);
                e5.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int c = mydb.deleteDatabooks(e1.getText().toString());
                        if(c>0)
                            Toast.makeText(getApplicationContext(),"Record Deleted Successfully",Toast.LENGTH_SHORT).show();
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
                    }
                });
                return true;
            case R.id.viewallbook:
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
                Cursor cr = mydb.getAllDatabooks();
                if (cr.getCount()==0){Toast.makeText(getApplicationContext(),"No Record Found..!!",Toast.LENGTH_SHORT).show();}
                else
                    {
                        StringBuffer bf = new StringBuffer();
                        while (cr.moveToNext())
                        {
                            bf.append("Book Id : "+cr.getString(0)+"\n");
                            bf.append("Book Name : "+cr.getString(1)+"\n");
                            bf.append("Book Author : "+cr.getString(2)+"\n");
                            bf.append("Book Category : "+cr.getString(3)+"\n");
                            bf.append("Book Price : "+cr.getString(4)+"\n");
                        }showMessage("All Books : ",bf.toString());
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
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setCancelable(true);
        ab.setTitle(title);
        ab.setMessage(message);
        ab.show();
    }
}
