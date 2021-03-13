package com.example.arnabsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean isShowingAnswers = true;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView flashcardquestion = findViewById(R.id.flashcard_question);
        TextView flashcardanswer = findViewById(R.id.flashcard_answer);
        TextView flashcardanswer1 = findViewById(R.id.flashcard_answer1);
        TextView flashcardanswer2 = findViewById(R.id.flashcard_answer2);
        TextView flashcardanswer3 = findViewById(R.id.flashcard_answer3);
        ImageView eyebutton =  (findViewById(R.id.toggle_choices_visibility));
        ImageView plusbutton = (findViewById(R.id.newcard));
        ImageView nextbutton = (findViewById(R.id.nextbutton));


        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // don't try to go to next card if you have no cards to begin with
                if (allFlashcards.size() == 0)
                    return;
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if(currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(findViewById(R.id.flashcard_question),
                            "You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard.getAnswer());
                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
            }
        });

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

        plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, AddCardActivityMain.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });


        
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
                } else {
                    flashcardanswer1.setVisibility(View.INVISIBLE);
                    flashcardanswer2.setVisibility(View.INVISIBLE);
                    flashcardanswer3.setVisibility(View.INVISIBLE);
                    eyebutton.setImageResource(R.drawable.show_icon);
                    isShowingAnswers = true;





                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data !=null) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("string2");

            TextView flashcardquestion= findViewById(R.id.flashcard_question);
            flashcardquestion.setText(question);
            TextView flashcardanswer = findViewById(R.id.flashcard_answer);
            flashcardanswer.setText(answer);

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);

            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }
}