package com.example.userdetailmappingtask.location;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MapsViewModel extends AndroidViewModel {
  private Application mApplication;

  public MapsViewModel(@NonNull Application application) {
    super(application);
    mApplication = application;
  }
}
