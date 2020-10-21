package com.example.airways;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CancelBookedTicket extends AppCompatActivity {
    TextView pnr;
    TextView flight;
    TextView From;
    TextView To;
    TextView Date;
    TextView Clas;
    TextView adult;
    TextView child;
    TextView contact,Amount,number;
    Button Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_booked_ticket);
        pnr=findViewById(R.id.PNR);
        flight=findViewById(R.id.FLIGHTfrom);
        From=findViewById(R.id.FLIGHTfrom);
        To=findViewById(R.id.TO);
        Date=findViewById(R.id.DATE);
        Clas=findViewById(R.id.CLASS);
        adult=findViewById(R.id.ADULT);
        child=findViewById(R.id.CHILD);
        contact=findViewById(R.id.CONTACT);
        Amount=findViewById(R.id.amount);
        number=findViewById(R.id.FLIGHTno);
        Cancel=findViewById(R.id.cancel);
        pnr=findViewById(R.id.PNR);
        Intent intent=getIntent();
        final String name=intent.getStringExtra("NAME");
        final String from=intent.getStringExtra("FROM");
        final String to=intent.getStringExtra("TO");
        final String flightnumber=intent.getStringExtra("NUMBER");
        final String adultcount=intent.getStringExtra("ADULTCOUNT");
        final String childcount=intent.getStringExtra("CHILDCOUNT");
        final String pnrno=intent.getStringExtra("PNR");
        final String phone=intent.getStringExtra("PHONE");
        final String clas=intent.getStringExtra("CLASS");
        final String date=intent.getStringExtra("DATE");
        final String ammount=intent.getStringExtra("AMOUNT");
        pnr=findViewById(R.id.PNR);
        flight=findViewById(R.id.FLIGHTfrom);
        From=findViewById(R.id.FLIGHTfrom);
        To=findViewById(R.id.TO);
        Date=findViewById(R.id.DATE);
        Clas=findViewById(R.id.CLASS);
        adult=findViewById(R.id.ADULT);
        child=findViewById(R.id.CHILD);
        contact=findViewById(R.id.CONTACT);
        Amount=findViewById(R.id.amount);
        number=findViewById(R.id.FLIGHTno);
        Cancel=findViewById(R.id.cancel);
        System.out.println(name+flightnumber);

        pnr.setText(pnrno);
        flight.setText(name);
        From.setText(from);
        To.setText(to);
        Date.setText(date);
        Clas.setText(clas);
        adult.setText(adultcount);
        child.setText(childcount);
        contact.setText(phone);
        Amount.setText(ammount);
        number.setText(flightnumber);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("bookedticket");


    }
}
