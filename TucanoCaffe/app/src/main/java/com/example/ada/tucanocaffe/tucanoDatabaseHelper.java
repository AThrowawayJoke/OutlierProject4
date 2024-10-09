package com.example.ada.tucanocaffe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ada on 8/20/16.
 */
class tucanoDatabaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "tucano_db";
    private static final int db_version = 4;

    //    constructor of the SQLiteOpenHelper superclass
    tucanoDatabaseHelper(Context context){
        super(context, db_name, null, db_version);
        Log.v("INFO:", "in constructor of tucando db helper");

    }

    public void onCreate(SQLiteDatabase db) {
        Log.v("INFO:", "in on create...");
    //  updateMyDatabase(db, 0, db_version);
        createDB(db);
    }

    private static void insertProduct(SQLiteDatabase db, String name, String description, int resourceId, int category) {
        //       object ContentValues holds key-values pairs of data
        ContentValues coffeeValues = new ContentValues();
        coffeeValues.put("Name", name);
        coffeeValues.put("Description", description);
        coffeeValues.put("ImageResourceId", resourceId);
        coffeeValues.put("Category", category);
        //        we insert these into the Coffee table
        db.insert("Product", null, coffeeValues);

    }


    private static void insertOrder(SQLiteDatabase db, int tableNo, String coffeeName, String clientMessage){
        ContentValues orderValues = new ContentValues();
        orderValues.put("tableNo",tableNo);
        orderValues.put("coffeeName", coffeeName);
        orderValues.put("clientMessage", clientMessage);
        //        we insert these into the Coffee table
        db.insert("Orders", null, orderValues);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("INFO:", "in upgrade....");
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void createDB(SQLiteDatabase db) {
//      if the user does not have any db, it creates it=> calls onCreate
        Log.v("INFO:", "in create table product");
            db.execSQL("CREATE TABLE Product( "
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Name TEXT, "
                    + "Description TEXT, "
                    + "ImageResourceId INTEGER, "
                    + "Category INTEGER);");

            insertProduct(db, "Latte", "A couple of expresso shots with steamed milk", R.drawable.latte, Product.COFFEE_CATEGORY);
            insertProduct(db, "Cappuccino", "Espresso, hot milk and steamed milk foam", R.drawable.cappucino2, Product.COFFEE_CATEGORY);
            insertProduct(db, "Espresso", "Highest quality beans roasted and brewed fresh", R.drawable.espresso, Product.COFFEE_CATEGORY);
            insertProduct(db, "Mocha Frappe", "Foamy frappe, with minced ice and chocolate flavour", R.drawable.mocha_frappe, Product.COFFEE_CATEGORY);
            insertProduct(db, "Frapuccino", "Cold foamy frappe, with crubles of almonds and a touch of caramel", R.drawable.frappucino, Product.COFFEE_CATEGORY);
            insertProduct(db, "Machiatto", "Double espresso shots, rest is mink with pistachios top creamy foam.", R.drawable.machiatto, Product.COFFEE_CATEGORY);
            insertProduct(db, "Brownie", "Delicious and fluffy!", R.drawable.brownie, Product.SWEETS_CATEGORY);
            insertProduct(db, "Apple Crumble", "Crunchy crust of apple dipped in caramel, covered by crushed biscuits!", R.drawable.apple_crumble, Product.SWEETS_CATEGORY);
            insertProduct(db, "Berry Mousse", "Deliciously smoothly fresh berry mousse with berry fruits on top!", R.drawable.berry_mousse, Product.SWEETS_CATEGORY);
            insertProduct(db, "Lemon Cake", "An explosion of lemon taste with a tint of mint flavour in a combination of mousse and cake!", R.drawable.lemon_cake, Product.SWEETS_CATEGORY);
            insertProduct(db, "Cheesecake", "Best local fresh mixed cheese with the freshest fruits from grandmo's garden", R.drawable.cheesecake, Product.SWEETS_CATEGORY);
            insertProduct(db, "Raspberry YogPuddle", "A combination of delicately mixed yogurt and raspberry puddle, with minced fresh organic fruits!", R.drawable.raspberry_cream, Product.SWEETS_CATEGORY);



//      if the user has the db, but the version is lower than current which is 2
            Log.v("INFO:", "in create table order");
            db.execSQL("CREATE TABLE Orders( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TableNo INTEGER, "
                + "CoffeeName TEXT, "
                + "ClientMessage TEXT);");

            insertOrder(db, 1, "Cappucino", "Without sugar please!");
            insertOrder(db, 2, "Latte", "Without sugar please!");

    }

}