package com.example.airways;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {
     Button book_ticket,check_status,cancel_ticket,update_ticket,update_profile,logout;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name=findViewById(R.id.textView);
        book_ticket=(Button)findViewById(R.id.button1);
        check_status=(Button)findViewById(R.id.button2);
        cancel_ticket=(Button)findViewById(R.id.btfaredetail);
        update_ticket=(Button)findViewById(R.id.button4);
        update_profile=(Button)findViewById(R.id.btbookticket);
        logout=(Button)findViewById(R.id.btsearchanother);

            Intent intent=getIntent();
            final String username=intent.getStringExtra("NAME");
            final String password=intent.getStringExtra("PASSWORD");
            final String email=intent.getStringExtra("EMAIL");
            final String phone=intent.getStringExtra("PHONE");
            name.setText("welcome  "+username);


        book_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent int1=new Intent(getApplicationContext(),TicketBookingActivity.class);
               int1.putExtra("PHONE",phone);
                startActivity(int1);

            }
        });
        check_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),CheckStatusActivity.class);
                intent1.putExtra("NAME",username);
                intent1.putExtra("PHONE", phone);
                startActivity(intent1);
            }
        });
        cancel_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(),CancelActivity.class);
                intent3.putExtra("NAME",username);
                intent3.putExtra("PHONE", phone);
                startActivity(intent3);

            }
        });
        update_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "please book a flight first", Toast.LENGTH_SHORT).show();
            }
        });

        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UpadateProfileActivity.class);
                intent.putExtra("NAME",username);
                intent.putExtra("EMAIL",email);

                intent.putExtra("PASSWORD",password);
                intent.putExtra("PHONE", phone);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int6=new Intent(UserActivity.this,StartActivity.class);
                startActivity(int6);

            }
        });

    }


}
