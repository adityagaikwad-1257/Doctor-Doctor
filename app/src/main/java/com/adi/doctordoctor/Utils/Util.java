package com.adi.doctordoctor.Utils;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.adi.doctordoctor.R;
import com.adi.doctordoctor.models.Doctor;

import java.util.ArrayList;

public class Util {
    /*
        constants
     */
    public static final String DOCTOR_NAME_KEY = "DOCTOR_NAME";
    public static final String DOCTOR_EMAIL_KEY = "DOCTOR_EMAIL";
    public static final String DOCTOR_PR_FR_MONTH_KEY = "MONTH";
    public static final String DOCTOR_PR_FR_YEAR_KEY = "YEAR";
    public static final String DOCTOR_GENDER_KEY = "GENDER";
    public static final String DOCTOR_GUID_KEY = "DOC_GUID";

    private static final ArrayList<String> STATIC_IMAGES;

    static {
        STATIC_IMAGES = new ArrayList<>();
        STATIC_IMAGES.add("https://thumbs.dreamstime.com/z/sneakers-solid-classic-blue-backdrop-inspirational-motivational-quote-never-give-up-keep-fit-athletic-sneakers-168073270.jpg");
        STATIC_IMAGES.add("https://thumbs.dreamstime.com/b/stationery-stay-home-made-plasticine-yellow-background-178345903.jpg");
        STATIC_IMAGES.add("https://thumbs.dreamstime.com/b/your-health-investment-not-expense-your-health-investment-not-expense-concept-health-sport-diet-fitness-144301911.jpg");
    }

    public static String getImageAt(int position){
        return STATIC_IMAGES.get(position);
    }

    public static int getStaticDataSize(){
        return STATIC_IMAGES.size();
    }

    /*
        util functions for RegisterActivity
     */

    /*
        returns @String gender according to the position selected in gender tab layout
        position    Gender     String returned
        0        ->  Male   =>     "M"
        1        -> Female  =>     "F"
        2        -> Others  =>     "O"
     */
    public static String getGenderFromPosition(int position){
        switch (position){
            case 0:
                return "M";
            case 1:
                return "F";
            case 2:
                return "O";
            default:
                return null;
        }
    }

    /*
        saves the doctor data into shared preference
     */
    public static void saveDoctorDetails(Doctor doctor, Activity activity, Class<? extends Activity> toClass) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(
                activity.getResources().getString(R.string.USER_DATA_KEY),
                Context.MODE_PRIVATE
        ).edit();

        editor.putString(DOCTOR_NAME_KEY, doctor.getName());
        editor.putString(DOCTOR_GUID_KEY, doctor.getDoctors_id());

        editor.apply();

        activity.startActivity(new Intent(activity, toClass));
        activity.finish();
    }

    /*
        get doctor name saved in shared preference
     */
    public static String getDoctorInName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getResources().getString(R.string.USER_DATA_KEY), MODE_PRIVATE
        );

        return sharedPreferences.getString(Util.DOCTOR_NAME_KEY, null);
    }
}
