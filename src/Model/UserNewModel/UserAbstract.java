package com.example.ad.retrofittest.Model.UserNewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public abstract class UserAbstract {
    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("second_name")
    @Expose
    private String secondName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("birthday")
    @Expose
    private Date birthday;



    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        if(this.birthday==null)
            return 0;
        Calendar a = Calendar.getInstance();
        a.setTime(this.birthday);
        Calendar b = Calendar.getInstance();
        b.setTime(new Date());
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }
}
