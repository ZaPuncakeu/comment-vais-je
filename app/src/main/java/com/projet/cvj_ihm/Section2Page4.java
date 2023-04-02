package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Section2Page4 extends AppCompatActivity {
    private PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section2_page4);

        ph = new PageHandler(
                this,
                R.id.section2_page4_screen_next_btn,
                R.id.section2_page4_screen_previous_btn,
                Section2Page5.class
        );
    }
}