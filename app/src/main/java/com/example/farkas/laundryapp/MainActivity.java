package com.example.farkas.laundryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.farkas.laundryapp.database.DataSource;
import com.example.farkas.laundryapp.model.DataItem;
import com.example.farkas.laundryapp.sample.SampleDataProvider;
import com.example.farkas.laundryapp.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    List<DataItem> dataItemList = SampleDataProvider.dataItemList;
    List<DataItem> dataItemList = new ArrayList<>();

    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_list);

        setUpList();
    }

    private void setUpList(){
        //list was not refreshing when returning fom add item
        //so implemented it like this and added it to the onResume
        //less resource heavy way to updateList?
        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.seedDatabase(dataItemList);

        List<DataItem> listFromDB = mDataSource.getAllItems();
        DataItemAdapter adapter = new DataItemAdapter(this, listFromDB);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //generic options menu
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        //Add item activity
        }else if (id == R.id.add_item) {
            Intent intent = new Intent(this, AddItem.class);
            startActivity(intent);
            return true;//}
//        Clear all data to be implemented later
         }else if(id == R.id.clear_database){
//            insert alert dialog
//            This method crashes the app
//             error: cannot find table
            //mDataSource.deleteAll();
            //setUpList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Refresh the list
        setUpList();
        mDataSource.open();
    }
}
