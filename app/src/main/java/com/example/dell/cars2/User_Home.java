package com.example.dell.cars2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User_Home extends AppCompatActivity {
    private Button cb,pb;
    DatabaseReference mDatabase2,mDatabase3;
    private String in,type,use,f_slots,profit;
    private  long sum=0,hrs=0;
    private Session session;private TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);
        t1=(TextView)findViewById(R.id.c_bill_txt);
        session=new Session(this);
        use=session.getusename();

cb=(Button)findViewById(R.id.c_bill);
pb=(Button)findViewById(R.id.p_bill);
        cb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            mDatabase2 = FirebaseDatabase.getInstance().getReference("users").child(use);
            mDatabase2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        in = dataSnapshot.child("intime").getValue().toString();
                    type=dataSnapshot.child("type").getValue().toString();
                        sum=calculate(in,type);
                        t1.setText("No. of minutes = "+hrs+" \nYour current bill is:Rs."+sum);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.fillInStackTrace()+"",Toast.LENGTH_LONG).show();
        }



    }
});
        //exit button
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mDatabase2 = FirebaseDatabase.getInstance().getReference("users").child(use);

                    mDatabase2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                in = dataSnapshot.child("intime").getValue().toString();
                                type=dataSnapshot.child("type").getValue().toString();

                                if(sum==0)
                                    sum=calculate(in,type);
                               intent5();

                               // Toast.makeText(getApplicationContext(),"Your paybill bill is : "+sum,Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });




                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.fillInStackTrace()+"",Toast.LENGTH_LONG).show();
                }



            }
        });

    }
    public void intent5()
    {
        Intent in5=new Intent(this,Payment_User.class);
        in5.putExtra("bill",sum+"");
        in5.putExtra("hours",hrs+"");
in5.putExtra("spl",type+"");
        startActivity(in5);
    }
    public long calculate(String in1,String type1)
    {long sum1=0;
        try {
            SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");


            Date intime = f.parse(in1);
            String strDate = f.format(new Date());
            Date outtime = f.parse(strDate);

            long diff = outtime.getTime() - intime.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

//            if (diffMinutes > 30)
//                diffHours++;
//            if (diffHours == 0)
//                diffHours++;
hrs=diffMinutes;
            switch (type1) {
                case "car":
                    sum1 = sum1 + (2 * diffMinutes);
                    break;
                case "truck":
                    sum1 = sum1 + (3 * diffMinutes);
                    break;
                case "bike":
                    sum1 = sum1 + (1 * diffMinutes);
                    break;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.fillInStackTrace().toString(),Toast.LENGTH_LONG);
        }

         return sum1;
    }

}
