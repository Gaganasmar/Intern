package com.example.android.intern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Member extends AppCompatActivity {
    TextView edittext1,edittext2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        edittext1 = (TextView) findViewById(R.id.textView);
        edittext2 = (TextView) findViewById(R.id.textView4);
        edittext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView myText = (TextView) view;
                //     Toast.makeText(this, "You selected " + edittext.getText().toString(), Toast.LENGTH_SHORT).show();
                if (edittext1.getText().toString().equals("Maintainance Details")) {
                    Intent intent = new Intent(Member.this, Memmaint.class);

                    startActivity(intent);


                }
            }

        });
        edittext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView myText = (TextView) view;
                //     Toast.makeText(this, "You selected " + edittext.getText().toString(), Toast.LENGTH_SHORT).show();
                if (edittext2.getText().toString().equals("Complaints")) {
                    Intent intent = new Intent(Member.this, Memcomplaint.class);

                    startActivity(intent);


                }
            }

        });


    }
}
