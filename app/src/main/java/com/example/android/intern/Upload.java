package com.example.android.intern;

/**
 * Created by Kartheeka on 3/5/2017.
 */

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/23/2017.
 */
@IgnoreExtraProperties
public class Upload{



    public String email;
    public String flatno;
    public String bill;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String email,  String flatno,String bill) {
        this.email = email;

        this.flatno=flatno;
        this.bill=bill;

    }





    public String getEmail(){return email;}
    public String getFlatno(){return flatno;}
    public String getBill(){return bill;}
}
