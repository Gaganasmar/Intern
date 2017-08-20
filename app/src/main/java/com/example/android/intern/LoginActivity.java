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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Firebase ref_User = null;
    String email;
    String password;
    Button blogin;
    EditText ed1, ed2;
    Spinner spinner;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        blogin = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        progressDialog = new ProgressDialog(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        blogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                // Toast.makeText(this, "You selected " + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if (spinner.getSelectedItem().toString().equals("Admin")) {
                    registerUser();
                    // Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                    //  startActivity(intent);
                    //  finish();

                }
                if (spinner.getSelectedItem().toString().equals("Member")) {
                    registerUser1();
                    //  Intent intent = new Intent(MainActivity.this, Member.class);
                    //  startActivity(intent);
                    //  finish();
                }
            }

        });
    }
    private void registerUser() {
         email = ed1.getText().toString().trim();
         password = ed2.getText().toString().trim();
        ref_User = new Firebase(Config.FIREBASE_USER_NODE);
        ref_User.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.hasChild(email)) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user_password = snapshot.getValue(User.class);
                    String user_pass = user_password.getPassword();

                    if (password.equals(user_pass)) {
                        Toast.makeText(getApplicationContext(), "USER AUTHENTICATED", Toast.LENGTH_SHORT).show();


                    }
                    else ed2.setError("Incorrect Password");
                }
            } else {
                ed1.setError("User not registered");
            }

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
            Toast.makeText(getApplicationContext(), "DATABASE ERROR", Toast.LENGTH_SHORT).show();
        }
    });



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
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {

                    finish();
                    Toast.makeText(LoginActivity.this, "logged successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DisplayMessageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "loggin Failed" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void registerUser1() {
        String email = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();
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
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {

                    finish();
                    Toast.makeText(LoginActivity.this, "logged successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, Member.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "loggin Failed" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
