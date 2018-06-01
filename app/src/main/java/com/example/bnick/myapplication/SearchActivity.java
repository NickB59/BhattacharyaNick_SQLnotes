package com.example.bnick.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get the intent that started the activity
        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Set the string in TextView
        android.widget.TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
    }
    public void goBack(View v){
        Log.d("MyContactApp","SearchActivity: launching MainActivity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
