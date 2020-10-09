package com.example.userdetailmappingtask.employee;

import android.app.Application;
import android.util.Log;

import com.example.userdetailmappingtask.adapter.AppointmentAdapter;
import com.example.userdetailmappingtask.managar.CloudManager;
import com.example.userdetailmappingtask.model.Appointment;
import com.example.userdetailmappingtask.model.Unassigned;
import com.example.userdetailmappingtask.utils.NetworkUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeViewModel extends AndroidViewModel {
  private Application mApplication;
  private Disposable mDisposable;
  private final CloudManager mCloudManager;
  private List<Unassigned> mUnassignedList;
  private AppointmentAdapter mAppointmentAdapter;

  public EmployeeViewModel(@NonNull Application application) {
    super(application);
    mApplication = application;
    mCloudManager = CloudManager.getInstance(application);
  }


  public void getAppointmentData() {
    if (NetworkUtil.isNetworkConnected(mApplication)) {
      mDisposable = mCloudManager.getResults().subscribeWith(new DisposableSingleObserver<Appointment>() {
        @Override
        public void onSuccess(Appointment appointment) {
          mUnassignedList = appointment.getUnassigned();
          setAppointmentAdapter();
        }

        @Override
        public void onError(Throwable e) {

        }
      });
    }
  }

  public void setAppointmentAdapter(){
    mAppointmentAdapter.setAppointmentList(mUnassignedList);
    mAppointmentAdapter.notifyDataSetChanged();
  }

  public AppointmentAdapter getAppointmentAdapter() {
    mAppointmentAdapter = new AppointmentAdapter(this);
    return mAppointmentAdapter;
  }
}
