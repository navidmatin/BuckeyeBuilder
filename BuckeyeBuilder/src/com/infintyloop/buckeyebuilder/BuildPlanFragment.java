package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

public class BuildPlanFragment extends Fragment {
	ArrayList<Building> buildingList;
	private Intent intent;
	LinearLayout linearLayout;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.buildplan_fragment, container, false);
         
        return rootView;
    }

	@Override
	public void onStart(){
		super.onStart();
		buildingList=((MainActivity)getActivity()).buildingList;
		for (Building building : buildingList)
		{
					linearLayout = (LinearLayout) getActivity().findViewById(R.id.buildplan_list);
					Button button = new Button(getActivity());
					LayoutParams params = new  LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					button.setLayoutParams(params);
					button.setText(building.GetName());			
					button.setOnClickListener(showBuildPlanFragment(button));
					linearLayout.addView(button);	
		}
	}
	private View.OnClickListener showBuildPlanFragment(final Button button)
	{
		return new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				//Bundling up information related to that building
				Bundle bundle = new Bundle();
				bundle.putParcelable("Building", BuildingFactory.FindBuilding((String)button.getText(), buildingList)); //finding building name based on the Button name
				//Starting up the Dialog Fragment
				FragmentManager fm= getFragmentManager();
				BuildingDetailFragment buildingDetail = new BuildingDetailFragment();
				buildingDetail.setArguments(bundle);
				buildingDetail.show(fm, "buildplan_fragment");
				
			}
		};
		

	}
	@Override
	public void onStop(){
		super.onPause();
		linearLayout.removeAllViewsInLayout();
	}
}
