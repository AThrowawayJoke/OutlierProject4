package com.example.ada.tucanocaffe;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by ada on 8/14/16.
 */
public class AllProductsInCategoryActivity extends ListActivity{

    private SQLiteDatabase db;
    private Cursor cursor;
    public static final String EXTRA_CATEGORY = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_category);

        ListView coffeeCategoryListView = getListView();

        int category = (Integer)getIntent().getExtras().get(EXTRA_CATEGORY);

        try{

            SQLiteOpenHelper helperDb = new tucanoDatabaseHelper(this);
            db = helperDb.getReadableDatabase();
            cursor = db.query("Product",
                                    new String[] {"_id", "Name"},
                                    "category = ?",
                                    new String[] {Integer.toString(category)},
                                    null, null, null
            );


            CursorAdapter coffeeListAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"Name"},
                    new int[]{android.R.id.text1},
                    0);


        coffeeCategoryListView.setAdapter(coffeeListAdapter);

        }catch(SQLiteException e){

            Toast toast = Toast.makeText(this, "DB unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void onListItemClick(ListView listView, View itemView, int position, long id){

//        String productName = (String)listView.getItemAtPosition(position);
        Intent intent = new Intent(AllProductsInCategoryActivity.this, ProductActivity.class);

        intent.putExtra(ProductActivity.EXTRA_COFFEE_NO, (int) id);
//        intent.putExtra("productName", productName);
        startActivity(intent);

    }

    public void onBackToMenu(View view){

        Intent intent= new Intent(this, MenuOptionsActivity.class);
        startActivity(intent);
    }

}
