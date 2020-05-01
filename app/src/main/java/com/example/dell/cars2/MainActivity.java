package com.example.dell.cars2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
private DatabaseReference dg;
private  Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
         b1=(Button)findViewById(R.id.park);
        b2=(Button)findViewById(R.id.parked);
         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
              intentLogin();
             }
         });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentRegister();
            }
        });

            }
            public void intentLogin()
            {
                Intent intent=new Intent(this,Login_User.class);
                startActivity(intent);
            }

    public void intentRegister()
    {
        dg= FirebaseDatabase.getInstance().getReference("users");
        Intent intent=new Intent(this,Registration.class);

        startActivity(intent);
    }


    public void xyz(View view) {
    }
}

