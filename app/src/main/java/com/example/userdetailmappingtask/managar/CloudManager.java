package com.example.userdetailmappingtask.managar;

import android.content.Context;

import com.example.userdetailmappingtask.interfaces.CloudApi;
import com.example.userdetailmappingtask.model.Appointment;
import com.example.userdetailmappingtask.service.CloudService;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CloudManager {
  private static CloudApi mCloudApi;
  private static CloudManager mCloudManager = null;

  private CloudManager() {
  }

  public static CloudManager getInstance(Context context) {
    if (mCloudManager == null) {
      mCloudManager = new CloudManager();
      if (context != null) {
        mCloudApi = CloudService.getClient().create(CloudApi.class);
      }
    }
    return mCloudManager;
  }

  public Single<Appointment> getResults(){
   return mCloudApi.get_booking_appointment_list("VI020PE0016","All","Employee","2020-07-25").subscribeOn(Schedulers.io())
       .observeOn(AndroidSchedulers.mainThread());
  }
}