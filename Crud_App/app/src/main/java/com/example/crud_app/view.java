package com.example.crud_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class view extends AppCompatActivity {

//    Create the list variable
    ListView list_view;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

//        The db connection-- to read the data from
        SQLiteDatabase db = openOrCreateDatabase("Crud_Application_DB", Context.MODE_PRIVATE, null);
//        Assign the list_view variable
        list_view = findViewById(R.id.list_view);
        final Cursor c = db.rawQuery("select * from records", null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int course = c.getColumnIndex("course");
        int fees = c.getColumnIndex("fees");
        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
//        Load the data into the list
        list_view.setAdapter(arrayAdapter);
//        After student class
        final ArrayList<student> stud = new ArrayList<student>();
//        Condition to read data
        if (c.moveToFirst()) {
            do {
                student stu = new student();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.course = c.getString(course);
                stu.fees = c.getString(fees);
                stud.add(stu);
                titles.add(c.getString(id) + "\t" + c.getString(name) + "\t" + c.getString(course) + "\t" + c.getString(fees) + "\t");

            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list_view.invalidateViews();
        }
    }
}