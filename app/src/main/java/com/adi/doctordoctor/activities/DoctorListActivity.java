package com.adi.doctordoctor.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.adi.doctordoctor.R;
import com.adi.doctordoctor.Utils.DoctorListAdapter;
import com.adi.doctordoctor.Utils.Util;
import com.adi.doctordoctor.databinding.ActivityDoctorListBinding;
import com.adi.doctordoctor.models.GetResponse;
import com.adi.doctordoctor.models.PostResponse;
import com.adi.doctordoctor.retrofit.DoctorsApi;
import com.adi.doctordoctor.retrofit.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorListActivity extends AppCompatActivity {
    private static final String TAG = "aditya";

    ActivityDoctorListBinding binding;

    DoctorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_list);

        binding.docRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DoctorListAdapter();

        binding.docRecyclerView.setAdapter(adapter);

        DoctorsApi doctorsApi = RetrofitHelper.getDoctorApi();

        doctorsApi.getAllDoctors().enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {

                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: Not success full : " +
                            "message : " + response.message());
                    Toast.makeText(DoctorListActivity.this, "Sign up Failed! Try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() != null) {
                    Toast.makeText(DoctorListActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();

                    Log.d(TAG, "onResponse: SUCCESSFUL : \n"+
                            response.body().getD().getResults());

                    adapter.submitList(response.body().getD().getResults());
                }
                else{
                    Log.d(TAG, "onResponse: response body null" + response.message());

                    Toast.makeText(DoctorListActivity.this, "sign up Failed! Try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                Toast.makeText(DoctorListActivity.this, "sign up Failed! try again", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}