package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ManageFragment extends Fragment {
	ArrayList<IBuilding> buildingList;
	IUser user;
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
		user=((MainActivity)getActivity()).user;
		for (IBuilding building : buildingList)
		{
			if(building.GetLevel()>0)
			{
					//Dynamically adding the buttons
					linearLayout = (LinearLayout) getActivity().findViewById(R.id.manage_list);
					Button button = new Button(getActivity());
					LayoutParams params = new  LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					button.setLayoutParams(params);
					button.setText(building.GetName());
					
					button.setOnClickListener(showUpgradeFragment(button));
					linearLayout.addView(button);	
			}
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
	/*private void showUpgradeFragment()
	{
		FragmentManager fm= getFragmentManager();
		UpgradeDialogFragment upgradeDialog = new UpgradeDialogFragment();
		upgradeDialog.setArguments(bundle);
		upgradeDialog.show(fm, "upgrade_dialog_fragment");
	}*/
	@Override
	public void onStop(){
		super.onPause();
		linearLayout.removeAllViewsInLayout();
	}
}
