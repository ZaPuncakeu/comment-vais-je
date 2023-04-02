package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Section2Title extends AppCompatActivity {

    private PageHandler ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section2_title);
        ph = new PageHandler(
                this,
                R.id.section2_title_screen_next_btn,
                R.id.section2_title_screen_previous_btn,
                Section2Page1.class,
                R.id.section2_title_screen_skip_btn,
                Section3Title.class
        );
    }
}