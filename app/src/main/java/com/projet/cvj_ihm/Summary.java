package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Summary extends AppCompatActivity {

    private ExpandableListView expandableLv;
    private ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        expandableLv = (ExpandableListView) findViewById(R.id.summary_screen_summary_lv);
        HashMap<String, List<String>> data = getSummary();
        Log.d("Hashkoupi", data.keySet().toString());
        if(data == null) return;
        expandableListAdapter = new SummaryExpandableListAdapater(this, new ArrayList<String>(data.keySet()), data);
        expandableLv.setAdapter(expandableListAdapter);
    }

    private HashMap<String, List<String>> getSummary() {
        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return null;
        }

        Person person = getIntent().getParcelableExtra("person");
        person.Log();
        HashMap<String, List<String>> summary = new HashMap<String, List<String>>();
        List<String> personal = new ArrayList<String>();
        personal.add(Utils.R2String(this, R.string.summary_screen_username) + " " + person.getUsername());
        personal.add(Utils.R2String(this, R.string.summary_screen_gender) + " "
                + (person.getGender() == 0 ? Utils.R2String(this, R.string.summary_screen_gender_woman)
                :
                Utils.R2String(this, R.string.summary_screen_gender_man)
        ));

        personal.add(Utils.R2String(this, R.string.summary_screen_age) + " " + person.getAge() + " " + Utils.R2String(this, R.string.summary_screen_years_old));
        personal.add(Utils.R2String(this, R.string.summary_screen_weight) + " " + person.getWeight() + " " + Utils.R2String(this, R.string.summary_screen_kg));
        personal.add(Utils.R2String(this, R.string.summary_screen_height) + " " + person.getHeight() + " " + Utils.R2String(this, R.string.summary_screen_cm));
        personal.add(Utils.R2String(this, R.string.summary_screen_married) + " "
                + (person.isMarried() ? Utils.R2String(this, R.string.global_yes)
                :
                Utils.R2String(this, R.string.global_no)
        ));

        summary.put(Utils.R2String(this, R.string.summary_screen_personal), personal);

        List<String> feelings = new ArrayList<String>();
        feelings.add(Utils.R2String(this, R.string.summary_screen_today_feeling) + " " + Utils.R2String(this, person.getFeeling()));
        feelings.add(Utils.R2String(this, R.string.summary_screen_stress_level) + " " + person.getStress() + " / 100");
        feelings.add(Utils.R2String(this, R.string.summary_screen_feeling_tired) + " " + Utils.R2String(this, person.getTired()));
        feelings.add(Utils.R2String(this, R.string.summary_screen_anger_frequency) + " " + person.getAnger() + " " + Utils.R2String(this, R.string.summary_screen_out_of) + " 10");
        feelings.add(Utils.R2String(this, R.string.summary_screen_anger_management) + " " + Utils.R2String(this, person.getAngerManagement()));
        summary.put(Utils.R2String(this, R.string.summary_screen_feelings), feelings);

        int reaction_1 = person.getReaction(0);
        if(reaction_1 != -1) {
            List<String> reactions = new ArrayList<String>();
            reactions.add(Utils.R2String(this, R.string.summary_screen_reaction_img_1) + " " + Utils.R2String(this, reaction_1));
            reactions.add(Utils.R2String(this, R.string.summary_screen_reaction_img_2) + " " + Utils.R2String(this, person.getReaction(1)));
            String[] descriptions = person.getDescription();
            reactions.add(Utils.R2String(this, R.string.summary_screen_rorschach_description) + "1: " + descriptions[0]);
            reactions.add(Utils.R2String(this, R.string.summary_screen_rorschach_description) + "2: " + descriptions[1]);
            reactions.add(Utils.R2String(this, R.string.summary_screen_rorschach_description) + "3: " + descriptions[2]);
            summary.put(Utils.R2String(this, R.string.summary_screen_reactions), reactions);
        }

        List<String> physical = new ArrayList<String>();
        boolean knows_bp = person.getKnows_blood_pressure();
        physical.add(Utils.R2String(this, R.string.section3_page1_screen_know_blood_pressure) + " " + Utils.R2String(this, knows_bp ? R.string.global_yes : R.string.global_no));
        if(knows_bp) {
            physical.add(Utils.R2String(this, R.string.summary_screen_blood_pressure) + " " + person.getBlood_pressure() + " " + Utils.R2String(this, R.string.global_mm_hg));
        }

        boolean knows_c = person.getKnows_cholesterol();
        physical.add(Utils.R2String(this, R.string.section3_page1_screen_know_cholesterol) + " " + Utils.R2String(this, knows_c ? R.string.global_yes : R.string.global_no));
        if(knows_c) {
            physical.add(Utils.R2String(this, R.string.summary_screen_cholesterol) + " " + person.getCholesterol() + " " + Utils.R2String(this, R.string.global_mmol_l));
        }

        boolean knows_bs = person.getKnows_blood_sugar();
        physical.add(Utils.R2String(this, R.string.section3_page1_screen_know_blood_sugar) + " " + Utils.R2String(this, knows_bs ? R.string.global_yes : R.string.global_no));
        if(knows_c) {
            physical.add(Utils.R2String(this, R.string.summary_screen_blood_sugar) + " " + person.getBlood_sugar() + " " + Utils.R2String(this, R.string.global_g_l));
        }

        summary.put(Utils.R2String(this, R.string.summary_screen_your_physical), physical);
        return summary;
    }
}