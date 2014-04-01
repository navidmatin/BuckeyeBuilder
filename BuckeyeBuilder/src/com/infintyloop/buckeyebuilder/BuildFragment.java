package com.infintyloop.buckeyebuilder;
//import android.app.Fragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class BuildFragment extends Fragment{
	final static String ARG_POSITION = "position";
	int mCurrentPosition =1;
	private GoogleMap map;
	private SupportMapFragment mapFragment;
	private Fragment userInfoFragment;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		View viewRoot= inflater.inflate(R.layout.build_fragment, container,false);
		
		//For now I'm just using the SupportMapFragment() and not GoogleMapFragment custom fragment
		mapFragment = new  SupportMapFragment();
		userInfoFragment = new UserInfoFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.id.map_fragment, mapFragment);
		transaction.add(R.id.user_info_fragment_placeholder, userInfoFragment);	
		transaction.commit();
		
		return viewRoot;
	}
	@Override
	public void onStart()
	{
		super.onStart();
		setUpMapIfNeeded();
		//map=((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();	
	}
	private void setUpMapIfNeeded() {
		 // Do a null check to confirm that we have not already instantiated the map.
	    if (map == null) {
	        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (map != null) {
	        	//CameraPosition camPosition= new CameraPosition(null, mCurrentPosition, mCurrentPosition, mCurrentPosition);
	            GoogleMapOptions options = new GoogleMapOptions();
	            options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
	            	.compassEnabled(true)
	            	.rotateGesturesEnabled(false)
	            	.tiltGesturesEnabled(true)
	            	.zoomGesturesEnabled(true);
	            SupportMapFragment.newInstance(options);
	            map.setPadding(10, 60, 10, 10);
	            	

	        }
	    }
	
	}
}
