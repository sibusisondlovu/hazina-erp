package co.za.jaspa.hazinaerp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    private void createCustomer(String customerName, String contactNumber, String emailAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("fullNames",customerName);
        cv.put("contactNumber",contactNumber);
        cv.put("emailAddress",emailAddress);

        long result = db.insert("customers",null,cv);

        if (result == -1) {
            Toast.makeText(context, "Failed to create new customer", Toast.LENGTH_SHORT).show();
        }else{
            //create customer order
            Toast.makeText(context, "Customer created successfully,... creating customer order...", Toast.LENGTH_SHORT).show();
        }

    }

    private void createOrder(String style, String fittingDate, String collectionDate, double deposit, double totalCost, int customerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("customerId",customerId);
        cv.put("style",style);
        cv.put("fittingDate",fittingDate);
        cv.put("collectionDate",collectionDate);
        cv.put("totalCost",totalCost);
        cv.put("depositPaid",deposit);

        long result = db.insert("orders",null,cv);

        if (result == -1) {
            Toast.makeText(context, "Failed to create new order", Toast.LENGTH_SHORT).show();
        }else{
            //create customer order
            Toast.makeText(context, "Order created successfully,... adding customer measurements to order...", Toast.LENGTH_SHORT).show();
        }

    }
}
