package com.infintyloop.buckeyebuilder;
//import android.app.Fragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
		return viewRoot;
	}
	@Override
	public void onStart()
	{
		super.onStart();
		mapFragment = new SupportMapFragment();
		userInfoFragment = new UserInfoFragment();
		FragmentTransaction transaction1 = getChildFragmentManager().beginTransaction();
		transaction1.add(R.id.map_fragment, mapFragment).commit();
		FragmentTransaction transaction2 = getChildFragmentManager().beginTransaction();
		transaction2.add(R.id.user_info_fragment_placeholder, userInfoFragment).commit();	
		/*
		IUser user = ((MainActivity)getActivity()).user;
		int money= user.GetMoney();
		int cap=user.GetCap();
		TextView moneyView = (TextView) getView().findViewById(R.id.textMoneyAmount);
		moneyView.setText(money+"$ /"+cap+"$");*/
		
		
	}
	/*@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		FragmentManager fm = getChildFragmentManager();
		mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
		if (mapFragment == null) {
			mapFragment = SupportMapFragment.newInstance();
			fm.beginTransaction().replace(R.id.map_container,mapFragment).commit();
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		if(map == null) {
			map = mapFragment.getMap();
			map.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
		}
	}*/
}
