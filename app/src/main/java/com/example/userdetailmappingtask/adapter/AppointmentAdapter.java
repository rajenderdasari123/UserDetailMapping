package com.example.userdetailmappingtask.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.userdetailmappingtask.R;
import com.example.userdetailmappingtask.databinding.ListItemBinding;
import com.example.userdetailmappingtask.employee.EmployeeViewModel;
import com.example.userdetailmappingtask.model.Unassigned;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.userdetailmappingtask.constant.EmployeeConstant.TAG_ADDRESS;

/**
 * Adapter to populate employee information into Recycler View.
 */
public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ItemViewHolder> {

  private List<Unassigned> mUnassignedList;
  private EmployeeViewModel mEmployeeViewModel;

  public AppointmentAdapter(EmployeeViewModel employeeViewModel) {
    mEmployeeViewModel = employeeViewModel;
  }

  /**
   * This method calls onCreateViewHolder(ViewGroup, int) to create a new RecyclerView.ViewHolder and initializes some private fields to be used by RecyclerView.
   *
   * @param parent   {@link ViewGroup}
   * @param viewType int of View type.
   * @return {@link ItemViewHolder}.
   */
  @NonNull
  @Override
  public AppointmentAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    ListItemBinding listItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item,
        parent, false);
    return new ItemViewHolder(listItemBinding);
  }

  /**
   * This method internally calls onBindViewHolder(ViewHolder, int) to update the RecyclerView.ViewHolder contents with the item at the given position and also sets up some private fields to be used by RecyclerView.
   *
   * @param holder   {@link ItemViewHolder}
   * @param position int
   */
  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    holder.bind(mEmployeeViewModel, position);

    holder.itemView.setOnClickListener(view -> {
      Bundle bundle = new Bundle();
      bundle.putString(TAG_ADDRESS, getAddress(mUnassignedList.get(position)));
      NavController navController = Navigation.findNavController(view);
      navController.navigate(R.id.employee_to_location, bundle);
    });
  }

  private String getAddress(Unassigned unassigned) {
    return unassigned.getAddress_Line_1() +","+
        unassigned.getAddress_Line_2() +","+
        unassigned.getLand_Mark() +","+
        unassigned.getCity() +","+
        unassigned.getState() +","+
        unassigned.getPIN();
  }

  /**
   * This method Returns the total number of items in the data set held by the adapter.
   *
   * @return int.
   */
  @Override
  public int getItemCount() {
    return mUnassignedList != null ? mUnassignedList.size() : 0;
  }

  /**
   * Item view holder sets the details for its view
   */
  public static class ItemViewHolder extends RecyclerView.ViewHolder {
    final ListItemBinding listItemBinding;

    void bind(EmployeeViewModel employeeViewModel, Integer position) {
      listItemBinding.setEmployeeViewModel(employeeViewModel);
      listItemBinding.setPosition(position);
      listItemBinding.executePendingBindings();
    }

    /**
     * @param listItemBinding {@link ListItemBinding}.
     */
    public ItemViewHolder(ListItemBinding listItemBinding) {
      super(listItemBinding.getRoot());
      this.listItemBinding = listItemBinding;
    }
  }

  /**
   * sets Adapter Data.
   *
   * @param unassignedList {@link List<Unassigned>}
   */
  public void setAppointmentList(List<Unassigned> unassignedList) {
    mUnassignedList = unassignedList;
  }
}
