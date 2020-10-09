package com.example.userdetailmappingtask.employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userdetailmappingtask.R;
import com.example.userdetailmappingtask.adapter.AppointmentAdapter;
import com.example.userdetailmappingtask.databinding.FragmentEmployeeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class EmployeeFragment extends Fragment {

  private EmployeeViewModel mEmployeeViewModel;
  private FragmentEmployeeBinding mEmployeeBinding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mEmployeeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee, container, false);
    mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
    mEmployeeBinding.setEmployeeViewModel(mEmployeeViewModel);
    return mEmployeeBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mEmployeeViewModel.getAppointmentData();
  }
}
