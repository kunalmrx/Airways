package com.example.airways;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CheckStatusActivity extends AppCompatActivity {


    List<Hero> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);
        Intent intent=getIntent();
        final String username=intent.getStringExtra("NAME");
        final String password=intent.getStringExtra("PASSWORD");
        final String email=intent.getStringExtra("EMAIL");
        final String phone=intent.getStringExtra("PHONE");


        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewcheckstatus);


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("bookedticket");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String value = String.valueOf(dataSnapshot1.child("customerid").getValue());



                    if (value.equalsIgnoreCase(phone))
                    {

                        String name = String.valueOf(dataSnapshot1.child("name").getValue());
                        String adultseat = String.valueOf(dataSnapshot1.child("adultseat").getValue());
                        String childseat = String.valueOf(dataSnapshot1.child("childseat").getValue());
                        String clas = String.valueOf(dataSnapshot1.child("clas").getValue());
                        String date = String.valueOf(dataSnapshot1.child("date").getValue());
                        String flightid = String.valueOf(dataSnapshot1.child("flightid").getValue());
                        String pnr = String.valueOf(dataSnapshot1.child("pnr").getValue());
                        if(name.equalsIgnoreCase("spicejet"))
                        {  heroList.add(new Hero(R.drawable.spicejetlogo,name,pnr,"DATE : "+date));}
                        else{heroList.add(new Hero(R.drawable.indigo2,name,pnr,"DATE : "+date));}
                        MyListAdapter adapter = new MyListAdapter(CheckStatusActivity.this, R.layout.my_custom_list, heroList);

                        //attaching adapter to the listview
                        listView.setAdapter(adapter);



                    }



                    else{
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        });


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final   String key=heroList.get(position).team.toString();

        // Toast.makeText(SearchedFlights.this, "hellon guys", Toast.LENGTH_SHORT).show();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("bookedticket");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //  String name=dataSnapshot.child(key).child("name").getValue(String.class);

                String flightnumber=dataSnapshot.child(key).child("flightid").getValue(String.class);
                String namefeomdb=dataSnapshot.child(key).child("name").getValue(String.class);
                String dayfromdb=dataSnapshot.child(key).child("date").getValue(String.class);
                String adultseatefromdb=dataSnapshot.child(key).child("adultseat").getValue(String.class);
                String childseatbfromdb=dataSnapshot.child(key).child("childseat").getValue(String.class);
                String custnumber=dataSnapshot.child(key).child("customerid").getValue(String.class);
                String pnr=dataSnapshot.child(key).child("pnr").getValue(String.class);
                String from=dataSnapshot.child(key).child("from").getValue(String.class);
                String to=dataSnapshot.child(key).child("to").getValue(String.class);
                String clas=dataSnapshot.child(key).child("clas").getValue(String.class);
                String amount=dataSnapshot.child(key).child("ammount").getValue(String.class);
                System.out.println(flightnumber+namefeomdb+dayfromdb+adultseatefromdb+childseatbfromdb+custnumber+pnr+from+to+clas);

                //Toast.makeText(CheckStatusActivity.this, "succesfully searched", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),ShowBookedTicket.class);
                intent.putExtra("NAME",namefeomdb);
                intent.putExtra("FROM",from);
                intent.putExtra("TO",to);
                intent.putExtra("NUMBER",flightnumber);
                intent.putExtra("ADULTCOUNT",adultseatefromdb);
                intent.putExtra("CHILDCOUNT",childseatbfromdb);
                intent.putExtra("PHONE",custnumber);
                intent.putExtra("PNR",pnr);
                intent.putExtra("CLASS",clas);
                intent.putExtra("AMOUNT",amount);
                intent.putExtra("DATE",dayfromdb);
               // intent.putExtra("DAY",dayofmonth);
                //intent.putExtra("MONTH",month);
                //intent.putExtra("YEAR",year);
               intent.putExtra("DATE",dayfromdb);
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