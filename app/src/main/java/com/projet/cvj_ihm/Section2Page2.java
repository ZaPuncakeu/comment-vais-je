package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

public class Section2Page2 extends AppCompatActivity {

    private PageHandler ph;
    private ImageButton[] reactions;
    int selected = -1;
    private final int currentStep = 7;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section2_page2);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        reactions = new ImageButton[5];
        reactions[0] = (ImageButton) findViewById(R.id.section2_page2_screen_sad_reaction);
        reactions[1] = (ImageButton) findViewById(R.id.section2_page2_screen_happy_reaction);
        reactions[2] = (ImageButton) findViewById(R.id.section2_page2_screen_anger_reaction);
        reactions[3] = (ImageButton) findViewById(R.id.section2_page2_screen_scared_reaction);
        reactions[4] = (ImageButton) findViewById(R.id.section2_page2_screen_neutral_reaction);

        selected = sharedPref.getInt("selectedEmotion2", -1);

        for(int i = 0; i < 5; ++i) {
            if(i == selected) {
                reactions[i].setBackgroundColor(getResources().getColor(R.color.logo_color));
            }
            else {
                reactions[i].setBackgroundColor(getResources().getColor(R.color.white));
            }
            int current = i;
            reactions[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(selected != -1)
                        reactions[selected].setBackgroundColor(getResources().getColor(R.color.white));
                    selected = current;
                    sharedPref.edit().putInt("selectedEmotion2", current).commit();
                    reactions[selected].setBackgroundColor(getResources().getColor(R.color.logo_color));
                }
            });
        }

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section2_page2_screen_next_btn,
                R.id.section2_page2_screen_previous_btn,
                Section2Page3.class,
                ()-> {
                    if(selected == -1) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    person.setReaction(selected, 1);

                    return person;
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref.edit().putInt("step", currentStep).commit();
    }
}