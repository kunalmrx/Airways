package com.example.airways;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class TicketBookingActivity extends AppCompatActivity {
   // EditText from, destination, depart, retur;
    int i, j,flag;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;
    Button selectDatedepart, selectDatereturn, roundtrip, oneway;
    Button adultplus, adultminus, childplus, childminus;
    Button faredetail, searchflight;
    TextView adultcount, childcount;
    TextView datedepart, datereturn,etretrun;
   // RadioButton Bussiness,Economy,FirstClass,PremiumEconomy;
    DatePickerDialog datePickerDialog1, datePickerDialog2;
    EditText To, From;
    int year, yerar;
    int month, monthr;
    int dayOfMonth, dayOfMonthr;
    Calendar calendar, calendar2;
    String date;
    String AdultCOUNT;
    String ChildCOUNT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_booking);
      //  Bussiness=findViewById(R.id.bussiness);
       // Economy=findViewById(R.id.economy);
        //FirstClass=findViewById(R.id.firstclass);
       // PremiumEconomy=findViewById(R.id.firstclass);
        roundtrip=findViewById(R.id.btroundtrip);
        etretrun=findViewById(R.id.etreturn);
        oneway=findViewById(R.id.btoneway);
        To = findViewById(R.id.etto);
        From = findViewById(R.id.etfrom);
       // faredetail = findViewById(R.id.btfaredetail);
        searchflight = findViewById(R.id.buttonsearchflight);
        adultcount = findViewById(R.id.etadultcount);
        childcount = findViewById(R.id.etchildcount);
        adultplus = findViewById(R.id.buttonadultp);
        adultminus = findViewById(R.id.buttonadultminus);
        childminus = findViewById(R.id.buttonchildminus);
        childplus = findViewById(R.id.buttonchildplus);
        selectDatedepart = findViewById(R.id.datedepart);
        datedepart = findViewById(R.id.etdatedepart);
        selectDatereturn = findViewById(R.id.datereturn);
        datereturn = findViewById(R.id.etdatereturn);
        childcount.setText(Integer.toString(i));
        adultcount.setText(Integer.toString(j));

        Intent intent=getIntent();
        final String phone=intent.getStringExtra("PHONE");
        roundtrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDatereturn.setVisibility(View.VISIBLE);
                datereturn.setVisibility(View.VISIBLE);
                etretrun.setVisibility(View.VISIBLE);
            }
        });
        oneway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDatereturn.setVisibility(View.INVISIBLE);
                datereturn.setVisibility(View.INVISIBLE);
                etretrun.setVisibility(View.INVISIBLE);
            }
        });
        selectDatedepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog1 = new DatePickerDialog(TicketBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                datedepart.setText(day + "/" + (month + 1) + "/" + year);
                                date=datedepart.getText().toString();
                                System.out.println("date1"+date);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog1.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog1.show();
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                System.out.println("date2"+dayOfMonth+"/"+month+"/"+year);
            }
        });
        selectDatereturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar2 = Calendar.getInstance();
                yerar = calendar2.get(Calendar.YEAR);
                monthr = calendar2.get(Calendar.MONTH);
                dayOfMonthr = calendar2.get(Calendar.DAY_OF_MONTH);
                datePickerDialog2 = new DatePickerDialog(TicketBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                datereturn.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, yerar, monthr, dayOfMonthr);
                datePickerDialog2.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog2.show();
            }
        });

        adultplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                adultcount.setText(Integer.toString(i));
            }
        });
        adultminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                adultcount.setText(Integer.toString(i));
            }
        });
        childplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j++;
                childcount.setText(Integer.toString(j));
            }
        });
        childminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j--;
                childcount.setText(Integer.toString(j));
            }
        });

        final String from=From.getText().toString();
        final String to=To.getText().toString();
       // final String date=String.valueOf(datedepart.getText());
        searchflight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdultCOUNT=String.valueOf(adultcount.getText());
                ChildCOUNT=String.valueOf(childcount.getText());
              /*  if(Bussiness.isChecked())
                {
                    clas="bussiness";
                }
                if(Economy.isChecked())
                {
                    clas="economy";
                }
                if(FirstClass.isChecked())
                {
                    clas="FirstClass";
                }
                if(PremiumEconomy.isChecked())
                {
                    clas="PremiumEconomy";
                }*/
                final String flightfrom=From.getText().toString().trim();
                final String flightto=To.getText().toString().trim();
                final  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("flight");
            // DatabaseReference kunal=databaseReference.child("101");
              // Query checUser = databaseReference.orderByChild("from").equalTo(flightfrom);
                /*   checUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            String tocheck=dataSnapshot.child(flightfrom).child("to").getValue(String.class);
                          if(flightto.equals(tocheck)){

                              Integer checknumber=dataSnapshot.child(flightfrom).child("number").getValue(Integer.class);
                              String checkname=dataSnapshot.child(flightfrom).child("Name").getValue(String.class);
                              Integer checkseat=dataSnapshot.child(flightfrom).child("seats").getValue(Integer.class);
                           Toast.makeText(TicketBookingActivity.this, "succesfully searched", Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(getApplicationContext(),Flight.class);
                           intent.putExtra("NAME",checkname);
                           intent.putExtra("FROM",flightfrom);
                           intent.putExtra("TO",tocheck);
                           intent.putExtra("NUMBER",checknumber);
                           intent.putExtra("SEAT",checkseat);
                          startActivity(intent);
                       }
                         else
                          {
                            Toast.makeText(TicketBookingActivity.this, "no flight are availabel", Toast.LENGTH_SHORT).show();
                          }
                        }
                        else
                        {

                            Toast.makeText(TicketBookingActivity.this, "no flight are availabel", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                      //  Toast.makeText(TicketBookingActivity.this, "unsuccesfull", Toast.LENGTH_SHORT).show();

                    }
                });*/
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            String value = String.valueOf(dataSnapshot1.child("from").getValue());
                            String value2=String.valueOf(dataSnapshot1.child("to").getValue());
                           if (value.equalsIgnoreCase(flightfrom)&&value2.equalsIgnoreCase(flightto)) {
                               flag=0;
                               String number=String.valueOf(dataSnapshot1.child("number").getValue());
                             String namefeomdb=String.valueOf(dataSnapshot1.child("name").getValue(String.class));
                               String numberfromdb=String.valueOf(dataSnapshot1.child("number").getValue(String.class));
                               String dayfromdb=String.valueOf(dataSnapshot1.child("day").getValue(String.class));
                               String seatefromdb=String.valueOf(dataSnapshot1.child("seate").getValue(String.class));
                               String seatbfromdb=String.valueOf(dataSnapshot1.child("seatb").getValue(String.class));
                               String seatfcfromdb=String.valueOf(dataSnapshot1.child("seatfc").getValue(String.class));
                               String seatpefromdb=String.valueOf(dataSnapshot1.child("seatpe").getValue(String.class));
                               Toast.makeText(TicketBookingActivity.this, "succesfully searched", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(getApplicationContext(),SearchedFlights.class);
                               intent.putExtra("NAME",namefeomdb);
                               intent.putExtra("FROM",flightfrom);
                               intent.putExtra("TO",flightto);
                               intent.putExtra("NUMBER",numberfromdb);
                               intent.putExtra("SEATe",seatefromdb);
                               intent.putExtra("SEATb",seatbfromdb);
                               intent.putExtra("SEATfc",seatfcfromdb);
                               intent.putExtra("SEATpe",seatpefromdb);
                               intent.putExtra("ADULTCOUNT",AdultCOUNT);
                               intent.putExtra("CHILDCOUNT",ChildCOUNT);
                               intent.putExtra("PHONE",phone);
                               intent.putExtra("DAY",dayOfMonth);
                               intent.putExtra("MONTH",String.valueOf(month));
                               intent.putExtra("YEAR",String.valueOf(year));
                               intent.putExtra("DATE",date);
                             //  intent.putExtra("CLASS",clas);

                         System.out.println(value+number+namefeomdb+phone+seatbfromdb+seatefromdb+seatfcfromdb+seatpefromdb);
                               System.out.println("adultcount"+AdultCOUNT);
                               System.out.println("childcount"+ChildCOUNT);
                               System.out.println("date"+date);
                               System.out.println("date"+dayOfMonth+"/"+month+"/"+year);


                               startActivity(intent);
                                     break;

                          }
                          else
                            {
                               flag=0;
                            }
                          if(flag==0)
                          {
                              Toast.makeText(TicketBookingActivity.this, "no flight are avalilable", Toast.LENGTH_SHORT).show();
                          }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(TicketBookingActivity.this, "fail", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}