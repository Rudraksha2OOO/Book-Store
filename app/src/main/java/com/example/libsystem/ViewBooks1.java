package com.example.libsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewBooks1 extends AppCompatActivity {

    DatabaseHelper mydb;
    Button b1;
    TextView tv = findViewById(R.id.tv1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books1);

        ListView listView = findViewById(R.id.listView);
        //b1=findViewById(R.id.btnvbback);
        mydb=new DatabaseHelper(this);

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
                        Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();

                        return true;

                    }
                });

            }
        }

    }
}
