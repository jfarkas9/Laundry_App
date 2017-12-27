package com.example.farkas.laundryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farkas.laundryapp.database.DBHelper;
import com.example.farkas.laundryapp.database.DataSource;
import com.example.farkas.laundryapp.model.DataItem;

public class AddItem extends AppCompatActivity {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }
    DataSource mDataSource;
    public void addItem(View view) {
        DataItem item = new DataItem();
        item.setItemName(((EditText) findViewById(R.id.nameInput)).getText().toString());
        item.setCurrentCount(Integer.parseInt(((EditText) findViewById(R.id.currentInput)).getText().toString()));
        item.setDefaultCount(Integer.parseInt(((EditText) findViewById(R.id.defaultInput)).getText().toString()));

        //Surround in try block incase of unsuccessful addition?
        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.createItem(item);
        mDataSource.close();
        Toast.makeText(this, ("Successful Addition: " + item.getItemName()), Toast.LENGTH_SHORT).show();
        this.finish();


    }
}
