package com.adi.doctordoctor.retrofit;

import com.adi.doctordoctor.models.Doctor;
import com.adi.doctordoctor.models.PostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DoctorsApi {

    @Headers({"Content-Type: application/json",
                "Accept: application/json",
                "X-Requested-With: X"})
    @POST("ZCDS_TEST_REGISTER_CDS/ZCDS_TEST_REGISTER")
    Call<PostResponse> registerDoctor(@Body Doctor doctor);
}
