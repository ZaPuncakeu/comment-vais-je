package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Section1Page1 extends AppCompatActivity {
    PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1_page1);
        ph = new PageHandler(
                this,
                R.id.section1_page1_screen_next_btn,
                R.id.section1_page1_screen_previous_btn,
                Section1Page2.class
        );
    }
}