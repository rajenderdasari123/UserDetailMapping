package com.example.userdetailmappingtask.employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.userdetailmappingtask.R;
import com.example.userdetailmappingtask.databinding.FragmentEmployeeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class EmployeeFragment extends Fragment {

  private EmployeeViewModel mEmployeeViewModel;
  private SwipeRefreshLayout mSwipeRefreshLayout;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    FragmentEmployeeBinding employeeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee, container, false);
    mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
    employeeBinding.setEmployeeViewModel(mEmployeeViewModel);
    mSwipeRefreshLayout = employeeBinding.swipeRefresh;
    return employeeBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getAppointments();
    mSwipeRefreshLayout.setOnRefreshListener(this::getAppointments);
    initObservers();
  }

  /**
   * Initializes Call for appointment data.
   */
  private void getAppointments() {
    mEmployeeViewModel.getAppointmentData();
  }

  /**
   * This method initializes the Observers.
   */
  private void initObservers() {
    mEmployeeViewModel.getErrorMutableLiveData().observe(getViewLifecycleOwner(), this::handleErrors);
    mEmployeeViewModel.getHideSwipeRefreshLiveData().observe(getViewLifecycleOwner(), this::hideSwipeRefresh);
  }

  /**
   * This method handle Api Call Errors.
   *
   * @param throwable {@link Throwable}.
   */
  private void handleErrors(Throwable throwable) {
    Toast.makeText(getActivity(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
  }

  /**
   * This method is used to hide the Swipe Refresh once after fetching all the data.
   *
   * @param aBoolean {@link Boolean}.
   */
  private void hideSwipeRefresh(Boolean aBoolean) {
    if (aBoolean) {
      mSwipeRefreshLayout.setRefreshing(false);
    }
  }

  /**
   * called when view related resources in onCreateView() are removed from activity's View
   * hierarchy and destroyed.
   */
  @Override
  public void onDestroyView() {
    mEmployeeViewModel.onCleared();
    mEmployeeViewModel.getHideSwipeRefreshLiveData().removeObservers(this);
    super.onDestroyView();
  }
}
