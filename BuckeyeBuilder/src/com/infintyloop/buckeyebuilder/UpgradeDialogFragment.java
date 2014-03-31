package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UpgradeDialogFragment extends DialogFragment {
	
	private TextView buildingName;
	private TextView description;
	private TextView level1Cost;
	private TextView level2Cost;
	private TextView level3Cost;
	private TextView level1GenRate;
	private TextView level2GenRate;
	private TextView level3GenRate;
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
		level1Cost= (TextView) view.findViewById(R.id.upgrade_level1_cost);
		level2Cost= (TextView) view.findViewById(R.id.upgrade_level2_cost);
		level3Cost= (TextView) view.findViewById(R.id.upgrade_level3_cost);
		level1GenRate= (TextView) view.findViewById(R.id.upgrade_level1_gen_rate);
		level2GenRate= (TextView) view.findViewById(R.id.upgrade_level2_gen_rate);
		level3GenRate= (TextView) view.findViewById(R.id.upgrade_level3_gen_rate);
	
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
		
		
		
	}
}
