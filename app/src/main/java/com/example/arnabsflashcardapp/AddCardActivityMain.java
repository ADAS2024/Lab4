package com.example.arnabsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCardActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_main);
        ImageView cancelbutton = ((ImageView) findViewById(R.id.cancel));
        ImageView savebutton = ((ImageView) findViewById(R.id.save));


        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelbutton.setVisibility(View.VISIBLE);
                finish();
            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.editTextQuestion)).getText().toString();
                String answer = ((EditText) findViewById(R.id.editTextAnswer)).getText().toString();
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("string1", question); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", answer); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });

    }
}