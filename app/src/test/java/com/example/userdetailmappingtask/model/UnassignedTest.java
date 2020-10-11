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
 * The test class for {@link Unassigned}
 */
@Config(sdk = Build.VERSION_CODES.O)
@RunWith(RobolectricTestRunner.class)
public class UnassignedTest {
  private Unassigned mUnassigned = new Unassigned();
  private List<Unassigned> mUnassignedList;
  private Appointment mAppointment;

  @Before
  public void setUp() {
    mUnassigned = new Unassigned();
    mAppointment = new Appointment();
    mUnassignedList = new ArrayList<>();
  }

  @Test
  public void testUnassignedData() {
    setAppointmentData();
    assertEquals("afte@gmail.com", mUnassigned.getPOC_Email());
    assertEquals("Okdfgh", mUnassigned.getMessage());
    assertEquals("Address_Line_1", mUnassigned.getAddress_Line_1());
    assertEquals("Address_Line_2", mUnassigned.getAddress_Line_2());
    assertEquals("VS20200724162913", mUnassigned.getCustom_appointment_id());
    assertEquals("Bangalore", mUnassigned.getCity());
    assertEquals("Marketing executive ", mUnassigned.getPOC_Designation());
    assertEquals("Mohan", mUnassigned.getMarketing_user_firstname());
    assertEquals("10:00:00", mUnassigned.getAppointment_time());
    assertEquals("VI020PE0016", mUnassigned.getMarketing_user_employee_id());
    assertEquals("Kanchan Bhawan, Patna - Gaya Rd, Parsa Bazar, Punpun, Nathupur, Bihar 804453, India Nathupur 804453", mUnassigned.getImage_address());
    assertEquals("on-site", mUnassigned.getState());
    assertEquals("Case ", mUnassigned.getInstitution_Name());

    assertEquals("Appointment completed", mUnassigned.getInstitution_Status());
    assertEquals("cas@gmail.com", mUnassigned.getInstitution_Email());
    assertEquals("varadhi_smartech/appointments/JPEG_20200724162901_9045066473649732288.jpg", mUnassigned.getImage_Selection_View());
    assertEquals("88c8c0aa-0546-46f7-8c5b-8977196d3a85", mUnassigned.getMarketing_user_id());
    assertEquals("Ab resistance ", mUnassigned.getLand_Mark());
    assertEquals("on-site", mUnassigned.getSite());
    assertEquals("varadhi_smartech/School/Marketing/User/Profile/profile-icon-illustration-user-profile-co_QDNpSqN.png", mUnassigned.getMarketing_user_picture());
    assertEquals("M", mUnassigned.getMarketing_user_lastname());
    assertEquals("2020-07-25", mUnassigned.getAppointment_date());
    assertEquals("2020-07-24T11:00:37.978Z", mUnassigned.getUpdated_datetime());
    assertEquals("2020-07-24T10:59:13.712Z", mUnassigned.getAdded_datetime());
    assertEquals("Booked", mUnassigned.getAppointment_status());
    assertEquals("Artt", mUnassigned.getPOC_Name());
    assertEquals("c9852c6f-96e8-4bc6-b948-f9b25515e637", mUnassigned.getAppointment_id());
  }

  private void setAppointmentData() {
    mUnassigned.setPOC_Email("afte@gmail.com");
    mUnassigned.setMessage("Okdfgh");
    mUnassigned.setAddress_Line_1("Address_Line_1");
    mUnassigned.setAddress_Line_2("Address_Line_2");
    mUnassigned.setCustom_appointment_id("VS20200724162913");
    mUnassigned.setCity("Bangalore");
    mUnassigned.setPOC_Designation("Marketing executive ");
    mUnassigned.setMarketing_user_firstname("Mohan");
    mUnassigned.setAppointment_time("10:00:00");
    mUnassigned.setMarketing_user_employee_id("VI020PE0016");
    mUnassigned.setImage_address("Kanchan Bhawan, Patna - Gaya Rd, Parsa Bazar, Punpun, Nathupur, Bihar 804453, India Nathupur 804453");
    mUnassigned.setState("on-site");
    mUnassigned.setInstitution_Name("Case ");
    mUnassigned.setInstitution_Status("Appointment completed");
    mUnassigned.setInstitution_Email("cas@gmail.com");
    mUnassigned.setImage_Selection_View("varadhi_smartech/appointments/JPEG_20200724162901_9045066473649732288.jpg");
    mUnassigned.setMarketing_user_id("88c8c0aa-0546-46f7-8c5b-8977196d3a85");
    mUnassigned.setInstitution_Extention_Number(12);
    mUnassigned.setLand_Mark("Ab resistance ");
    mUnassigned.setSite("on-site");
    mUnassigned.setInstitution_STD_Code(80);
    mUnassigned.setMarketing_user_picture("varadhi_smartech/School/Marketing/User/Profile/profile-icon-illustration-user-profile-co_QDNpSqN.png");
    mUnassigned.setMarketing_user_lastname("M");
    mUnassigned.setAppointment_date("2020-07-25");
    mUnassigned.setUpdated_datetime("2020-07-24T11:00:37.978Z");
    mUnassigned.
        setAdded_datetime("2020-07-24T10:59:13.712Z");
    mUnassigned.setPIN(560068);
    mUnassigned.setAppointment_status("Booked");
    mUnassigned.setPOC_Name("Artt");
    mUnassigned.setAppointment_id("c9852c6f-96e8-4bc6-b948-f9b25515e637");
  }

  @After
  public void tearDown() {
    mUnassigned = null;
  }

}
