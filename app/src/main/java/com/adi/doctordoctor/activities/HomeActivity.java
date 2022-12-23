package com.adi.doctordoctor.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.adi.doctordoctor.R;
import com.adi.doctordoctor.Utils.FingerTipAdapter;
import com.adi.doctordoctor.Utils.Util;
import com.adi.doctordoctor.databinding.ActivityHomeBinding;
import com.adi.doctordoctor.models.Doctor;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        /*
            enabling gridLayoutManager to recyclerview
         */
        binding.ftRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        /*
            creating an adapter for recycler view
         */
        FingerTipAdapter adapter = new FingerTipAdapter(this);
        binding.ftRecyclerView.setAdapter(adapter);

        /*
            log out listener
         */
        binding.logOutBtn.setOnClickListener(this::logOut);

        setUpUserDetails();
    }

    /*
        logging out
        saving null at shared preference
     */
    private void logOut(View view){
        /*
            passing doctor object with null values
         */
        Util.saveDoctorDetails(new Doctor(), this, RegisterActivity.class);
    }

    private void setUpUserDetails() {
        String name = Util.getDoctorInName(this);

        if (name == null){
            name = "Name here";
        }

        name = name.concat("!!");
        binding.userName.setText(name);
    }
}