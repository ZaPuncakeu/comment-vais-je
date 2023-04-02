package com.projet.cvj_ihm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Person implements Parcelable {
    private String username;

    private int gender;
    private int age;
    private float weight;
    private int height;
    private boolean married;

    private int feeling;
    private int stress;
    private int tired;
    private int anger;
    private int angerManagement;

    private int[] reaction = {-1, -1};
    private String[] description = {"", "", ""};

    private boolean knows_blood_pressure;
    private float blood_pressure;

    private boolean knows_cholesterol;
    private float cholesterol;


    private boolean knows_blood_sugar;
    private float blood_sugar;

    public Person(){}

    protected Person(Parcel in) {
        username = in.readString();
        gender = in.readInt();
        age = in.readInt();
        weight = in.readFloat();
        height = in.readInt();
        married = in.readByte() != 0;
        feeling = in.readInt();
        stress = in.readInt();
        tired = in.readInt();
        anger = in.readInt();
        angerManagement = in.readInt();
        reaction = in.createIntArray();
        description = in.createStringArray();
        knows_blood_pressure = in.readByte() != 0;
        blood_pressure = in.readFloat();
        knows_cholesterol = in.readByte() != 0;
        cholesterol = in.readFloat();
        knows_blood_sugar = in.readByte() != 0;
        blood_sugar = in.readFloat();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeInt(gender);
        parcel.writeInt(age);
        parcel.writeFloat(weight);
        parcel.writeInt(height);
        parcel.writeByte((byte) (married ? 1 : 0));
        parcel.writeInt(feeling);
        parcel.writeInt(stress);
        parcel.writeInt(tired);
        parcel.writeInt(anger);
        parcel.writeInt(angerManagement);
        parcel.writeIntArray(reaction);
        parcel.writeStringArray(description);
        parcel.writeByte((byte) (knows_blood_pressure ? 1 : 0));
        parcel.writeFloat(blood_pressure);
        parcel.writeByte((byte) (knows_cholesterol ? 1 : 0));
        parcel.writeFloat(cholesterol);
        parcel.writeByte((byte) (knows_blood_sugar ? 1 : 0));
        parcel.writeFloat(blood_sugar);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public void setFeeling(int feeling) {
        this.feeling = feeling;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public void setTired(int tired) {
        this.tired = tired;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }

    public void setAngerManagement(int angerManagement) {
        this.angerManagement = angerManagement;
    }

    public void setReaction(int reaction, int position) {
        this.reaction[position] = reaction;
    }

    public void setDescription(String description, int position) {
        this.description[position] = description;
    }

    public void setKnows_blood_pressure(boolean knows_blood_pressure) {
        this.knows_blood_pressure = knows_blood_pressure;
    }

    public void setBlood_pressure(float blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public void setKnows_cholesterol(boolean knows_cholesterol) {
        this.knows_cholesterol = knows_cholesterol;
    }

    public void setCholesterol(float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setKnows_blood_sugar(boolean knows_blood_sugar) {
        this.knows_blood_sugar = knows_blood_sugar;
    }

    public void setBlood_sugar(float blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public String getUsername() {
        return username;
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMarried() {
        return married;
    }

    public int getFeeling() {
        int[] feelings = {
                R.string.global_not_good,
                R.string.global_neutral,
                R.string.global_good,
                R.string.global_very_good
        };
        return feelings[feeling];
    }

    public int getStress() {
        return stress;
    }

    public int getTired() {
        int[] tiredFrequency = {
                R.string.global_not_much,
                R.string.global_pretty_often,
                R.string.global_all_the_time,
        };
        return tiredFrequency[tired];
    }

    public int getAnger() {
        return anger;
    }

    public int getAngerManagement() {
        int[] angerManagementLevels = {
                R.string.global_not_good,
                R.string.global_neutral,
                R.string.global_very_good,
        };
        return angerManagementLevels[angerManagement];
    }

    public int getReaction(int position) {
        if(reaction[position] == -1)
        {
            return -1;
        }

        int[] reactions = {
                R.string.global_angry,
                R.string.global_neutral,
                R.string.global_happy,
                R.string.global_sad,
                R.string.global_scared
        };
        return reactions[reaction[position]];
    }

    public String[] getDescription() {
        return description;
    }

    public boolean getKnows_blood_pressure() {
        return knows_blood_pressure;
    }

    public float getBlood_pressure() {
        return blood_pressure;
    }

    public boolean getKnows_cholesterol() {
        return knows_cholesterol;
    }

    public float getCholesterol() {
        return cholesterol;
    }

    public boolean getKnows_blood_sugar() {
        return knows_blood_sugar;
    }

    public float getBlood_sugar() {
        return blood_sugar;
    }

    public void Log() {
        Log.v("Person", "username "+ getUsername());
        Log.v("Person", "age "+ getAge());
        Log.v("Person", "weight " + getWeight());
        Log.v("Person", "height "+ getHeight());
        Log.v("Person", "married "+ isMarried());
        Log.v("Person", "feeling "+getFeeling());
        Log.v("Person", "stress "+getStress());
        Log.v("Person", "tired "+getTired());
        Log.v("Person", "anger "+getAnger());
        Log.v("Person", "angerManagement "+getAngerManagement());
    }
}
