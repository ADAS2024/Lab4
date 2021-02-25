package com.example.arnabsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView flashcardquestion = findViewById(R.id.flashcard_question);
        TextView flashcardanswer = findViewById(R.id.flashcard_answer);
        TextView flashcardanswer1 = findViewById(R.id.flashcard_answer1);
        TextView flashcardanswer2 = findViewById(R.id.flashcard_answer2);
        TextView flashcardanswer3 = findViewById(R.id.flashcard_answer3);
        ImageView eyebutton =  ((ImageView) findViewById(R.id.toggle_choices_visibility));


        flashcardquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardquestion.setVisibility(View.INVISIBLE);
                flashcardanswer.setVisibility(View.VISIBLE);

            }
        });

        flashcardanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardquestion.setVisibility(View.VISIBLE);
                flashcardanswer.setVisibility(View.INVISIBLE);
            }
        });

        flashcardanswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardanswer1.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        flashcardanswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardanswer1.setBackgroundColor(getResources().getColor(R.color.green, null));
                flashcardanswer2.setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });


        flashcardanswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardanswer3.setBackgroundColor(getResources().getColor(R.color.red, null));
                flashcardanswer1.setBackgroundColor(getResources().getColor(R.color.green,null));
            }
        });


        eyebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eyebutton.setImageResource(R.drawable.hide_icon);
                if (isShowingAnswers) {
                    flashcardanswer1.setVisibility(View.VISIBLE);
                    flashcardanswer2.setVisibility(View.VISIBLE);
                    flashcardanswer3.setVisibility(View.VISIBLE);
                    eyebutton.setImageResource(R.drawable.hide_icon);
                    isShowingAnswers = false;
                } else if (!isShowingAnswers) {
                    flashcardanswer1.setVisibility(View.INVISIBLE);
                    flashcardanswer2.setVisibility(View.INVISIBLE);
                    flashcardanswer3.setVisibility(View.INVISIBLE);
                    eyebutton.setImageResource(R.drawable.show_icon);
                    isShowingAnswers = true;





                }
            }
        });
    }
}