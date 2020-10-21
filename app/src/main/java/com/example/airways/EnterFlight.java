package com.example.airways;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterFlight extends AppCompatActivity {
    TextView Name,Number,From,To,Day,Time,SeatB,SeatFC,SeatE,SEAtPE;
    EditText Ab;
    EditText Ae;
    EditText Afc;
    EditText Ape;
    EditText Cb;
    EditText Ce;
    EditText Cfc;
    EditText Cpe;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_flight);
        Name=findViewById(R.id.flightname);
        Number=findViewById(R.id.flightno);
        To=findViewById(R.id.tvto);
        From=findViewById(R.id.tvfrom);
        SeatB=findViewById(R.id.b);

        SeatFC=findViewById(R.id.fc);
        SeatE=findViewById(R.id.e);
        SEAtPE=findViewById(R.id.pe);
        save=findViewById(R.id.addflight);
        Ab=findViewById(R.id.ab);
        Ae=findViewById(R.id.ae);
        Afc=findViewById(R.id.afc);
        Ape=findViewById(R.id.ape);
        Cb=findViewById(R.id.cb);
        Ce=findViewById(R.id.ce);
        Cfc=findViewById(R.id.cfc);
        Cpe=findViewById(R.id.cpe);
        Day=findViewById(R.id.day);
        Time=findViewById(R.id.time);
        final FirebaseDatabase firebasedatabase = FirebaseDatabase.getInstance();
       final DatabaseReference databaseReference=firebasedatabase.getReference("flight");


        final String eseatpe=SEAtPE.getText().toString();
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String ename=Name.getText().toString();
                final String enumber=Number.getText().toString();
                final String efrom=From.getText().toString();
                final String eto=To.getText().toString();
                final String eday=Day.getText().toString();
                final String etime=Time.getText().toString();
                final String eseatb=SeatB.getText().toString();
                final String eseate=SeatE.getText().toString();
                final String eseatfc=SeatFC.getText().toString();
                final String eseatpe=SEAtPE.getText().toString();
                final String ab=Ab.getText().toString();
                final String ae=Ae.getText().toString();
                final String afc=Afc.getText().toString();
                final String ape=Ape.getText().toString();
                final String cb=Cb.getText().toString();
                final String ce=Ce.getText().toString();
                final String cfc=Cfc.getText().toString();
                final String cpe=Cpe.getText().toString();


                Flightinfo info=new Flightinfo(
                        ename,enumber,efrom,eto,eday,etime,eseatb,eseate,eseatfc,eseatpe,ab,ae,afc,ape,cb,ce,cfc,cpe);
                databaseReference.child(enumber).setValue(info);

            }
        });




    }
}
