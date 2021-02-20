package com.example.arnabsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dalvik.annotation.TestTargetClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardquestion = findViewById(R.id.flashcard_question);
        TextView flashcardanswer = findViewById(R.id.flashcard_answer);
        flashcardquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardquestion.setVisibility(View.INVISIBLE);
                flashcardanswer.setVisibility(View.VISIBLE);
            }
        });
    }
}