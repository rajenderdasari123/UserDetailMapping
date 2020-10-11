package com.example.userdetailmappingtask.employee;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.example.userdetailmappingtask.FragmentScenarioRule;
import com.example.userdetailmappingtask.MainActivity;
import com.example.userdetailmappingtask.adapter.AppointmentAdapter;
import com.example.userdetailmappingtask.model.Unassigned;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * The test class for {@link EmployeeViewModel}
 */
@Config(sdk = Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4.class)
public class EmployeeViewModelTest {
  private EmployeeViewModel mEmployeeViewModel;
  private AppointmentAdapter mAppointmentAdapter;
  List<Unassigned> mUnassignedList = new ArrayList<>();


  public FragmentScenarioRule<EmployeeFragment, MainActivity> mFragmentScenarioRule =
      new FragmentScenarioRule(EmployeeFragment.class, MainActivity.class);

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Context context = InstrumentationRegistry.getInstrumentation().getContext();
    mEmployeeViewModel = new EmployeeViewModel((Application) context);
    mAppointmentAdapter = mEmployeeViewModel.getAppointmentAdapter();
    mEmployeeViewModel.setAdapterList(getDetailsList());
  }

  @Test
  public void testAdapter() {
    mEmployeeViewModel.setAppointmentList();
    assertEquals(2, mAppointmentAdapter.getItemCount());
  }

  private List<Unassigned> getDetailsList() {
    Unassigned unassigned = new Unassigned();
    unassigned.setPOC_Email("afte@gmail.com");
    unassigned.setMessage("Okdfgh");
    unassigned.setAddress_Line_1("Address_Line_1");
    unassigned.setAppointment_id("c9852c6f-96e8-4bc6-b948-f9b25515e637");
    unassigned.setInstitution_Name("Case ");
    unassigned.setPIN(560068);
    unassigned.setState("on-site");
    mUnassignedList.add(unassigned);

    unassigned.setPOC_Email("vinay@gmail.com");
    unassigned.setMessage("Kkkk");
    unassigned.setAddress_Line_1("Begur road ");
    unassigned.setAppointment_id("29360b07-628f-4b2a-95b9-40502bddff91");
    unassigned.setInstitution_Name("Case ");
    unassigned.setPIN(560068);
    unassigned.setState("on-site");
    mUnassignedList.add(unassigned);
    return mUnassignedList;
  }
}
