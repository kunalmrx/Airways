package com.example.airways;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchedFlights extends AppCompatActivity implements ValueEventListener {

    List<Hero> heroList;

    //the listview
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_flights);
     Intent intent=getIntent();
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        final String name=intent.getStringExtra("NAME");
        final String to=intent.getStringExtra("TO");
        final String from=intent.getStringExtra("FROM");
       // final String day=intent.getStringExtra("DAY");
        final String number=intent.getStringExtra("NUMBER");
        final String seatE=intent.getStringExtra("SEATe");
        final String seaTb=intent.getStringExtra("SEATb");
        final String seaTfc=intent.getStringExtra("SEATfc");
        final String seatPE=intent.getStringExtra("SEATpe");
        final String phone=intent.getStringExtra("PHONE");
       // final String clas=intent.getStringExtra("CLASS");
        final String adultcount=intent.getStringExtra("ADULTCOUNT");
        final String childcount=intent.getStringExtra("CHILDCOUNT");
       // final String dayofmonth=intent.getStringExtra("DAY");
       // final String month=intent.getStringExtra("MONTH");
       // final String year=intent.getStringExtra("YEAR");
        final String date=intent.getStringExtra("DATE");




        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        //adding some values to our list
       //heroList.add(new Hero(R.drawable.air, "Spiderman", "Avengers"));
       // heroList.add(new Hero(R.drawable.air2, "Joker", "Injustice Gang"));

        //creating the adapter
       MyListAdapter adapter = new MyListAdapter(this, R.layout.my_custom_list, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);







        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("flight");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String value = String.valueOf(dataSnapshot1.child("from").getValue());
                    String value2=String.valueOf(dataSnapshot1.child("to").getValue());
                    String name=String.valueOf(dataSnapshot1.child("name").getValue());
                    String time=String.valueOf(dataSnapshot1.child("time").getValue());



                    if (value.equalsIgnoreCase(from)&&value2.equalsIgnoreCase(to)) {

                        String value3 = String.valueOf(dataSnapshot1.child("number").getValue());
                        if(name.equalsIgnoreCase("spicejet"))
                        {  heroList.add(new Hero(R.drawable.spicejetlogo,name,value3,"Date : "+time));}
                        else{heroList.add(new Hero(R.drawable.indigo2,name,value3, "Date : "+time));}
                        MyListAdapter adapter = new MyListAdapter(SearchedFlights.this, R.layout.my_custom_list, heroList);

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

    /*    mylistView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // Toast.makeText(CheckStatusActivity.this,"hello", Toast.LENGTH_SHORT).show();

                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("flight");
               final String key= myarraylist2.get(position).toString();
              //  final String key= title[position].toString();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //  String name=dataSnapshot.child(key).child("name").getValue(String.class);

                        String number=dataSnapshot.child(key).child("number").getValue(String.class);
                        String namefeomdb=dataSnapshot.child(key).child("name").getValue(String.class);
                        String dayfromdb=dataSnapshot.child(key).child("day").getValue(String.class);
                        String seatefromdb=dataSnapshot.child(key).child("seate").getValue(String.class);
                        String seatbfromdb=dataSnapshot.child(key).child("seatb").getValue(String.class);
                        String seatfcfromdb=dataSnapshot.child(key).child("seatfc").getValue(String.class);
                        String seatpefromdb=dataSnapshot.child(key).child("seatpe").getValue(String.class);
                        //Toast.makeText(CheckStatusActivity.this, "succesfully searched", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Flight.class);
                        intent.putExtra("NAME",namefeomdb);
                        intent.putExtra("FROM",from);
                        intent.putExtra("TO",to);
                        intent.putExtra("NUMBER",number);
                        intent.putExtra("SEATe",seatefromdb);
                        intent.putExtra("SEATb",seatbfromdb);
                        intent.putExtra("SEATfc",seatfcfromdb);
                        intent.putExtra("SEATpe",seatpefromdb);
                       // intent.putExtra("CLASS",clas);
                        intent.putExtra("ADULTCOUNT",adultcount);
                        intent.putExtra("CHILDCOUNT",childcount);
                        intent.putExtra("PHONE",phone);
                       // intent.putExtra("DAY",dayofmonth);
                        //intent.putExtra("MONTH",month);
                        //intent.putExtra("YEAR",year);
                        intent.putExtra("DATE",date);
                        System.out.println(from+number+namefeomdb+phone+seatbfromdb+seatefromdb+seatfcfromdb+seatpefromdb);
                        System.out.println("adultcount"+adultcount);
                        System.out.println("childcount"+childcount);
                        System.out.println("date"+date);
                        System.out.println("date"+title[0]);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
*


/
     */



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final   String key=heroList.get(position).team.toString();

                   // Toast.makeText(SearchedFlights.this, "hellon guys", Toast.LENGTH_SHORT).show();
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("flight");
















                //  final String key= title[position].toString();
               // final String key="10101";
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //  String name=dataSnapshot.child(key).child("name").getValue(String.class);

                        String number=dataSnapshot.child(key).child("number").getValue(String.class);
                        String namefeomdb=dataSnapshot.child(key).child("name").getValue(String.class);
                        String dayfromdb=dataSnapshot.child(key).child("day").getValue(String.class);
                        String seatefromdb=dataSnapshot.child(key).child("seate").getValue(String.class);
                        String seatbfromdb=dataSnapshot.child(key).child("seatb").getValue(String.class);
                        String seatfcfromdb=dataSnapshot.child(key).child("seatfc").getValue(String.class);
                        String seatpefromdb=dataSnapshot.child(key).child("seatpe").getValue(String.class);
                        //Toast.makeText(CheckStatusActivity.this, "succesfully searched", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Flight.class);
                        intent.putExtra("NAME",namefeomdb);
                        intent.putExtra("FROM",from);
                        intent.putExtra("TO",to);
                        intent.putExtra("NUMBER",number);
                        intent.putExtra("SEATe",seatefromdb);
                        intent.putExtra("SEATb",seatbfromdb);
                        intent.putExtra("SEATfc",seatfcfromdb);
                        intent.putExtra("SEATpe",seatpefromdb);
                        // intent.putExtra("CLASS",clas);
                        intent.putExtra("ADULTCOUNT",adultcount);
                        intent.putExtra("CHILDCOUNT",childcount);
                        intent.putExtra("PHONE",phone);
                        // intent.putExtra("DAY",dayofmonth);
                        //intent.putExtra("MONTH",month);
                        //intent.putExtra("YEAR",year);
                        intent.putExtra("DATE",date);
                        System.out.println(from+number+namefeomdb+phone+seatbfromdb+seatefromdb+seatfcfromdb+seatpefromdb);
                        System.out.println("adultcount"+adultcount);
                        System.out.println("childcount"+childcount);
                        System.out.println("date"+date);
                      //  System.out.println("date"+title[0]);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });





    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
