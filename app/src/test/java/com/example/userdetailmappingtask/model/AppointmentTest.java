package com.example.userdetailmappingtask.model;

import android.os.Build;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * The test class for {@link Appointment}
 */
@Config(sdk = Build.VERSION_CODES.O)
@RunWith(RobolectricTestRunner.class)
public class AppointmentTest {
  private Appointment mAppointment;
  private List<Unassigned> mUnassignedList = new ArrayList<>();
  private List<Assigned> mAssignedList = new ArrayList<>();

  @Before
  public void setUp() {
    mAppointment = new Appointment();
    setAppointmentData();
  }

  private void setAppointmentData() {
    Unassigned unassignedList1 = new Unassigned();
    unassignedList1.setPOC_Email("afte@gmail.com");
    unassignedList1.setMessage("Okdfgh");
    unassignedList1.setAddress_Line_1("Address_Line_1");
    unassignedList1.setAppointment_id("c9852c6f-96e8-4bc6-b948-f9b25515e637");
    unassignedList1.setInstitution_Name("Case ");
    unassignedList1.setPIN(560068);
    unassignedList1.setState("on-site");
    mUnassignedList.add(unassignedList1);

    Unassigned unassignedList2 = new Unassigned();
    unassignedList2.setPOC_Email("vinay@gmail.com");
    unassignedList2.setMessage("Kkkk");
    unassignedList2.setAddress_Line_1("Begur road ");
    unassignedList2.setAppointment_id("29360b07-628f-4b2a-95b9-40502bddff91");
    unassignedList2.setInstitution_Name("Case ");
    unassignedList2.setPIN(560068);
    unassignedList2.setState("on-site");
    mUnassignedList.add(unassignedList2);
  }

  @Test
  public void testUnassignedList() {
    mAppointment.setUnassigned((ArrayList<Unassigned>) mUnassignedList);
    assertEquals(2, mAppointment.getUnassigned().size());
  }

  @Test
  public void testAssignedList() {
    Assigned assignedList1 = new Assigned();
    Assigned assignedList2 = new Assigned();
    mAssignedList.add(assignedList1);
    mAssignedList.add(assignedList2);
    mAppointment.setAssigned((ArrayList<Assigned>) mAssignedList);
    assertEquals(2, mAppointment.getAssigned().size());
  }

  @Test
  public void testMessage() {
    mAppointment.setMessage("List of appointments");
    assertEquals("List of appointments", mAppointment.getMessage());
  }

  @Test
  public void testStatus() {
    mAppointment.setStatus("Success");
    assertEquals("Success", mAppointment.getStatus());
  }

  @After
  public void tearDown() {
    mAppointment = null;
  }

}

