package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Section2Page5 extends AppCompatActivity {
    private PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section2_page5);

        ph = new PageHandler(
                this,
                R.id.section2_page5_screen_next_btn,
                R.id.section2_page5_screen_previous_btn,
                Section3Title.class
        );
    }
}