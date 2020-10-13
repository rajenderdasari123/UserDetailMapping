package com.example.userdetailmappingtask.location;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userdetailmappingtask.R;
import com.example.userdetailmappingtask.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static com.example.userdetailmappingtask.constant.EmployeeConstant.TAG_ADDRESS;

public class MapsFragment extends Fragment {

  private String mAddress;
  private GoogleMap mGoogleMap;
  private MapsViewModel mMapsViewModel;
  private FragmentMapsBinding mFragmentMapsBinding;

  private OnMapReadyCallback callback = new OnMapReadyCallback() {

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to
     * install it inside the SupportMapFragment. This method will only be triggered once the
     * user has installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
      mGoogleMap = googleMap;
      getLocationFromAddress(mAddress);
    }
  };

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mFragmentMapsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps,
        container, false);
    mMapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
    mFragmentMapsBinding.setMapsViewModel(mMapsViewModel);
    return mFragmentMapsBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SupportMapFragment mapFragment =
        (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
    if (mapFragment != null) {
      mapFragment.getMapAsync(callback);
    }
    if (requireArguments().getString(TAG_ADDRESS) != null) {
      mAddress = requireArguments().getString(TAG_ADDRESS);
      mFragmentMapsBinding.tvAddressName.tvAddressHint.setText(mAddress);
    }
  }

  /**
   * Get searched location points from address and plot/update on map.
   *
   * @param strAddress Address/Location String
   */
  public void getLocationFromAddress(String strAddress) {
    Geocoder coder = new Geocoder(getContext());
    List<Address> address;

    try {
      address = coder.getFromLocationName(strAddress, 5);
      if (address == null) {
        return;
      }

      Address location = address.get(0);
      LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
      mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
      mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
      mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}