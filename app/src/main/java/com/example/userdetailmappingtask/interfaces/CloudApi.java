package com.example.userdetailmappingtask.interfaces;


import com.example.userdetailmappingtask.model.Appointment;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CloudApi {

  @FormUrlEncoded
  @POST("get_monthly_appointments")
  Single<Appointment> get_booking_appointment_list(
      @Field("user_employeid") String employeId,
      @Field("status") String page_no,
      @Field("appointment_type") String appointment_type,
      @Field("month") String appointment_date);
}

