package com.example.dell.cars2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_User extends AppCompatActivity  {
    private EditText u1,p;
    private Button b1;
    private DatabaseReference mDatabase1;
    private String us,ps;
    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        b1=(Button)findViewById(R.id.submit);
        u1=(EditText)findViewById(R.id.user_name);
        p=(EditText)findViewById(R.id.password1);

        session = new Session(this); //in oncreate
//and now we set sharedpreference then use this like


        b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        us=u1.getText().toString();
        ps=p.getText().toString();
        session.setusename(us);
        try {

            mDatabase1 = FirebaseDatabase.getInstance().getReference("users").child(u1.getText().toString()+"");
          //  Toast.makeText(getApplicationContext(), "ewsdx", Toast.LENGTH_LONG).show();

            mDatabase1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   // Toast.makeText(getApplicationContext(), "ewsdx", Toast.LENGTH_LONG).show();

                    if (dataSnapshot.exists()) {
                        String s = dataSnapshot.child("password").getValue().toString();
                       // intent2();
                       // Toast.makeText(getApplicationContext(), ps, Toast.LENGTH_LONG).show();

                        if(s.equals(ps))
                        {

                            intent2();
                        }
                        else
                        {
                           Toast.makeText(getApplicationContext(), "Please enter correct password", Toast.LENGTH_LONG).show();
                        p.setText("");
                        }
                        }
                        else {
                        Toast.makeText(getApplicationContext(), "User id not found", Toast.LENGTH_LONG).show();
                        u1.setText("");
                        p.setText("");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
           // FirebaseDatabase.getInstance().goOffline();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
});


               // User user ;

              //  mDatabase1.child("users").child("123").getValue();
               // txt.setText("siddi");
              //  intent2();




    }
    public void intent2()
    {

        Intent intent3=new Intent(this,User_Home.class);
        intent3.putExtra("user_N",us);
        startActivity(intent3);

    }


}
