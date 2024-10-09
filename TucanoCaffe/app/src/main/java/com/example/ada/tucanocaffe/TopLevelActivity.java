package com.example.ada.tucanocaffe;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class TopLevelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        FloatingActionButton myFab = (FloatingActionButton)findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onShareClick(v);
            }
        });
        myFab.setOnHoverListener(new View.OnHoverListener() {
            public boolean onHover(View v, MotionEvent m) {
                Toast t =  Toast.makeText(getApplicationContext(), "Shareeeeee....", Toast.LENGTH_SHORT);
                t.show();
                return true;
            }
        });

    }

    public void onSeeMenu(View view){
        Intent intent = new Intent (this, MenuOptionsActivity.class);
        startActivity(intent);
    }

    public void onShareClick(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");

        // Add data to the intent, the receiving app will decide what to do with it.
        intent.putExtra(Intent.EXTRA_SUBJECT, "lalal");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm ");
        startActivity(Intent.createChooser(intent, "How do you want to share?"));
    }
}
