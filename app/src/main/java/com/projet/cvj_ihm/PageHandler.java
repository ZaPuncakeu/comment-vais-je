package com.projet.cvj_ihm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PageHandler {

    public interface ConditionalNextCallback {
        Person callback();
    }


    public PageHandler(SharedPreferences sharedPref, int currentStep, AppCompatActivity app, int nextBtnId, int prevBtnId, Class nextPage, int skipBtnId, Class skipPage, int skipStep) {

        Button nextBtn = (Button) app.findViewById(nextBtnId);
        Person person = app.getIntent().getParcelableExtra("person");

        boolean skipped = sharedPref.getBoolean("skipped", false);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("step", currentStep + 1).commit();
                sharedPref.edit().putBoolean("skipped", false).commit();
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
                sharedPref.edit().putInt("step", skipStep).commit();
                sharedPref.edit().putBoolean("skipped", true).commit();
                goToPage(app, skipPage, person);
            }
        });

        if(sharedPref.getInt("step", 0) != currentStep) {
            if(!skipped)
                goToPage(app, nextPage, person);
            else
                goToPage(app, skipPage, person);
        }
    }

    public void goToPage(AppCompatActivity app, Class page, Person person) {

        Intent intent = new Intent(app, page);
        if(person != null) {
            intent.putExtra("person", person);
        }
        app.startActivity(intent);
    }

    public PageHandler(SharedPreferences sharedPref, int currentStep, AppCompatActivity app, int nextBtnId, int prevBtnId, Class nextPage, ConditionalNextCallback condition) {
        Button nextBtn = (Button) app.findViewById(nextBtnId);

        LanguageManager.setLanguage(app);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = condition.callback();
                if(person == null) {
                    return;
                }
                sharedPref.edit().putInt("step", currentStep + 1).commit();
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

        if(sharedPref.getInt("step", 0) != currentStep) {

            Person person = condition.callback();
            if(person == null)
            {
                sharedPref.edit().putInt("step", currentStep).commit();
                return;
            }
            person.Log();
            goToPage(app, nextPage, person);
        }
    }


}
