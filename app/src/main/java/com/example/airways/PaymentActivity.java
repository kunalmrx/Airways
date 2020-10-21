package com.example.airways;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class PaymentActivity extends AppCompatActivity {
    TextView From,To,amountadult,amountchild,totalamount,date;
    Button Proceed;
    FirebaseDatabase firebasedatabase,firebasedatabase2;
    FirebaseDatabase firebasedatabase3;
    DatabaseReference databaseReference,databaseReference2;
    DatabaseReference databaseReference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        From=findViewById(R.id.tvfrom);
        To=findViewById(R.id.tvto);
        amountadult=findViewById(R.id.adult);
        amountchild=findViewById(R.id.child);
        totalamount=findViewById(R.id.tvammount);
        date=findViewById(R.id.tvdate);
        Proceed=findViewById(R.id.proceed);
        Intent intent=getIntent();
        final String to=intent.getStringExtra("TO");
        final String name=intent.getStringExtra("NAME");
        final String from=intent.getStringExtra("FROM");
        final String number=intent.getStringExtra("FLIGHTNUMBER");
        final String phone=intent.getStringExtra("PHONE");
        final String clas=intent.getStringExtra("CLASS");
        final String adultcount=intent.getStringExtra("ADULTCOUNT");
        final String childcount=intent.getStringExtra("CHILDCOUNT");
        final String dayofmonth=intent.getStringExtra("DAY");
        final String month=intent.getStringExtra("MONTH");
        final String year=intent.getStringExtra("YEAR");
        final String seatE=intent.getStringExtra("SEATe");
        final String seatB=intent.getStringExtra("SEATb");
        final String seatFC=intent.getStringExtra("SEATfc");
        final String seatPE=intent.getStringExtra("SEATpe");
        final String Date=intent.getStringExtra("DATE");
        int childseatcost = 0,adultseatcost = 0;

        //for seat cost
        String costofadultbussiness=intent.getStringExtra("COSTOFADULTBUSSINESS");
        String costofadulteconomy =intent.getStringExtra("COSTOFADULTECONOMY");
        String costofadultfirstclass=intent.getStringExtra("COSTOFADULTFIRSTCLASS");
        String costofadultpreeconomy=intent.getStringExtra("COSTOFADULTPREMIUMECONOMY");
        String costofchildbussiness=intent.getStringExtra("COSTOFCHILDBUSSINESS");
        String costofchildeconomy=intent.getStringExtra("COSTOFCHILDECONOMY");
        String costofchildfirstclass=intent.getStringExtra("COSTOFCHILDBFIRSTCLASS");
        String costofchildpreeconomy=intent.getStringExtra("COSTOFCHILDPREMIUMECONOMY");





        //new available seats
        int setseatadult=0;

        if(clas.equalsIgnoreCase("economy"))
        {
            setseatadult=Integer.parseInt(seatE)-Integer.parseInt(adultcount)+Integer.parseInt(childcount);
            childseatcost=Integer.parseInt(costofchildeconomy);
            adultseatcost=Integer.parseInt(costofadulteconomy);
        }
        else   if(clas.equalsIgnoreCase("bussiness"))
        {
            setseatadult=Integer.parseInt(seatB)-Integer.parseInt(adultcount)+Integer.parseInt(childcount);
            childseatcost=Integer.parseInt(costofchildbussiness);
            adultseatcost=Integer.parseInt(costofchildbussiness);
        }
        else if(clas.equalsIgnoreCase("firstclass"))
        {
            setseatadult=Integer.parseInt(seatFC)-Integer.parseInt(adultcount)+Integer.parseInt(childcount);
            childseatcost=Integer.parseInt(costofchildfirstclass);
            adultseatcost=Integer.parseInt(costofadultfirstclass);
        }  else if(clas.equalsIgnoreCase("premiumeconomy"))
        {
            setseatadult=Integer.parseInt(seatPE)-Integer.parseInt(adultcount)+Integer.parseInt(childcount);
            childseatcost=Integer.parseInt(costofchildpreeconomy);
            adultseatcost=Integer.parseInt(costofadultpreeconomy);
        }
        firebasedatabase=FirebaseDatabase.getInstance();
        databaseReference=firebasedatabase.getReference("flight");
       // String costofchildseat=databaseReference.child(phone).child("cb")

        From.setText(from);
        To.setText(to);
        amountchild.setText(childcount+" CHILD "+" * "+childseatcost+" = "+(Integer.parseInt(childcount)*childseatcost));
        amountadult.setText(adultcount+" ADULT "+" * "+adultseatcost+" = "+(Integer.parseInt(adultcount)*adultseatcost));
        date.setText(Date);
        final int ammout=(Integer.parseInt(childcount)*childseatcost)+(Integer.parseInt(adultcount)*adultseatcost);
        totalamount.setText("YOUR TOTAL AMMOUNT IS "+ammout);
        System.out.println(from+number+phone);
        System.out.println("adultcount"+adultcount);
        System.out.println("childcount"+childcount);
        System.out.println("class"+clas);
        System.out.println("date"+dayofmonth+"/"+month+"/"+year);

        final int finalSetseatadult = setseatadult;
        Proceed.setOnClickListener(new View.OnClickListener() {
            Random random = new Random();
            String generatedPassword = String.format("%08d", random.nextInt(100000000));
            @Override
            public void onClick(View v) {
                firebasedatabase2=FirebaseDatabase.getInstance();
                databaseReference2=firebasedatabase2.getReference("flight");
                if(clas.equalsIgnoreCase("bussiness"))
                {databaseReference2.child(number).child("seatb").setValue(String.valueOf(finalSetseatadult));}
                else  if(clas.equalsIgnoreCase("economy"))
                {databaseReference2.child(number).child("seate").setValue(String.valueOf(finalSetseatadult));}
                else  if(clas.equalsIgnoreCase("firstclass"))
                {databaseReference2.child(number).child("seatfc").setValue(String.valueOf(finalSetseatadult));}
                else  if(clas.equalsIgnoreCase("premiumeconomy"))
                {databaseReference2.child(number).child("seatpe").setValue(String.valueOf(finalSetseatadult));}


                firebasedatabase=FirebaseDatabase.getInstance();
                databaseReference=firebasedatabase.getReference("bookedticket");
                Ticketinfo infor=new Ticketinfo(phone,number,adultcount,childcount,dayofmonth,month,year,clas,Date,generatedPassword,name,from,to,String.valueOf(ammout));
                databaseReference.child((generatedPassword)).setValue(infor);
                Toast.makeText(PaymentActivity.this, "your pnr is "+generatedPassword, Toast.LENGTH_LONG).show();

                firebasedatabase3= FirebaseDatabase.getInstance();
                databaseReference3=firebasedatabase3.getReference("user");
                databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String namefromdb = dataSnapshot.child(phone).child("name").getValue(String.class);
                        String lastnamefromdb = dataSnapshot.child(phone).child("last_name").getValue(String.class);
                        String emailfromdb = dataSnapshot.child(phone).child("email").getValue(String.class);
                        String genderfromdb = dataSnapshot.child(phone).child("gender").getValue(String.class);
                        String phonefromdb = dataSnapshot.child(phone).child("phone").getValue(String.class);
                        String passwordfromdb = dataSnapshot.child(phone).child("password").getValue(String.class);
                        String pnr = dataSnapshot.child(phone).child("pnr").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                        intent.putExtra("NAME", namefromdb);
                        intent.putExtra("EMAIL", emailfromdb);
                        intent.putExtra("LASTNAME", lastnamefromdb);
                        intent.putExtra("PASSWORD", passwordfromdb);
                        intent.putExtra("GENDER", genderfromdb);
                        intent.putExtra("PHONE", phonefromdb);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
