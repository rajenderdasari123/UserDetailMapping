package com.example.userdetailmappingtask.employee;

import android.app.Application;
import android.widget.Toast;

import com.example.userdetailmappingtask.adapter.AppointmentAdapter;
import com.example.userdetailmappingtask.managar.CloudManager;
import com.example.userdetailmappingtask.model.Appointment;
import com.example.userdetailmappingtask.model.Unassigned;
import com.example.userdetailmappingtask.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class EmployeeViewModel extends AndroidViewModel {
  private Application mApplication;
  private Disposable mDisposable;
  private final CloudManager mCloudManager;
  private List<Unassigned> mUnassignedList;
  private AppointmentAdapter mAppointmentAdapter;
  private MutableLiveData<Boolean> mHideSwipeRefreshLiveData;

  private MutableLiveData<Throwable> mErrorMutableLiveData;

  /**
   * constructor for viewmodel.
   *
   * @param application
   */
  public EmployeeViewModel(@NonNull Application application) {
    super(application);
    mApplication = application;
    mCloudManager = CloudManager.getInstance(application);
    mErrorMutableLiveData = new MutableLiveData<>();
    mHideSwipeRefreshLiveData = new MutableLiveData<>();
    mUnassignedList = new ArrayList<>();
  }

  /**
   * Returns errors  to the observer
   *
   * @return MutableLiveData
   */
  public MutableLiveData<Throwable> getErrorMutableLiveData() {
    return mErrorMutableLiveData;
  }

  /**
   * Initiates the cloud call for appointment data.
   */
  public void getAppointmentData() {
    if (NetworkUtil.isNetworkConnected(mApplication)) {
      mDisposable =
          mCloudManager.getData().subscribeWith(new DisposableSingleObserver<Appointment>() {
            @Override
            public void onSuccess(Appointment appointment) {
              mHideSwipeRefreshLiveData.setValue(true);
              mUnassignedList = appointment.getUnassigned();
              setAppointmentList();
            }

            @Override
            public void onError(Throwable e) {
              mHideSwipeRefreshLiveData.setValue(true);
              mErrorMutableLiveData.postValue(e);
            }
          });
    } else {
      mHideSwipeRefreshLiveData.setValue(true);
      Toast.makeText(mApplication.getApplicationContext(), "Please check your network connection", Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * Sets appointment list for adapter.
   */
  private void setAppointmentList() {
    mAppointmentAdapter.setAppointmentList(mUnassignedList);
    mAppointmentAdapter.notifyDataSetChanged();
  }

  /**
   * Getter for appointment adapter instance.
   *
   * @return {@link AppointmentAdapter}.
   */
  public AppointmentAdapter getAppointmentAdapter() {
    mAppointmentAdapter = new AppointmentAdapter(this);
    return mAppointmentAdapter;
  }

  /**
   * gets the values from the api call and populates to a list item with {@link Unassigned} at the
   * position mentioned.
   *
   * @param position {@link Integer}
   * @return {@link Unassigned}.
   */
  public Unassigned getUnassignedDetails(Integer position) {
    return mUnassignedList.get(position);
  }

  public MutableLiveData<Boolean> getHideSwipeRefreshLiveData() {
    return mHideSwipeRefreshLiveData;
  }

  /**
   * Disposes all the disposables on Destroy of Fragment.
   */
  @Override
  protected void onCleared() {
    super.onCleared();
    if (mDisposable != null) {
      mDisposable.dispose();
    }
  }

}
