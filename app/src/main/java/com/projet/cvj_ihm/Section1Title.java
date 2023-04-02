package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Section1Title extends AppCompatActivity {

    PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1_title);
        ph = new PageHandler(
                this,
                R.id.section1_title_next_btn,
                R.id.section1_title_previous_btn,
                Section1Page1.class
        );
    }
}