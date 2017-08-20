package com.example.android.intern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Memcomplaint extends AppCompatActivity {
    Button mybutton;
    EditText phone, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memcomplaint);
        mybutton = (Button) findViewById(R.id.button2);
        phone = (EditText) findViewById(R.id.editText3);
        message = (EditText) findViewById(R.id.editText5);
        final SmsManager mySms = SmsManager.getDefault();
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ph = phone.getText().toString();
                String msg = message.getText().toString();
                try {
                    mySms.sendTextMessage("+91" + ph, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "SENT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }
            }


        });

    }
}

