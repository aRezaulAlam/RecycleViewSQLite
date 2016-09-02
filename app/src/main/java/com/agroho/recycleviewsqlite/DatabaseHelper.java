package com.agroho.recycleviewsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rezaul on 2016-08-31.
 * arezaulalam@gmail.com
 * www.agroho.com
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "product.db";
    public static final String TABLE_NAME = "product_table";

    public static final String product_id = "product_id";
    public static final String product_name = "product_name";
    public static final String product_category = "product_category";
    public static final String product_location = "product_location";
    public static final String product_price = "product_price";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableSql = "create table "+TABLE_NAME+" ("+product_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+product_name+" TEXT, "+
                product_category+" TEXT, "+product_location+" TEXT, "+product_price+" INTEGER) ";

        sqLiteDatabase.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertProductData(Product product){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(product_name,product.getProductName());
        contentValues.put(product_category,product.getProductCategory());
        contentValues.put(product_location,product.getProductLocation());
        contentValues.put(product_price,product.getProductPrice());

        Log.d("product_name", product.getProductName());

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<Product> getAllProductList(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        ArrayList<Product> products = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            Product product = new Product();

            product.setProductName(cursor.getString(cursor.getColumnIndex(product_name)));
            product.setProductCategory(cursor.getString(cursor.getColumnIndex(product_category)));
            product.setProductLocation(cursor.getString(cursor.getColumnIndex(product_location)));
            product.setProductPrice(cursor.getInt(cursor.getColumnIndex(product_price)));

            products.add(product);
            cursor.moveToNext();

        }

        return products;

    }
}
