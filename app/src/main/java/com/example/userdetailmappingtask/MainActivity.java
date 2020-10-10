package com.example.userdetailmappingtask;

import android.os.Bundle;

import com.example.userdetailmappingtask.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import static com.example.userdetailmappingtask.BR.mainViewModel;

public class MainActivity extends AppCompatActivity {

  private MainViewModel mMainViewModel;

  /**
   * Called when the activity is first created. This is where you should do all of your normal
   * static set up: create views, bind data to lists, etc. This method also provides you with a
   * Bundle containing the activity's previously frozen state, if there was one.
   *
   * @param savedInstanceState-{@link Bundle}.
   */
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