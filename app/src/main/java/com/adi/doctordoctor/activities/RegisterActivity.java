package com.adi.doctordoctor.activities;

import static com.adi.doctordoctor.Utils.Util.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adi.doctordoctor.Utils.Util;
import com.adi.doctordoctor.databinding.ActivityMainBinding;
import com.adi.doctordoctor.models.Doctor;
import com.adi.doctordoctor.retrofit.RetrofitHelper;
import com.google.android.material.tabs.TabLayout;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "aditya";

    /*
        DataBinding object for activity_main.xml
     */
    ActivityMainBinding binding;

    /*
        @Doctor object to hold details entered by user
        This works with two way binding
            as soon as user enter data into input text the same data will be written to this doctor object
     */
    Doctor doctor;

    @Override
    protected void onStart() {
        super.onStart();
        /*
            checking if doctor details already there and redirecting to HomeActivity
         */
        if (Util.getDoctorInName(this) != null){
            startActivity(new Intent(this, HomeActivity.class));
            this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
            setting tab select listener to gender tab layout to listen tab selections by the user
         */
        binding.genderTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: " + tab.getPosition());
                /*
                    updating doctor's gender
                 */

                updateDoctorGender(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: ");

                updateDoctorGender(tab);
            }
        });

        /*
            initiating doctor instance according to the state of savedInstanceState
         */
        initiateDoctorObject(savedInstanceState);

        /*
            providing doctor object for two way data binding
         */
        binding.setDoctor(doctor);

        binding.continueBtn.setOnClickListener(this::verifyDetailsAndRegister);
    }

    /*
        updating doctor's gender in accordance to tab selected in the gender tab layout
     */
    private void updateDoctorGender(TabLayout.Tab tab){
        doctor.setGender(getGenderFromPosition(tab.getPosition()));
    }

    private void initiateDoctorObject(Bundle savedInstanceState) {
        if (savedInstanceState == null){
            /*
                as savedInstanceState is null, this is the fresh instance of the activity
                thus, initiating a new doctor with all the details being null
             */

            doctor = new Doctor();

            /*
                selecting first tab from the gender table layout for default
                i.e. Male
             */
            binding.genderTabLayout.selectTab(binding.genderTabLayout.getTabAt(0));
        }else{
            /*
                as savedInstanceState is not null, this is a new instance of the activity undergone
                configuration change
                Also, getting selected position of the gender tab layout and programmatically selecting that tab

                thus, initiating doctor object with the received data with the savedInstanceState from the previous instance
                of the activity
             */

            doctor = new Doctor(
                    savedInstanceState.getString(DOCTOR_NAME_KEY),
                    savedInstanceState.getString(DOCTOR_EMAIL_KEY),
                    null, // gender is programmatically set according to the gender tab layout selection
                    savedInstanceState.getString(DOCTOR_PR_FR_MONTH_KEY),
                    savedInstanceState.getString(DOCTOR_PR_FR_YEAR_KEY)
            );

            int selectedGenderPosition = savedInstanceState.getInt(DOCTOR_GENDER_KEY, 0);
            binding.genderTabLayout.selectTab(binding.genderTabLayout.getTabAt(selectedGenderPosition));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        /*
            passing the details already entered by user to the new instance of activity create undergone
            configuration change
         */
        outState.putString(DOCTOR_NAME_KEY, doctor.getName());
        outState.putString(DOCTOR_EMAIL_KEY, doctor.getEmail());
        outState.putString(DOCTOR_PR_FR_MONTH_KEY, doctor.getPractice_frm_month());
        outState.putString(DOCTOR_PR_FR_YEAR_KEY, doctor.getPractice_frm_year());

        /*
            passing selected position from the gender tab layout
         */
        int selectedGenderPosition = binding.genderTabLayout.getSelectedTabPosition();
        outState.putInt(DOCTOR_GENDER_KEY, selectedGenderPosition);
    }

    /*
        click listener to continue button
     */
    private void verifyDetailsAndRegister(View view){
        Log.d(TAG, "click: " + doctor);

        hideSoftKeyboard();
        
        /*
            if any of the checks following goes wrong
            allOkay would be turned false
         */
        boolean allOkay = true;
        
        /*
            checking name entered
         */
        if (doctor.getName() == null || doctor.getName().trim().isEmpty()){
            allOkay = false;
            binding.nameEt.setError("Name required");
        }
        
        /*
            checking email entered and matching it with the pattern
         */
        if (doctor.getEmail() == null || doctor.getEmail().trim().isEmpty()){
            allOkay = false;
            binding.emailIdEt.setError("Email required");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(doctor.getEmail()).matches()){
            allOkay = false;
            binding.emailIdEt.setError("Invalid email");
        }
        
        /*
            checking month entered and if it is in ranger 0 to 12
         */
        if (doctor.getPractice_frm_month() == null || doctor.getPractice_frm_month().trim().isEmpty()){
            allOkay = false;
            binding.fromMonthEt.setError("Month required");
        }else{
            try{
                int month = Integer.parseInt(doctor.getPractice_frm_month());
                if (month < 0 || month > 12){
                    allOkay = false;
                    binding.fromMonthEt.setError("Invalid month");
                }
            }catch (Exception e){
                Log.d(TAG, "verifyDetailsAndRegister: month check " + e.getMessage());
                allOkay = false;
                binding.fromMonthEt.setError("Invalid month");
            }
        }
        
        /*
            checking year entered and if it is in ranger 0 to 12
         */
        if (doctor.getPractice_frm_year() == null || doctor.getPractice_frm_year().trim().isEmpty()){
            allOkay = false;
            binding.fromYearEt.setError("year required");
        }else{
            try{
                int year = Integer.parseInt(doctor.getPractice_frm_year());
                if (year < 1950 || year > 2022){
                    allOkay = false;
                    binding.fromYearEt.setError("Invalid year");
                }
            }catch (Exception e){
                Log.d(TAG, "verifyDetailsAndRegister: year check " + e.getMessage());
                allOkay = false;
                binding.fromYearEt.setError("Invalid year");
            }
        }
        
        if (allOkay){
            /*
                as all details entered by user are okay
                registering the user with these details entered = doctor object
             */

            RetrofitHelper.registerDoctor(this, doctor);
        }
    }

    private void hideSoftKeyboard(){
        /*
            hiding soft keyboard on screen
         */
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}