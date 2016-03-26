package Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.Mortgage;

/**
 * Created by angelagao on 3/24/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME ="MortgageDB";
    public static final String TABLE_NAME = "MortGageResult";
    public static final int DATABASE_VERSION = 1;

    public static final String COL_1 = "ID";
    public static final String COL_2 = "MONTHLYPAYMENT";
    public static final String COL_3 = "TOTALPAYMENT";
    public static final String COL_4 = "PAYOFFDATE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,MONTHLYPAYMENT TEXT,TOTALPAYMENT TEXT, PAYOFFDATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public String insertRecord(Mortgage mortgage) {
        String paymentMonth = String.valueOf(mortgage.getMonthlyRecord());
        String paymentTotal = String.valueOf(mortgage.getTotalRecord());
        String payoffDate = mortgage.getPayoffDateRecord();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, paymentMonth);
        values.put(COL_3, paymentTotal);
        values.put(COL_4,payoffDate);

        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return "Data not inserted";
        else
            return "Data Inserted successful";
    }
}
