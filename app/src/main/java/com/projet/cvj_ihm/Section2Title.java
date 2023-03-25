package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Section2Title extends AppCompatActivity {

    private Button nextBtn;
    private Button prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section2_title);
        nextBtn = (Button) findViewById(R.id.section_2_page_1_next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage(view);
            }
        });

        prevBtn = (Button) findViewById(R.id.section_2_page_1_previous_button);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousPage(view);
            }
        });
    }
    public void nextPage(View v){
        Intent intent = new Intent(this, Section2Question1.class);
        startActivity(intent);
    }
    public void previousPage(View v){
        Intent intent = new Intent(this, Section1Question5.class);
        startActivity(intent);
    }
}