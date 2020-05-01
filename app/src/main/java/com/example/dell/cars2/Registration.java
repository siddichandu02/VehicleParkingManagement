package com.example.dell.cars2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Registration extends AppCompatActivity {
    private DatabaseReference mDatabase,mDatabse5,dg;
    private Button sub;
    private EditText use, email_id, pass, car;
    private TextView txt,td;
    private String id,sp1;
    private String f_slots,t_slots,slots,slot_no;
    private Spinner sp;
    private int i1,flag1=0;
private String d3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        sub = (Button) findViewById(R.id.sunmit);
        use = (EditText) findViewById(R.id.name);
        email_id = (EditText) findViewById(R.id.mail);
         td=(TextView)findViewById(R.id.empty);
        dg= FirebaseDatabase.getInstance().getReference("users");

        pass = (EditText) findViewById(R.id.password);
        car = (EditText) findViewById(R.id.car_no);

        txt=(TextView) findViewById(R.id.txt1);

sp=(Spinner)findViewById(R.id.type);
        sp1=String.valueOf(sp.getSelectedItem());
        SimpleDateFormat f=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        d3=f.format(new Date());
        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try
                {
                    mDatabse5= FirebaseDatabase.getInstance().getReference("admin");

                    mDatabse5.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                        //    Toast.makeText(getApplicationContext(), "ewsdx", Toast.LENGTH_LONG).show();

                            if (dataSnapshot.exists()) {
                                 f_slots = dataSnapshot.child("f_slots").getValue().toString();
                                t_slots = dataSnapshot.child("t_slots").getValue().toString();
                                slots=dataSnapshot.child("slots").getValue().toString();
                                switch (sp1) {
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



                                if(i1>(Integer.parseInt(t_slots)-Integer.parseInt(f_slots)))
                                {
                                    td.setText("Not enough slots are available");
                                    flag1=1;
                                }
                                StringBuilder sb=new StringBuilder(slots);
                                for(int h=0;h<slots.length();h++)
                                {int flg=0;
                                    if(slots.charAt(h)=='0')
                                    {
                                        for(int g=h;g<slots.length()&&g<i1+h;g++)
                                        {
                                            if(slots.charAt(g)!='0')
                                            {  flg=1;
                                                break;

                                            }

                                        }
                                        if(flg==0)
                                        {
                                            slot_no=h+1+"";
                                            break;
                                        }
                                    }
                                }
                                for (int j = Integer.parseInt(slot_no);j<=i1;j++)
                                {
                                    sb.setCharAt(j,'1');
                                }
                                slots=sb.toString();
                                Toast.makeText(getApplicationContext(),slots,Toast.LENGTH_LONG);


                            }
                            else {
                                Toast.makeText(getApplicationContext(), "else part", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    if (flag1==0) {

                        Random random = new Random();
                        id = car.getText().toString() + String.format("%04d", random.nextInt(10000));
                        //   Toast.makeText(getApplicationContext(),sp1,Toast.LENGTH_LONG);

                        writeNewUser(id, use.getText() + "", email_id.getText() + "", pass.getText() + "", car.getText() + "", sp1);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.fillInStackTrace().toString()+"hello",Toast.LENGTH_LONG).show();
                }



            }
        });




    }
    private void writeNewUser(String user_ID,String username, String email, String password,String car_no,String typ) {
       try {

           mDatabase = FirebaseDatabase.getInstance().getReference();

           User user = new User(username, email, password, car_no, typ, d3);

           mDatabase.child("users").child(user_ID).setValue(user);
          // txt.setText("siddi");


           int f = Integer.parseInt(f_slots) + i1;

           // int t=Integer.parseInt(t_slots)+1;
           mDatabase.child("admin").child("f_slots").setValue(f + "");
          // mDatabase.child("admin").child("t_slots").setValue(t+"");
            intent7();
         }
       catch (Exception e)
       {
           Toast.makeText(getApplicationContext(),e.toString()+"ffff",Toast.LENGTH_LONG);
       }

    }
    public void intent7()
    { //txt.setText("dvz");
try {
    mDatabase.child("admin").child("slots").setValue(slots);
    Intent in7 = new Intent(this, Success_Reg.class);
    in7.putExtra("id", id);
    in7.putExtra("slot_no",slot_no);
    in7.putExtra("slot1",slots);
    startActivity(in7);
}
catch (Exception e)
{
    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG);

}
    }
}

