package com.example.libsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.ad:
                Intent it1 = new Intent(HomeActivity.this,AdminLogin1.class);
                startActivity(it1);
                return true;
            case R.id.reg:
                Intent it2 = new Intent(HomeActivity.this,UserRegisteration.class);
                startActivity(it2);
                return true;
            case R.id.up:
                Intent it3 = new Intent(HomeActivity.this,UserProfile.class);
                startActivity(it3);
                return true;
            case R.id.vb:
                Intent it4 = new Intent(HomeActivity.this,UserLogin.class);
                startActivity(it4);
                return true;
            case R.id.about:
               Intent it5 = new Intent(HomeActivity.this,AboutForm.class);
                startActivity(it5);
               return true;
            case R.id.exit:
                AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);
                ab.setTitle("EXIT");
                ab.setMessage("Do you want to exit Application?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                //System.exit(0);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog ad = ab.create();
                ad.show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
