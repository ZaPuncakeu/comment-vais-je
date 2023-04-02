package com.projet.cvj_ihm;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PageHandler {

    public interface ConditionalNextCallback {
        Person callback();
    }

    public PageHandler(AppCompatActivity app, int nextBtnId, int prevBtnId, Class nextPage, int skipBtnId, Class skipPage) {
        Button nextBtn = (Button) app.findViewById(nextBtnId);
        Person person = app.getIntent().getParcelableExtra("person");
        LanguageManager.setLanguage(app);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPage(app, nextPage, person);
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
                goToPage(app, skipPage, person);
            }
        });
    }

    public void goToPage(AppCompatActivity app, Class page, Person person) {

        Intent intent = new Intent(app, page);
        if(person != null) {
            intent.putExtra("person", person);
        }
        app.startActivity(intent);
    }

    public PageHandler(AppCompatActivity app, int nextBtnId, int prevBtnId, Class nextPage, ConditionalNextCallback condition) {
        Button nextBtn = (Button) app.findViewById(nextBtnId);

        LanguageManager.setLanguage(app);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = condition.callback();
                if(person == null) {
                    return;
                }
                goToPage(app, nextPage, person);
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


}
