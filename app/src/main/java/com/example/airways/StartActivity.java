package com.example.airways;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.jar.Attributes;

public class StartActivity extends AppCompatActivity {

    EditText Email, Password;
    Button submit, signup;
    TextView result;
    FirebaseAuth auth;
    ProgressDialog ProgressBar;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        submit = (Button) findViewById(R.id.etbutton);
        result = (TextView) findViewById(R.id.ettext);
        Email = (EditText) findViewById(R.id.etPhone);
        Password = (EditText) findViewById(R.id.etPassword);
        signup = (Button) findViewById(R.id.etbutton2);
        ProgressBar = new ProgressDialog(this);
        // auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Email.getText().toString();
                String password = Password.getText().toString();
                if (!(name.isEmpty() && password.isEmpty())) {
                    isUser();
                    // String txt_email=name;
                    // String txt_password=password;

                    ProgressBar.setTitle("login Account");
                    ProgressBar.setMessage("Please wait");
                    ProgressBar.setCanceledOnTouchOutside(false);
                    ProgressBar.show();

                }
            }


        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, SignupActivity.class));
            }
        });
    }

    private void isUser() {
        if (!validatePassword() | !validateusername()) {
            return;
        } else {
            loginUser();
        }
    }


    private  Boolean validateusername(){
        String name = Email.getText().toString();
        if (name.isEmpty()) {
            Email.setError("Please enter a email");
            Email.requestFocus();

            return false;}
        else {
            return true;
        }
    }
    private  Boolean validatePassword(){
        String password = Password.getText().toString();
    if (password.isEmpty()) {

        Password.setError("Please enter the password");
        Password.requestFocus();
        return false;
    }
    else
    {
        return true;
        }
    }
    private void loginUser() {
        final String userenterdemail = Email.getText().toString().trim();
        final String userenterdpassword = Password.getText().toString().trim();
        DatabaseReference refrence = FirebaseDatabase.getInstance().getReference("user");
        Query checUser = refrence.orderByChild("phone").equalTo(userenterdemail);
        checUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Email.setError(null);

                    String passwordfromdb = dataSnapshot.child(userenterdemail).child("password").getValue(String.class);
                    if (userenterdpassword.equals(passwordfromdb)) {
                        Password.setError(null);
                        String namefromdb = dataSnapshot.child(userenterdemail).child("name").getValue(String.class);
                        String lastnamefromdb = dataSnapshot.child(userenterdemail).child("last_name").getValue(String.class);
                        String emailfromdb = dataSnapshot.child(userenterdemail).child("email").getValue(String.class);
                        String genderfromdb = dataSnapshot.child(userenterdemail).child("gender").getValue(String.class);
                        String phonefromdb = dataSnapshot.child(userenterdemail).child("phone").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                        intent.putExtra("NAME", namefromdb);
                        intent.putExtra("EMAIL", emailfromdb);
                        intent.putExtra("LASTNAME", lastnamefromdb);
                        intent.putExtra("PASSWORD", passwordfromdb);
                        intent.putExtra("GENDER", genderfromdb);
                        intent.putExtra("PHONE", phonefromdb);
                        startActivity(intent);

                    }
                    else {
                        ProgressBar.hide();
                        Password.setError("wrong password");
                        Password.requestFocus();
                    }
                }
                else
                {
                    ProgressBar.hide();
                    Email.setError("no such user exist");
                    Email.requestFocus();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

}
