package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "InventoryDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_INVENTORY = "Inventory";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUANTITY = "quantity";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_INVENTORY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        onCreate(db);
    }

    // Insert an item
    public void addItem(InventoryItem item) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, item.getName());
            values.put(COLUMN_QUANTITY, item.getQuantity());
            db.insert(TABLE_INVENTORY, null, values);
        }
    }

    // Get all items
    public List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();
        String query = "SELECT " + COLUMN_NAME + ", " + COLUMN_QUANTITY + " FROM " + TABLE_INVENTORY;

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery(query, null)) {

            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                int quantity = cursor.getInt(1);
                items.add(new InventoryItem(name, quantity));
            }
        }

        return items;
    }

    // Delete an item by name
    public void deleteItem(String name) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.delete(TABLE_INVENTORY, COLUMN_NAME + " = ?", new String[]{name});
        }
    }

    // Update an item's quantity
    public void updateItem(String name, int newQuantity) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_QUANTITY, newQuantity);
            db.update(TABLE_INVENTORY, values, COLUMN_NAME + " = ?", new String[]{name});
        }
    }
}
