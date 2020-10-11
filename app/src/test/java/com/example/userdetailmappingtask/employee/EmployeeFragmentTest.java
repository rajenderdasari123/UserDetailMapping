package com.example.userdetailmappingtask.employee;

import android.content.Context;
import android.os.Build;

import com.example.userdetailmappingtask.FragmentScenarioRule;
import com.example.userdetailmappingtask.MainActivity;
import com.example.userdetailmappingtask.model.Appointment;
import com.example.userdetailmappingtask.model.Unassigned;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

/**
 * The test class for {@link EmployeeViewModel}
 */
@Config(sdk = Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4.class)
public class EmployeeFragmentTest {
  private EmployeeFragment mEmployeeFragment;
  List<Unassigned> mUnassignedList = new ArrayList<>();
  private NavController mNavController;
  private Context mContext;

  public FragmentScenarioRule<EmployeeFragment, MainActivity> mFragmentScenarioRule =
      new FragmentScenarioRule(EmployeeFragment.class, MainActivity.class);


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    List<Appointment> appointments = new ArrayList<>();
    mFragmentScenarioRule.onFragment(fragment -> mEmployeeFragment = fragment);
    mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    mNavController = Mockito.mock(NavController.class);
    Navigation.setViewNavController(Objects.requireNonNull(mEmployeeFragment.getView()),
        mNavController);
  }

  @Test
  public void testInstance() {
    Assert.assertNotNull(mEmployeeFragment);
  }

  @After
  public void tearDown() {
    mEmployeeFragment = null;
  }

}
