package com.example.dell.cars2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment_User extends AppCompatActivity
{String f_slots,pro,spl;Bundle in6;int flag =0;
    private DatabaseReference mDatabase4,mDatabase5;
    private Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_user);
        TextView t2 = (TextView) findViewById(R.id.t_bill);
        in6 = getIntent().getExtras();
spl=in6.getString("spl");
b4=findViewById(R.id.paytm);
        t2.setText("No. of minutes = " + in6.getString("hours") + "\nPlease pay " + in6.getString("bill"));
      //  Toast.makeText(getApplicationContext(), f_slots + " " + pro, Toast.LENGTH_LONG);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appPackageName = "net.one97.paytm";
                PackageManager pm = getApplicationContext().getPackageManager();
                Intent appstart = pm.getLaunchIntentForPackage(appPackageName);
                if(null!=appstart)
                {
                    getApplicationContext().startActivity(appstart);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Install PayTm on your device", Toast.LENGTH_SHORT).show();
                }

                mDatabase5.child("profit").setValue(pro+"");
            }
        });

        try{

        mDatabase4 = FirebaseDatabase.getInstance().getReference("admin");
        //updating profit and slots in adamin
        mDatabase4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int i1=0;

                    switch (spl) {
                        case "car":
                            i1=2;
                            break;
                        case "truck":
                            i1=3;
                            break;
                        case "bike":
                            i1=1;
                            break;
                    }
                    if(dataSnapshot.child("f_slots").getValue()!=null){
                     int f1=Integer.parseInt(dataSnapshot.child("f_slots").getValue().toString())-i1;
f_slots=f1+"";}
                    long l = Long.parseLong(dataSnapshot.child("profit").getValue().toString()) + Long.parseLong(in6.getString("bill"));
                    pro = l + "";


                  if(flag==0){
    intent5(); // Toast.makeText(getApplicationContext(), "slots upated "+f_slots+"  "+pro, Toast.LENGTH_LONG).show();
                }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



        }
    catch(Exception e)

    {
Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG);
    }
    }

    public void intent5()
    {
        mDatabase5=FirebaseDatabase.getInstance().getReference("admin");
flag=1;
       mDatabase5.child("f_slots").setValue(f_slots+"");

    }
}
