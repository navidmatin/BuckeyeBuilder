package com.infintyloop.buckeyebuilder;
//import android.app.Fragment;
import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuildFragment extends Fragment{
	final static String ARG_POSITION = "position";
	int mCurrentPosition =1;
	private GoogleMap map;
	private SupportMapFragment mapFragment;
	private Fragment userInfoFragment;
	private View userInfoFragmentView;
	private ArrayList<Building> buildingList;
	boolean fragmentState=false;
	int updateMap=0;
	ArrayList<Marker> markerList= new ArrayList<Marker>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		View viewRoot= inflater.inflate(R.layout.build_fragment, container,false);
		
		//For now I'm just using the SupportMapFragment() and not GoogleMapFragment custom fragment

		
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		if(mapFragment==null){
			mapFragment = new  SupportMapFragment();
			//Map stuff
			GoogleMapOptions options = new GoogleMapOptions();
			options.mapType(GoogleMap.MAP_TYPE_NORMAL)
         		.compassEnabled(true)
         		.rotateGesturesEnabled(false)
         		.tiltGesturesEnabled(true)
         		.zoomGesturesEnabled(true);
			mapFragment=SupportMapFragment.newInstance(options);
			transaction.add(R.id.map_fragment, mapFragment);
		}
		userInfoFragment = new UserInfoFragment();
		transaction.add(R.id.user_info_fragment_placeholder, userInfoFragment);	
		transaction.commit();
		return viewRoot;
	}
	private void SetupTheMap(){

		setUpMapIfNeeded();
		if(updateMap<((MainActivity)getActivity()).updateMap)
		{
			buildingList=((MainActivity)getActivity()).buildingList;
			//This section puts a marker for each buildings
			for(Marker m: markerList)
				m.remove();
			for(Building building : buildingList)
			{
					
					double lat=building.GetLatitude();
					double longi=building.GetLongitude();
					LatLng latlng= new LatLng(lat, longi);
					if(building.GetLevel()==0)
					{
						markerList.add(map.addMarker(new MarkerOptions()
								.position(latlng)
								.title(building.GetName())
								.snippet("Not Built Yet")
								.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
								));
					}
					else if(building.GetLevel()==1)
					{
						markerList.add(map.addMarker(new MarkerOptions()
						.position(latlng)
						.title(building.GetName())
						.snippet("Level 1, Generation Rate:"+building.GetCurrentGenRate())
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
						));
					}
					else if(building.GetLevel()==2)
					{
						markerList.add(map.addMarker(new MarkerOptions()
						.position(latlng)
						.title(building.GetName())
						.snippet("Level 2, Generation Rate:"+building.GetCurrentGenRate())
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
						));
					}
					else if(building.GetLevel()==3)
					{
						markerList.add(map.addMarker(new MarkerOptions()
						.position(latlng)
						.title(building.GetName())
						.snippet("Level 3, Generation Rate:"+building.GetCurrentGenRate())
						.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
						));
					}
					
			}
			updateMap++;
		
		}
	}
	@Override
	public void onResume(){
		SetupTheMap();
		super.onResume();
		fragmentState=true;
		updateMapChecker();
		
	}
	@Override
	public void onStart()
	{
		super.onStart();
		userInfoFragmentView=userInfoFragment.getView();
	}
	@Override
	public void onPause()
	{
		fragmentState=false;
		super.onPause();
	}
	private void setUpMapIfNeeded() {
		 // Do a null check to confirm that we have not already instantiated the map.
	    if (map == null) {
	        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (map != null) {
	        	
	        	LatLng l= new LatLng(39.999050, -83.019880);
	        	//CameraPosition camPosition= new CameraPosition(l, 10, mCurrentPosition, mCurrentPosition);
	        	
	        	map.animateCamera(CameraUpdateFactory.newLatLngZoom(l, 15));
	        	int i = userInfoFragmentView.getHeight();
	        	i=userInfoFragmentView.getMeasuredHeight();
	        	map.setMyLocationEnabled(true);
	            
	        }
	    }
	
	}
	private void updateMapChecker() {
		final Handler handler = new Handler();
		Thread runnable = new Thread(new Runnable() {
			private long startTime = System.currentTimeMillis();
			public void run(){
				while(fragmentState)
				{
					try {
						Thread.sleep(2000);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable(){
					
						@Override
						public void run(){
							//Constantly updating the map if needed 
								SetupTheMap();
							
						}
					});
				}
			}
		});
			runnable.start();
	}
}
