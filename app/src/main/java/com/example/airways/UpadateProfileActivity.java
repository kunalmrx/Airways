package com.example.airways;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UpadateProfileActivity extends AppCompatActivity {
    Uri filepath;
    FirebaseStorage storage;
    StorageReference storageReference;
    private EditText NAME,Password,Email,Phone;
    private Button Uploadphoto,Save,Cancel;
    private ImageView img;
    TextView txt;
    private String phone;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadate_profile);
        NAME=findViewById(R.id.editTextname);
        Password=findViewById(R.id.editTextpassword);
        Email=findViewById(R.id.editTextemail);
        Phone=findViewById(R.id.editTextphone);
        Uploadphoto=findViewById(R.id.uploadphoto);
        Save=findViewById(R.id.save);
        Cancel=findViewById(R.id.cancel);
        img=findViewById(R.id.imageView);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        Intent intent=getIntent();
        String username=intent.getStringExtra("NAME");
        String password=intent.getStringExtra("PASSWORD");
        String email=intent.getStringExtra("EMAIL");
         phone=intent.getStringExtra("PHONE");
        NAME.setText(username);
        Email.setText(email);
        Phone.setText(phone);
        Password.setText(password);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("user");
        //FirebaseUser user=databaseReference.child(phone);
       // UserProfileChangeRequest


        Uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
      //  uploadImage();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        img.setImageBitmap(bitmap);
        handleUpload(bitmap);

    }

    private void handleUpload(Bitmap bitmap) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        //String PHONE=phone;
        final StorageReference reference=FirebaseStorage.getInstance().getReference()
                .child("profileimage")
                .child(phone);
        reference.putBytes(baos.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UpadateProfileActivity.this, "Update profile succesfully", Toast.LENGTH_SHORT).show();
                getDownloadUrl(reference);

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(UpadateProfileActivity.this, "profile is not updated", Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void getDownloadUrl(StorageReference reference)
    {
    reference.getDownloadUrl()
            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                //     Log.d(TAG,"onsucces"+uri);
                    setUserProfile(uri);
                }
            });
    }
    private void setUserProfile(Uri uri)
     { //StorageReference User=FirebaseStorage.getInstance().getReference()
         //    .child("profileimage").child(phone);
         FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
         UserProfileChangeRequest request=new UserProfileChangeRequest.Builder()
                 .setPhotoUri(uri)
                 .build();
         user.updateProfile(request)
                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {

                     }
                 });





     }
}

