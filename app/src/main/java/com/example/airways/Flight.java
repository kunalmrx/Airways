package com.example.airways;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Flight extends AppCompatActivity {
    private Button FareDetails;
    private Button Book;
    TextView Flightname;
    TextView Flightno;
    TextView Source;
    TextView Destination;
    TextView Bussiness;
    TextView Economy;
    TextView FirstClass;
    TextView PremiumEconomy;
    Spinner spin;
    String selectedItemText;
    EditText Time;
    String userrole;
    FirebaseDatabase firebasedatabase,firebasedatabase2;
    FirebaseDatabase firebasedatabase3;
    DatabaseReference databaseReference,databaseReference2;
    DatabaseReference databaseReference3;

    private String[] userRoleString = new String[] { "Bussiness", "Economy","FirstClass","PremiumEconomy"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        FareDetails=findViewById(R.id.button7);
        Book=findViewById(R.id.booktkt);
        Flightname=findViewById(R.id.tv1);
        Flightno=findViewById(R.id.tv2);
        Source=findViewById(R.id.tv3);
        Destination=findViewById(R.id.tv4);
        Bussiness=findViewById(R.id.tv6);
        Economy=findViewById(R.id.tv5);
        FirstClass=findViewById(R.id.tv7);
        PremiumEconomy=findViewById(R.id.tv8);
        spin=findViewById(R.id.spinner);
        final Intent intent=getIntent();
        final String name=intent.getStringExtra("NAME");
        final String to=intent.getStringExtra("TO");
        final String from=intent.getStringExtra("FROM");
        final String number=intent.getStringExtra("NUMBER");
        final String seatE=intent.getStringExtra("SEATe");
        final String seaTb=intent.getStringExtra("SEATb");
        final String seaTfc=intent.getStringExtra("SEATfc");
        final String seatPE=intent.getStringExtra("SEATpe");
        final String phone=intent.getStringExtra("PHONE");
        //final String clas=intent.getStringExtra("CLASS");
        final String adultcount=intent.getStringExtra("ADULTCOUNT");
        final String childcount=intent.getStringExtra("CHILDCOUNT");
        String a=String.valueOf(spin.getSelectedItem());
        final String clas = null;
       // final String dayofmonth=intent.getStringExtra("DAY");
        //final String month=intent.getStringExtra("MONTH");
        //final String year=intent.getStringExtra("YEAR");
        final String date=intent.getStringExtra("DATE");
       // AdapterView.OnItemClickListener onItemClickListener = ;
       // spin.setOnItemClickListener(onItemClickListener);
        Flightname.setText(name);
        Flightno.setText(number);
        Source.setText(from);
        Destination.setText(to);
        Bussiness.setText(seaTb);
        Economy.setText(seatE);
        FirstClass.setText(seaTfc);
        PremiumEconomy.setText(seatPE);
        FareDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),FareDetail.class);
                intent1.putExtra("number",number);
                startActivity(intent1);
            }
        });
      /*  spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selectedItemText = (String) parent.getItemAtPosition(position);
            }
        });*/
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                userrole =(String) spin.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        adapter_role
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter_role);

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebasedatabase2= FirebaseDatabase.getInstance();
                databaseReference2=firebasedatabase2.getReference("flight");
                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String costofadultbussiness=dataSnapshot.child(number).child("ab").getValue(String.class);
                        String costofadulteconomy =dataSnapshot.child(number).child("ae").getValue(String.class);
                        String costofadultfirstclass=dataSnapshot.child(number).child("afc").getValue(String.class);
                        String costofadultpreeconomy=dataSnapshot.child(number).child("ape").getValue(String.class);
                        String costofchildbussiness=dataSnapshot.child(number).child("cb").getValue(String.class);
                        String costofchildeconomy=dataSnapshot.child(number).child("ce").getValue(String.class);
                        String costofchildfirstclass=dataSnapshot.child(number).child("cfc").getValue(String.class);
                        String costofchildpreeconomy=dataSnapshot.child(number).child("cpe").getValue(String.class);
                        Intent intent1=new Intent(getApplicationContext(),PaymentActivity.class);
                        intent1.putExtra("PHONE",phone);
                        intent1.putExtra("NAME",name);
                        intent1.putExtra("FLIGHTNUMBER",number);
                        intent1.putExtra("FROM",from);
                        intent1.putExtra("TO",to);
                        intent1.putExtra("CLASS",userrole);
                        intent1.putExtra("ADULTCOUNT",adultcount);
                        intent1.putExtra("CHILDCOUNT",childcount);
                        // intent1.putExtra("DAY",dayofmonth);
                        //intent1.putExtra("MONTH",month);
                        //intent1.putExtra("YEAR",year);
                        intent1.putExtra("SEATe",seatE);
                        intent1.putExtra("SEATb",seaTb);
                        intent1.putExtra("SEATfc",seaTfc);
                        intent1.putExtra("SEATpe",seatPE);
                        intent1.putExtra("COSTOFADULTBUSSINESS",costofadultbussiness);
                        intent1.putExtra("COSTOFADULTECONOMY",costofadulteconomy);
                        intent1.putExtra("COSTOFADULTFIRSTCLASS",costofadultfirstclass);
                        intent1.putExtra("COSTOFADULTPREMIUMECONOMY",costofadultpreeconomy);
                        intent1.putExtra("COSTOFCHILDBUSSINESS",costofchildbussiness);
                        intent1.putExtra("COSTOFCHILDECONOMY",costofchildeconomy);
                        intent1.putExtra("COSTOFCHILDFIRSTCLASS",costofchildfirstclass);
                        intent1.putExtra("COSTOFCHILDPREMIUMECONOMY",costofchildpreeconomy);

                        intent1.putExtra("DATE",date);

                        System.out.println(from+number+phone);
                        System.out.println("adultcount"+adultcount);
                        System.out.println("childcount"+childcount);
                        System.out.println("class"+userrole);
                        System.out.println("date"+date);
                        // System.out.println("date"+dayofmonth+"/"+month+"/"+year);
                        startActivity(intent1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });







            }
        });
    }
}
