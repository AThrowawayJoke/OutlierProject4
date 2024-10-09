package com.example.ada.tucanocaffe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MenuOptionsActivity extends Activity {

    private static final String TAG = "MenuOptionsActivity";
    // Create the category map to check for correct values
    private final Map<String, Integer> categoryMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_options);

        // Insert expected values into the category mapping
        categoryMap.put("Coffees", Product.COFFEE_CATEGORY);
        categoryMap.put("Sweets", Product.SWEETS_CATEGORY);

        ListView productsListMenu = findViewById(R.id.listProductsMenu);
        productsListMenu.setOnItemClickListener((listView, itemView, position, id) -> {
            try {
                String selectedText = listView.getItemAtPosition(position).toString();
                // Get the category from the mapping using selectedText as the key
                Integer category = categoryMap.get(selectedText);

                // Check for an incorrect category
                if (category != null) {
                    Intent intent = new Intent(MenuOptionsActivity.this, AllProductsInCategoryActivity.class);
                    intent.putExtra(AllProductsInCategoryActivity.EXTRA_CATEGORY, category);
                    startActivity(intent);
                } else {
                    // Handle incorrect categories
                    Toast.makeText(this, "Category not found", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Invalid category: " + selectedText);
                }

            } catch (Exception e) {
                // Handle other unexpected exceptions
                Log.e(TAG, "Error handling item click", e);
                Toast.makeText(this, "An error occurred during item click handling", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackToMain(View view) {
        try {
            Intent intent = new Intent(this, TopLevelActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            // Handle unexpected errors when returning to main
            Log.e(TAG, "Error navigating back to main", e);
            Toast.makeText(this, "An error occurred whilst going back to main", Toast.LENGTH_SHORT).show();
        }
    }
}