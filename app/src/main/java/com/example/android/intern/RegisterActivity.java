package com.example.android.intern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.firebase.client.Config;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    ArrayList<String> mylist = new ArrayList<String>();
    private Button breg;
   private EditText ed1, ed2,ed3;
    private Spinner spinner;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Firebase mref;
    String  temp_key;
    DatabaseReference root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        breg = (Button) findViewById(R.id.button5);
        ed1 = (EditText) findViewById(R.id.editText4);
        ed2 = (EditText) findViewById(R.id.editText6);
        ed3 = (EditText) findViewById(R.id.editText7);
        root = FirebaseDatabase.getInstance().getReference();

        mref = new Firebase("https://myfirebase-f9e26.firebaseio.com/");
        progressDialog = new ProgressDialog(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        breg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){

                if (spinner.getSelectedItem().toString().equals("Admin")) {

                  loginUser();
                }
                if (spinner.getSelectedItem().toString().equals("Member")) {

                    loginUser1();

                }
            }

        });


    }

    private void loginUser() {

        String email = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();
        String flatno=ed3.getText().toString().trim();
       final User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setFlatno(flatno);
        String  ref1 =    mref.getKey();
        mylist.add(ref1);

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        (firebaseAuth.createUserWithEmailAndPassword(ed1.getText().toString(), ed2.getText().toString())).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {
                    mref.child("User").push().setValue(user);


                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, DisplayMessageActivity.class);
                    intent.putExtra("flatno", user.getFlatno());
                    intent.putExtra("username", user.getEmail());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
    private void loginUser1() {
        String email = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();
        String flatno = ed3.getText().toString().trim();
        final User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setFlatno(flatno);
        String  ref1 =    mref.getKey();
        mylist.add(ref1);
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        ( firebaseAuth.createUserWithEmailAndPassword(ed1.getText().toString(), ed2.getText().toString())).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {

                    mref.child("User").push().setValue(user);
                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, Member.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Failed" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}