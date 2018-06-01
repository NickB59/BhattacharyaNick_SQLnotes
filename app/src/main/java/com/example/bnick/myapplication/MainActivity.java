package com.example.bnick.myapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editName;
    EditText editNumber;
    EditText editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editNumber = findViewById(R.id.editText_number);
        editAddress = findViewById(R.id.editText_address);

        myDB = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated myDb");
    }

    public void addData(View view){
        Log.d("MyContactApp", "MainActivity: instantiated myDb");

        boolean NameInserted = myDB.insertData(editName.getText().toString(), editNumber.getText().toString(),editAddress.getText().toString());
        if(NameInserted==true){
            Toast.makeText(MainActivity.this, "Success - contact inserted!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "FAILED - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData (View view){
        Cursor res = myDB.getAllData();
        Log.d("MyContactApp", "MainActivity: viewData: received cursor");

        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            //Append res column 0,1,2,3 to the buffer, delimited by "/n"
            buffer.append(res.getColumnName(res.getPosition()) + "/n");

        }
        Log.d("MyContactApp", "MainActivity: viewData: assembled stringBuffer");
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        Log.d("MyContactApp", "MainActivity: showMessage: assembling AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
    public static final String EXTRA_MESSAGE = "com.example.nickbhattacharya.mycontactapp_px.MESSAGE";
    public void searchRecord(View view){
        Log.d("MyContactApp", "MainActivity: showMessage: Launching SearchActivity");
        android.content.Intent intent = new android.content.Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, editName.getText().toString());
        startActivity(intent);
    }
}
