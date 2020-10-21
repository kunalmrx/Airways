package com.example.airways;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CancelActivity extends AppCompatActivity {
     EditText pnrnumber;
     Button Cancel;
     FirebaseDatabase firebaseDatabase;
     DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);
        Intent intent=getIntent();
        final String username=intent.getStringExtra("NAME");
        final String password=intent.getStringExtra("PASSWORD");
        final String email=intent.getStringExtra("EMAIL");
        final String phone=intent.getStringExtra("PHONE");
pnrnumber=findViewById(R.id.etpnr);
Cancel=findViewById(R.id.btcancel);
final String PNr=pnrnumber.getText().toString();


      databaseReference = FirebaseDatabase.getInstance().getReference("bookedticket");
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())

                    {
                        String value = String.valueOf(dataSnapshot1.child("pnr").getValue());
                        if(pnrnumber.equals(PNr))
                        {
                            String key=dataSnapshot1.getKey();
                            databaseReference.child(key).removeValue();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
                finish();

            }
        });


    }
}
