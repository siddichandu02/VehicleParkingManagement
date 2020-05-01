package com.example.dell.cars2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Success_Reg extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.success_reg);


        TextView t3=(TextView)findViewById(R.id.suc);
        Bundle in9=getIntent().getExtras();
        t3.setText("Succesfully booked.\n Your id is "+in9.getString("id")+"\nYour slot no is "+in9.getString("slot_no"));

        Toast.makeText(getApplicationContext(),in9.getString("slot1"),Toast.LENGTH_LONG);

        Button be=(Button)findViewById(R.id.home);
        be.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent8();
            }
        });

    }
    public void intent8()
    {
        Intent in8=new Intent(this,MainActivity.class);
        startActivity(in8);
    }
}
