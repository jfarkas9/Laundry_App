package com.example.farkas.laundryapp.model;

import android.content.ContentValues;

import com.example.farkas.laundryapp.database.ItemsTable;

import java.util.UUID;

/**
 * Created by Farko on 11/15/2017.
 */

public class DataItem {
    private String itemId;
    private String itemName;
    private int defaultCount;
    private int currentCount;

    public DataItem() {
    }

    public DataItem(String itemId, String itemName, int defaultCount, int currentCount) {

        if (itemId == null) {
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.defaultCount = defaultCount;
        this.currentCount = currentCount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount(int defaultCount) {
        this.defaultCount = defaultCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(4);

        values.put(ItemsTable.COLUMN_ID, itemId);
        values.put(ItemsTable.COLUMN_NAME, itemName);
        values.put(ItemsTable.COLUMN_CURRENT_COUNT, currentCount);
        values.put(ItemsTable.COLUMN_DEFAULT_COUNT, defaultCount);

        return values;
    }
}
