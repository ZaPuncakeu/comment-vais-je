package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Section1Page2 extends AppCompatActivity {
    PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1_page2);
        ph = new PageHandler(
                this,
                R.id.section1_page2_screen_next_btn,
                R.id.section1_page2_screen_previous_btn,
                Section2Title.class
        );
    }
}