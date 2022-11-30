package com.example.crud_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    Creating the variables for the input fields and buttons
    EditText ed1, ed2, ed3;
    Button b1, b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //    Assign the values
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.course);
        ed3 = findViewById(R.id.fees);

        b1 = findViewById(R.id.btn_add);
        b2 = findViewById(R.id.btn_view);


        //    Setting the codes for execution
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            Call the insert function
                insert();
            }
        });
//        Linking the view button
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });

    }
//    Method to insert
    public void insert () {
        try {
//            Get the values in the text boxes and convert them to string
            String name = ed1.getText().toString();
            String course = ed2.getText().toString();
            String fees = ed3.getText().toString();
//            Database connection
            SQLiteDatabase db = openOrCreateDatabase("Crud_Application_DB", Context.MODE_PRIVATE, null);
//            Tables
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, course VARCHAR, fees VARCHAR)");
//            Sql query
            String sql = "insert into records(name, course, fees) values(?, ?, ?)";
//            Sql statements
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, course);
            statement.bindString(3, fees);
            statement.execute();
//            Show success message upon success addition
            Toast.makeText(this, "Course details added successfully...", Toast.LENGTH_LONG).show();

        }
        catch (Exception ex) {
//            Show error message upon failed addition
            Toast.makeText(this, "Failed to add course details!!!", Toast.LENGTH_LONG).show();
        }
    }


}