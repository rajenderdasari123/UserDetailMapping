package com.example.userdetailmappingtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.userdetailmappingtask.databinding.ActivityMainBinding;

import static com.example.userdetailmappingtask.BR.mainViewModel;

public class MainActivity extends AppCompatActivity {

  private  MainViewModel mMainViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    activityMainBinding.setVariable(mainViewModel, mMainViewModel);
    activityMainBinding.setLifecycleOwner(this);
    activityMainBinding.executePendingBindings();

  }
}