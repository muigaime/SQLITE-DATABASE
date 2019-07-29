package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText mFname, mLname,mID;
    Button mSave,mView,mDelete;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFname = findViewById(R.id.edtFname);
        mLname = findViewById(R.id.edtLname);
        mID = findViewById(R.id.edtId);
        mSave = findViewById(R.id.bnSave);
        mView = findViewById(R.id.bnView);
        mDelete = findViewById(R.id.bnDelete);

        //Create the database
        db = openOrCreateDatabase("huduma",MODE_PRIVATE,null);

        //Create a table in your database
        db.execSQL("CREATE TABLE IF NOT EXISTS citizens(first_name VARCHAR, last_name VARCHAR, id_number INTEGER)");

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the data from the user
                String firstname = mFname.getText().toString();
                String lastname = mLname.getText().toString();
                String idnumber = mID.getText().toString();

                //check if the user is attempting to submit empty fields
                if (firstname.isEmpty()){
                    messages("First Name error","Please Enter the last name");
                }else if (lastname.isEmpty()){
                    messages("Last Name Error","Please Enter the last name");
                }else if (idnumber.isEmpty()){
                    messages("Id number error","Please Enter the Id number");
                }else {
                    //Proceed to save the received data into your db called huduma
                    //Insert data into DB using an internet Query

                    db.execSQL("INSERT INTO citizens VALUES ('"+firstname+"','"+lastname+"','"+idnumber+"')");
                    messages("Success","User Saved Successfully");
                    //Clear input fields in the next entry
                    mFname.setText("");
                    mLname.setText("");
                    mID.setText("");

                }

            }
        });
    }
    //message display function
    public void messages(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.create().show();
    }
}
