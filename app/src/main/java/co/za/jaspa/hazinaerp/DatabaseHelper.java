package co.za.jaspa.hazinaerp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {

    private  Context context;
    private static final String DATABASE_NAME ="HazinaERP.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE customers (id INTEGER PRIMARY KEY AUTOINCREMENT, fullNames TEXT, contactNumber TEXT, emailAddress TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS customers");
        onCreate(db);
    }
}
