package com.adi.doctordoctor.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.adi.doctordoctor.R;
import com.adi.doctordoctor.Utils.Util;
import com.adi.doctordoctor.activities.HomeActivity;
import com.adi.doctordoctor.models.Doctor;
import com.adi.doctordoctor.models.PostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.adi.doctordoctor.Utils.Util.DOCTOR_GUID_KEY;
import static com.adi.doctordoctor.Utils.Util.DOCTOR_NAME_KEY;

public class RetrofitHelper {
    private static final String TAG = "aditya";

    private static final String BASE_URL = "http://199.192.26.248:8000/sap/opu/odata/sap/";

    public static DoctorsApi getDoctorApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(DoctorsApi.class);
    }

    public static void registerDoctor(Activity activity, Doctor doctor){
        /*
            making the POST call on API using retrofit
         */

        DoctorsApi doctorsApi = getDoctorApi();

        /*
            showing a loading view until the following API call is completed
         */
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("while we sign you up");
        progressDialog.setCancelable(false);
        progressDialog.show();

        /*
            making POST API call along with the payload doctor object
         */
        doctorsApi.registerDoctor(doctor)
                .enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        progressDialog.dismiss(); // shutting loading view down

                        if (!response.isSuccessful()){
                            Log.d(TAG, "onResponse: Not success full : " +
                                    "message : " + response.message());
                            Toast.makeText(activity, "Sign up Failed! Try again", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (response.body() != null) {
                            Toast.makeText(activity, "Sign up successful", Toast.LENGTH_SHORT).show();

                            Log.d(TAG, "onResponse: SUCCESSFUL : \n"+
                                    response.body().getD());

                            Util.saveDoctorDetails(response.body().getD(), activity, HomeActivity.class);
                        }
                        else{
                            Log.d(TAG, "onResponse: response body null" + response.message());

                            Toast.makeText(activity, "sign up Failed! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        progressDialog.dismiss(); // shutting loading view down
                        Toast.makeText(activity, "sign up Failed! try again", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }
}
