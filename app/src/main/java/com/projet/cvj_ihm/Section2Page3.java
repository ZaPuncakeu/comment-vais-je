package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Section2Page3 extends AppCompatActivity {
    private PageHandler ph;
    EditText descriptionEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section2_page3);

        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        descriptionEt = (EditText) findViewById(R.id.section2_page3_screen_description_et);

        ph = new PageHandler(
                this,
                R.id.section2_page3_screen_next_btn,
                R.id.section2_page3_screen_previous_btn,
                Section2Page4.class,
                ()-> {
                    String description = descriptionEt.getText().toString();
                    if(description.isEmpty()) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    person.setDescription(description, 0);
                    return person;
                }
        );
    }
}