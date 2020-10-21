package com.example.airways;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.NavigableMap;

public class SignupActivity<RegisterActivity> extends AppCompatActivity {
    EditText Name;
    EditText Lastname;
    EditText Email;
    EditText Password;
    EditText Phone;
    EditText ConfirmPassword;
    private Button register;
    private FirebaseAuth auth;
    private RadioButton Male,Female;
    private ProgressDialog ProgressBar;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;
            DatabaseReference databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name=findViewById(R.id.nameet);
        Lastname=findViewById(R.id.lastnameet);
        Phone=findViewById(R.id.phoneet);
        Email=findViewById(R.id.emailet);
        Password=findViewById(R.id.passwordet);
        ConfirmPassword=findViewById(R.id.confirmpasswordet);
        register=findViewById(R.id.registerbutton);
        Male=findViewById(R.id.radioM);
        Female=findViewById(R.id.radioF);
        ProgressBar=new ProgressDialog(this);
        final String username=Name.toString();
        auth=FirebaseAuth.getInstance();
        register=(Button)findViewById(R.id.registerbutton);
        firebasedatabase=FirebaseDatabase.getInstance();
        databaseReference=firebasedatabase.getReference("user");
        //databaseReference2=firebasedatabase.getReference("airline");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 final String txt_email=Email.getText().toString();
                 final String txt_password=Password.getText().toString();
                 String txt_confirmpassword=ConfirmPassword.getText().toString();
                 final String txt_name=Name.getText().toString();
                 final String txt_phone=Phone.getText().toString();
                 final String txt_lastname=Lastname.getText().toString();
                 String txt_gender="";
                if (txt_name.isEmpty())
                {
                    Name.setError("Please enter a name");
                    Name.requestFocus();
                }
                else if (txt_email.isEmpty())
                {
                    Email.setError("Please enter the email");
                    Email.requestFocus();
                }
                else if (txt_password.isEmpty())
                {
                    Password.setError("Please enter the password");
                    Password.requestFocus();
                }
                else if (txt_confirmpassword.isEmpty())
                {
                    ConfirmPassword.setError("Please enter the password");
                    ConfirmPassword.requestFocus();
                }
                else if (txt_phone.isEmpty())
                {
                    Phone.setError("Please enter the phone number");
                    Phone.requestFocus();
                }
                else if (!(txt_password.equals(txt_confirmpassword)))
                {
                    ConfirmPassword.setError(" password is not match");
                    ConfirmPassword.requestFocus();
                }
               if( Male.isChecked())
                {
                    txt_gender="Male";
                }
               else{
                   txt_gender="Female";
               }
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(SignupActivity.this,"empty credential",Toast.LENGTH_SHORT).show();
                }
                else
                {

                   // registerUser(txt_email,txt_password);
                    //final String finalTxt_gender = txt_gender;

                    ProgressBar.setTitle("Create Account");
                    ProgressBar.setMessage("Please wait");
                    ProgressBar.setCanceledOnTouchOutside(false);
                    ProgressBar.show();

                    final String finalTxt_gender = txt_gender;
                    auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(SignupActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(SignupActivity.this,"succesfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(SignupActivity.this,UserActivity.class);

                                startActivity(intent);

                               // finish();

                                User info=new User (

                                        txt_name,
                                        txt_lastname,txt_email,txt_password,finalTxt_gender,txt_phone
                                          );
                                databaseReference.child(txt_phone).setValue(info);
                            /*   FirebaseDatabase.getInstance().getReference("user")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(SignupActivity.this,"succesfully",Toast.LENGTH_SHORT).show();

                                    }
                                });*/

                                    }
                else{
                                Toast.makeText(SignupActivity.this,"registerd unsuccesfully",Toast.LENGTH_SHORT).show();
                                ProgressBar.hide();
                            }

                        }
                    });
                }
            }
        });
    }

  /*  private void registerUser(String email, String password) {

    }*/
}
