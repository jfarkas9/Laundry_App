package com.example.farkas.laundryapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.farkas.laundryapp.model.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farko on 9/23/2017.
 */

public class DataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public DataItem createItem(DataItem item) {
        ContentValues values = item.toValues();
        mDatabase.insert(ItemsTable.TABLE_ITEMS, null, values);
        return item;
    }

    public void deleteItem(DataItem item){
        ContentValues values = item.toValues();
        mDatabase.delete(ItemsTable.TABLE_ITEMS, "itemName = \"" + item.getItemName() + "\"", null);

    }

    //Use for deleting all data
    public void deleteAll(){
        // mDatabase.execSQL(ItemsTable.SQL_DELETE);
//        String sqlDelete = ;
        mDatabase.execSQL(ItemsTable.SQL_DELETE);
        mDatabase.close();
    }

    public void open() {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public long getDataItemsCount(){
        return DatabaseUtils.queryNumEntries(mDatabase, ItemsTable.TABLE_ITEMS);
    }

    public void seedDatabase(List<DataItem> dataItemList) {
        long numItems = getDataItemsCount();
        if (numItems == 0) {
            for (DataItem item :
                    dataItemList) {
                try {
                    createItem(item);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<DataItem> getAllItems(){
        List<DataItem> dataItems = new ArrayList<>();
        Cursor cursor = mDatabase.query(ItemsTable.TABLE_ITEMS, ItemsTable.ALL_COLUMNS, null, null, null, null, ItemsTable.COLUMN_NAME);

        while (cursor.moveToNext()) {
            DataItem item = new DataItem();
            item.setItemId(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_ID)));
            item.setItemName(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_NAME)));
            item.setDefaultCount(cursor.getInt(
                    cursor.getColumnIndex(ItemsTable.COLUMN_DEFAULT_COUNT)));
            item.setCurrentCount(cursor.getInt(
                    cursor.getColumnIndex(ItemsTable.COLUMN_CURRENT_COUNT)));
            dataItems.add(item);
        }
        cursor.close();
        return dataItems;

    }
}
