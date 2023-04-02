package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Section3Page1 extends AppCompatActivity {
    private PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section3_page1);

        ph = new PageHandler(
                this,
                R.id.section3_page1_screen_view_summary_btn,
                R.id.section3_page1_screen_previous_btn,
                Summary.class
        );
    }
}