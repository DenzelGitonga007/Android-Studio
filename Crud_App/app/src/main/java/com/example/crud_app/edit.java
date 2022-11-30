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

public class edit extends AppCompatActivity {
    //    Creating the variables for the input fields and buttons
    EditText ed1, ed2, ed3, ed4;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //    Assign the values
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.course);
        ed3 = findViewById(R.id.fees);
        ed4 = findViewById(R.id.id);

        b1 = findViewById(R.id.btn_edit);
        b2 = findViewById(R.id.btn_delete);
        b3 = findViewById(R.id.btn_back);

//        Receiving the data from the view
        Intent i = getIntent();
        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("course").toString();
        String t4 = i.getStringExtra("fees").toString();

//        Display the values onto the text boxes
        ed4.setText(t1); //id
        ed1.setText(t2); //name
        ed2.setText(t3); //course
        ed3.setText(t4); //fees

//        Delete button
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Call the function
                Delete();
            }
        });

//        Back button action
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Go back to the view page
               Intent i = new Intent(getApplicationContext(),view.class);
               startActivity(i);
            }
        });

//        Edit Button action
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Call the edit function
                    edit();
            }
        });
    }
    //    Delete function
    public void Delete () {
        try {
//            Get the values in the text boxes and convert them to string
            String id = ed4.getText().toString();
//            Database connection
            SQLiteDatabase db = openOrCreateDatabase("Crud_Application_DB", Context.MODE_PRIVATE, null);
//            Sql update query
            String sql = "delete from records where id = ?";
//            Sql statements
            SQLiteStatement statement = db.compileStatement(sql);
//                Add id
            statement.bindString(1, id);
            statement.execute();
//            Show success message upon success addition
            Toast.makeText(this, "Course details deleted successfully...", Toast.LENGTH_LONG).show();
//            Clearing the texts after inserting
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
//            Set the focus to be on the first input
            ed1.requestFocus();

        }
        catch (Exception ex) {
//            Show error message upon failed addition
            Toast.makeText(this, "Failed to delete course details!!!", Toast.LENGTH_LONG).show();
        }
    }//End of delete function

//    Edit function
    public void edit () {
        try {
//            Get the values in the text boxes and convert them to string
            String name = ed1.getText().toString();
            String course = ed2.getText().toString();
            String fees = ed3.getText().toString();
            String id = ed4.getText().toString();
//            Database connection
            SQLiteDatabase db = openOrCreateDatabase("Crud_Application_DB", Context.MODE_PRIVATE, null);
//            Sql update query
            String sql = "update records set name = ?, course = ?, fees = ? where id = ?";
//            Sql statements
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, course);
            statement.bindString(3, fees);
//                Add id
            statement.bindString(4, id);
            statement.execute();
//            Show success message upon success addition
            Toast.makeText(this, "Course details updated successfully...", Toast.LENGTH_LONG).show();
//            Clearing the texts after inserting
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
//            Set the focus to be on the first input
            ed1.requestFocus();

        }
        catch (Exception ex) {
//            Show error message upon failed addition
            Toast.makeText(this, "Failed to add course details!!!", Toast.LENGTH_LONG).show();
        }
    }//End of edit function
}