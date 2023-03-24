package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Section1Question2 extends AppCompatActivity {
    private Button nextBtn;
    private Button prevBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1_question2);
        nextBtn = (Button) findViewById(R.id.section_1_question_2_next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage(view);
            }
        });

        prevBtn = (Button) findViewById(R.id.section_1_question_2_previous_button);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousPage(view);
            }
        });
    }

    public void nextPage(View v){
        Intent intent = new Intent(this, Section1Page1.class);
        startActivity(intent);
    }

    public void previousPage(View v) {
        finish();
    }
}