package com.example.userdetailmappingtask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Appointment {

  private float status_code;
  private String message;
  private String status;

  @SerializedName("assigned")
  @Expose
  ArrayList<Assigned> assigned = null;

  public ArrayList<Assigned> getAssigned() {
    return assigned;
  }

  public void setAssigned(ArrayList<Assigned> assigned) {
    this.assigned = assigned;
  }


  @SerializedName("unassigned")
  @Expose
  ArrayList<Unassigned> unassigned = null;


  public ArrayList<Unassigned> getUnassigned() {
    return unassigned;
  }

  public void setUnassigned(ArrayList<Unassigned> unassigned) {
    this.unassigned = unassigned;
  }


  public float getStatus_code() {
    return status_code;
  }

  public String getMessage() {
    return message;
  }

  public String getStatus() {
    return status;
  }


  public void setStatus_code(float status_code) {
    this.status_code = status_code;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}

