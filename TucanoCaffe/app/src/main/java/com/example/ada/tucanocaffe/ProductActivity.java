package com.example.ada.tucanocaffe;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Spinner;


public class ProductActivity extends Activity {

    public static final String EXTRA_COFFEE_NO = "coffeeNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // get id of coffee from intent + the coffee object with that id
        int coffeeNo = (Integer)getIntent().getExtras().get(EXTRA_COFFEE_NO);
//        String productName = getIntent().getStringExtra("productName");

        try{
//          create a hel[per object
            SQLiteOpenHelper tucanoDatabaseHelper = new tucanoDatabaseHelper(this);
            SQLiteDatabase db = tucanoDatabaseHelper.getReadableDatabase();
//          the query method returns a cursor object, from which we extract data to display in Activities
            Cursor cursor = db.query("Product",
                    new String[] {"Name", "Description", "ImageResourceId"},
                    "_id = ?",
                    new String[] {Integer.toString(coffeeNo)},
                    null, null, null
            );

//          navigate through the cursor for data
            if (cursor.moveToFirst()){

                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageId = cursor.getInt(2);

                // populate the drink image
                ImageView coffeeImageView = (ImageView) findViewById(R.id.image);
                coffeeImageView.setImageResource(imageId);
                coffeeImageView.setContentDescription(nameText);

                TextView coffeeName = (TextView) findViewById(R.id.name);
                coffeeName.setText(nameText);

//                TextView coffeePrice = (TextView) findViewById(R.id.price);
//                coffeePrice.setText(String.valueOf(coffeePrice);

                TextView coffeeDescrip = (TextView) findViewById(R.id.description);
                coffeeDescrip.setText(descriptionText);

            }
            cursor.close();
            db.close();

        }catch (SQLiteException e){

            Toast toast = Toast.makeText(this, "DB UNavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onSendOrder(View view){

        int coffeeNo = (Integer)getIntent().getExtras().get(EXTRA_COFFEE_NO);

        TextView productNameView = (TextView)findViewById(R.id.name);
        String productName = productNameView.getText().toString();

        EditText clientMessageView = (EditText)findViewById(R.id.clientMessage);
        String clientMessage = clientMessageView.getText().toString();

        Spinner tableNumsArray = (Spinner) findViewById(R.id.tableNums); // gives me an array
        String tableNum = tableNumsArray.getSelectedItem().toString();

        Intent sendOrderIntent = new Intent(ProductActivity.this, OrderActivity.class);
        sendOrderIntent.putExtra("productName", productName);
        sendOrderIntent.putExtra("clientMessage", clientMessage);
        sendOrderIntent.putExtra("tableNumber", tableNum);
        sendOrderIntent.putExtra("coffeeId", Integer.toString(coffeeNo));
        startActivity(sendOrderIntent);

    }


    public void onBackToProductsCategory(View view){

        Intent intent = new Intent(this, AllProductsInCategoryActivity.class);

        startActivity(intent);

    }

}
