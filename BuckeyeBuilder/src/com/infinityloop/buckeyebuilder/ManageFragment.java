package com.infinityloop.buckeyebuilder;

import java.util.ArrayList;

import com.infinityloop.buckeyebuilder.Core.Building;
import com.infinityloop.buckeyebuilder.Core.BuildingFactory;
import com.infinityloop.buckeyebuilder.Core.IUser;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * A fragment that shows all of the buildings that are currently owned
 */
public class ManageFragment extends Fragment {
	ArrayList<Building> buildingList;
	IUser user;
	LinearLayout linearLayout;
	boolean fragmentState=false;
	int ownedBuildings = 0;
	boolean updateList;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.manage_fragment, container, false);
         
        return rootView;
    }
	private void generateCurrentBuilding(){
		if(getActivity()!=null)
		{
			buildingList=((MainActivity)getActivity()).buildingList;
			for (Building building : buildingList)
			{
				if(building.GetLevel()>0)
				{
					ownedBuildings++;
						//Dynamically adding the buttons
					updateList = false;
						Button button = new Button(getActivity());
						LayoutParams params = new  LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
						button.setLayoutParams(params);
						button.setText(building.GetName());
						button.setOnClickListener(showUpgradeFragment(button));
						if(!building.isAffordable(user.GetMoney()))
						{
							button.setTextColor(Color.parseColor("#adadad")); 
							//TODO: Show ready to upgrade button
						}
						if(building.GetLevel()==3)
						{
							button.setTextColor(Color.parseColor("#0099CC"));
						}
						linearLayout.addView(button);	
				}
			}
		}
	}
	@Override
	public void onStart(){
		super.onStart();
		fragmentState=true;
		ownedBuildings=0;
		
		
		updateUI();
	}
	@Override
	public void onResume(){

		super.onResume();
		linearLayout = (LinearLayout) getActivity().findViewById(R.id.manage_list);
		buildingList=((MainActivity)getActivity()).buildingList;
		user=((MainActivity)getActivity()).user;
		if(user.NumberofBuildingsOwned()==0)
		{
			TextView noBuildingMessage = (TextView) getView().findViewById(R.id.no_buildings_message);
			if(noBuildingMessage!=null)
				noBuildingMessage.setText("You have not built any buildings yet, how about you start to build the campus?");
		}

		
		
		
	}
	private View.OnClickListener showUpgradeFragment(final Button button)
	{
		return new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				//Bundling up information related to that building
				Bundle bundle = new Bundle();
				bundle.putParcelable("Building", BuildingFactory.FindBuilding((String)button.getText(), buildingList)); //finding building name based on the Button name
				bundle.putParcelable("User", user);
				//Starting up the Dialog Fragment
				FragmentManager fm= getFragmentManager();
				UpgradeDialogFragment upgradeDialog = new UpgradeDialogFragment();
				upgradeDialog.setArguments(bundle);
				upgradeDialog.show(fm, "upgrade_dialog_fragment");
				
			}
		};
		
	}
	//Constantly updating the Buildings list
	private void updateUI() {
		user=((MainActivity)getActivity()).user;
		final Handler handler = new Handler();
		Thread runnable = new Thread(new Runnable() {
			private long startTime = System.currentTimeMillis();
			public void run(){
				while(fragmentState)
				{
					try {
						Thread.sleep(1000);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable(){
						@Override
						public void run(){
							
							if((user.NumberofBuildingsOwned()>ownedBuildings && user.NumberofBuildingsOwned()>0) || ((MainActivity)getActivity()).updateList)
							{
								
								linearLayout.removeAllViewsInLayout();
								ownedBuildings=0;
								generateCurrentBuilding();
							}
						}
					});
				}
			}
		});
			runnable.start();
	}
	@Override
	public void onStop(){
		super.onStop();
		fragmentState=false;
		((MainActivity)getActivity()).buildingList=buildingList;
	}
}
