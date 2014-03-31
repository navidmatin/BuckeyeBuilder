package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ManageFragment extends Fragment {
	ArrayList<IBuilding> buildingList;
	LinearLayout linearLayout;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.manage_fragment, container, false);
         
        return rootView;
    }
	@Override
	public void onStart(){
		super.onStart();
		buildingList=((MainActivity)getActivity()).buildingList;
		for (IBuilding building : buildingList)
		{
			linearLayout = (LinearLayout) getActivity().findViewById(R.id.manage_list);
			Button button = new Button(getActivity());
			LayoutParams params = new  LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			button.setLayoutParams(params);
			button.setText("Upgrade "+building.GetName());
			linearLayout.addView(button);	
		}
	}
	@Override
	public void onPause(){
		super.onPause();
		linearLayout.removeAllViewsInLayout();
	}
}
