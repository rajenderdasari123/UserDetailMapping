package com.example.userdetailmappingtask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdetailmappingtask.R;
import com.example.userdetailmappingtask.employee.EmployeeViewModel;
import com.example.userdetailmappingtask.model.Unassigned;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ItemViewHolder> {

  private List<Unassigned> mUnassignedList;
  private EmployeeViewModel mEmployeeViewModel;

  public AppointmentAdapter(EmployeeViewModel employeeViewModel) {
    mEmployeeViewModel = employeeViewModel;
  }

  @NonNull
  @Override
  public AppointmentAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
    ItemViewHolder viewHolder = new ItemViewHolder(listItem);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    holder.tvInstituteName.setText(mUnassignedList.get(position).getInstitution_Name());
    holder.tvPocName.setText(mUnassignedList.get(position).getPOC_Name());
    holder.tvEmployeeId.setText(mUnassignedList.get(position).getCustom_appointment_id());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.employee_to_location);
      }
    });
  }

  @Override
  public int getItemCount() {
    return mUnassignedList != null ? mUnassignedList.size() : 0;
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvInstituteName;
    public TextView tvPocName;
    public TextView tvEmployeeId;

    public ItemViewHolder(View listItem) {
      super(listItem);
      this.tvInstituteName = (TextView) listItem.findViewById(R.id.tv_institute_name);
      this.tvPocName = (TextView) listItem.findViewById(R.id.tv_poc_name);
      this.tvEmployeeId = (TextView) listItem.findViewById(R.id.tv_employee_id);
    }
  }

  public void setAppointmentList(List<Unassigned> unassignedList) {
    mUnassignedList = unassignedList;
  }
}
