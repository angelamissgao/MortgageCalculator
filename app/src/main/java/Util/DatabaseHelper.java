package Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by angelagao on 3/24/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME ="MortgageDB";
    public static final String TABLE_NAME = "student_table";
    public static final int DATABASE_VERSION = 1;

    public static final String COL_1 = "ID";
    public static final String COL_2 = "MONTHLYPAYMENT";
    public static final String COL_3 = "TOTALPAYMENT";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,MONTHLYPAYMENT TEXT,TOTALPAYMENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public String insertRecord(String paymentMonth, String paymentTotal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, paymentMonth);
        values.put(COL_3, paymentTotal);

        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return "Data not inserted";
        else
            return "Data Inserted successful";
    }
}
