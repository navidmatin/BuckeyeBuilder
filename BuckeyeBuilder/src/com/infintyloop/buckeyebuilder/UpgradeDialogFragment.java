package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UpgradeDialogFragment extends DialogFragment {
	
	private TextView buildingName;
	private TextView description;
	private TextView levelCost;
	private TextView levelGenRate;
	private TextView level;
	private Bundle bundle;
	private IBuilding building;
	public UpgradeDialogFragment(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.upgrade_dialog_fragment, container);
		
		buildingName= (TextView) view.findViewById(R.id.upgrade_building_title);
		description= (TextView) view.findViewById(R.id.upgrade_building_description);
		level= (TextView) view.findViewById(R.id.upgrade_level);
		levelCost= (TextView) view.findViewById(R.id.upgrade_level_cost);
		levelGenRate= (TextView) view.findViewById(R.id.upgrade_level_gen_rate);
		Button cancelBtn = (Button) view.findViewById(R.id.upgrade_cancel_button);
		cancelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
			
		});
	
		return view;	
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		bundle=this.getArguments();
		building=bundle.getParcelable("Building");
		
		buildingName.setText(building.GetName());
		description.setText(building.GetDescription());
		int levelnumber=building.GetLevel()+1;
		level.setText("Level "+ Integer.toString(levelnumber));
		levelGenRate.setText(building.GetCurrentGenRate()+"$");
		levelCost.setText(building.GetCurrentCost()+"$");
		
		
		
	}
}
