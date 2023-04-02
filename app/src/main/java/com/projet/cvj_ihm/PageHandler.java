package com.projet.cvj_ihm;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PageHandler {

    public PageHandler(AppCompatActivity app, int nextBtnId, int prevBtnId, Class nextPage) {
        Button nextBtn = (Button) app.findViewById(nextBtnId);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPage(app, nextPage);
            }
        });

        Button prevBtn = (Button) app.findViewById(prevBtnId);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.finish();
            }
        });
    }

    public PageHandler(AppCompatActivity app, int nextBtnId, int prevBtnId, Class nextPage, int skipBtnId, Class skipPage) {
        Button nextBtn = (Button) app.findViewById(nextBtnId);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPage(app, nextPage);
            }
        });

        Button prevBtn = (Button) app.findViewById(prevBtnId);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.finish();
            }
        });

        Button skipBtn = (Button) app.findViewById(skipBtnId);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPage(app, skipPage);
            }
        });
    }

    public void goToPage(AppCompatActivity app, Class page) {
        Intent intent = new Intent(app, page);
        app.startActivity(intent);
    }
}
