package com.alliance.foodintern.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqlDataBaseAdapter
{
    static final String KEY_OrderID = "order_id";
    static final String KEY_FoodName = "food_name";
    static final String KEY_FoodDescription = "food_desc";
    static final String KEY_Discount = "food_discount";
    static final String KEY_TotalPrice = "food_price";
    static final String KEY_NumberOfItems= "food_items";
    static final String KEY_IMAGE= "food_image";

    static final String TAG = "SqlDataBaseAdapter";

    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "foodDetails";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE ="create table foodDetails(order_id integer primary key autoincrement,food_name text not null, food_desc text not null, food_discount int not null, food_price text not null ,food_items integer not null,food_image text not null );";

    private final Context ctx;
    private DatabaseHelper DbHelper;
    private SQLiteDatabase db;

    public SqlDataBaseAdapter(Context c){
        this.ctx = c;
        DbHelper = new DatabaseHelper(c);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db)
        {
                try {
                    db.execSQL(DATABASE_CREATE);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            //Drops table if Exists
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

    }


    public SqlDataBaseAdapter open()throws SQLException
    {
        DbHelper = new DatabaseHelper(ctx);
        db = DbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        DbHelper.close();
    }
    public long insert(String name,String des,Integer discount,String price,Integer no,String image)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_FoodName, name);
        initialValues.put(KEY_FoodDescription, des);
        initialValues.put(KEY_Discount, discount);
        initialValues.put(KEY_TotalPrice, price);
        initialValues.put(KEY_NumberOfItems, no);
        initialValues.put(KEY_IMAGE,image);

        return db.insert(DATABASE_TABLE, null, initialValues);

    }


    public Cursor retrieve()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_OrderID, KEY_FoodName,KEY_FoodDescription,KEY_Discount,
                KEY_TotalPrice,KEY_NumberOfItems,KEY_IMAGE}, null, null, null, null, null);
    }

}
