package com.example.farkas.laundryapp.database;

public class ItemsTable {
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "itemId";
    public static final String COLUMN_NAME = "itemName";
    public static final String COLUMN_DEFAULT_COUNT = "defaultCount";
    public static final String COLUMN_CURRENT_COUNT = "currentCount";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_NAME,
            COLUMN_DEFAULT_COUNT, COLUMN_CURRENT_COUNT};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DEFAULT_COUNT + " INTEGER," +
                    COLUMN_CURRENT_COUNT + " INTEGER" + ");";
    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;


}