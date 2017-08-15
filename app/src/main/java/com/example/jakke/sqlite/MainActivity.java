package com.example.jakke.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView output;
    EditText input;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);
        dbHandler = new DBHandler(this, null, null, 1);
        printDatabase();
    }

    // Add a product to the database
    public void addButtonClicked(View view){
        Products products = new Products(input.getText().toString());
        dbHandler.addProduct(products);
        printDatabase();
    }
    // Delete item
    public void deleteButtonClicked(View view){
        String inputText = input.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        output.setText(dbString);
        input.setText("");
    }
}
