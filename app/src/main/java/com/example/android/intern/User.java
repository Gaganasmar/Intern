package com.example.android.intern;

/**
 * Created by HAPPY on 24/03/2017.
 */
public class User {
    private String Email;
    private String Password;
    private String FlatNo;
    public User(){
        //required for firebase database
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
       this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
    public String getFlatno() {
        return FlatNo;
    }

    public void setFlatno(String flatno) {
        this.FlatNo = flatno;
    }

}
