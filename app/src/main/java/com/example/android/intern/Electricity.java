package com.example.android.intern;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.firebase.client.ChildEventListener;

//import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import com.firebase.client.authentication.Constants;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Electricity extends AppCompatActivity {

    private Firebase mref;
  //  private List<String> mUsername = new ArrayList<>();
  //  private ListView mlistview;
 //   private SearchView sv;
  private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Upload> uploads;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);

        final String email= null;
        final String flatno= null;
        final String bill=null;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
       recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Firebase.setAndroidContext(this);
        mref = new Firebase("https://myfirebase-f9e26.firebaseio.com/User");

        uploads = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                // progressDialog.dismiss();


                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                   // if(upload.getName().equalsIgnoreCase("Bangles"))
                     uploads.add(upload);
                    System.out.println(upload);
                }
                //creating adapter
                adapter = new MyAdapter(uploads);
                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }
                @Override
                public void onCancelled (DatabaseError databaseError){
                    //  progressDialog.dismiss();
                }

        });


  //      mlistview = (ListView) findViewById(R.id.listView);

     /*  final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUsername);
       mlistview.setAdapter(arrayAdapter);

        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

             String value=dataSnapshot.getValue().toString();


                mUsername.add(value);


                System.out.println(value);

               arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }*/
    }
}