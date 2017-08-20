package com.example.android.intern;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class DisplayMessageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView edittext1,edittext2;
   de.hdodenhof.circleimageview.CircleImageView buttonLoadImage;
Bitmap bitmap;

    static  final int SELECT_FILE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        edittext1 = (TextView) findViewById(R.id.textView);
        edittext2 = (TextView) findViewById(R.id.textView4);
        edittext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TextView myText = (TextView) view;
                //     Toast.makeText(this, "You selected " + edittext.getText().toString(), Toast.LENGTH_SHORT).show();
                if (edittext1.getText().toString().equals("Maintainance Details")) {
                    Intent intent = new Intent(DisplayMessageActivity.this, Electricity.class);

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
                    Intent intent = new Intent(DisplayMessageActivity.this, Salary.class);

                    startActivity(intent);

                }
            }

        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tree);
        View headerLayout=navigationView.getHeaderView(0);

       buttonLoadImage = (de.hdodenhof.circleimageview.CircleImageView)headerLayout.findViewById(R.id.circleView);
        buttonLoadImage.setClickable(true);
       buttonLoadImage.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                //Toast.makeText(this, "youhavent picked", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(), "jj", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                return;
                //  startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
            }


        });
    }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            //super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECT_FILE)
                    onSelectFromGalleryResult(data);


            }

        }
        @SuppressWarnings("depreciation")
        private void onSelectFromGalleryResult (Intent data)
        {
            Bitmap bm = null;
            if (data != null) {
                try {
                    bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            buttonLoadImage.setImageBitmap(bm);
        }

    @Override
    public void onBackPressed() {
       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
      //  finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
           // Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
