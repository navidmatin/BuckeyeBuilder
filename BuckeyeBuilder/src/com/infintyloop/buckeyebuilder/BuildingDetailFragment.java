package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * A Dialog fragment that opens up through BuildPlan Fragment, and shows building general information
 */
public class BuildingDetailFragment extends DialogFragment{
	private TextView buildingName;
	private TextView description;
	private TextView levelCost1;
	private TextView levelGenRate1;
	private TextView levelCost2;
	private TextView levelGenRate2;
	private TextView levelCost3;
	private TextView levelGenRate3;
	private Bundle bundle;
	private Building building;
	public 	BuildingDetailFragment(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.building_detail_fragment, container);
		
		buildingName= (TextView) view.findViewById(R.id.detail_building_name);
		description= (TextView) view.findViewById(R.id.detail_building_description);
		levelCost1= (TextView) view.findViewById(R.id.detail_first_level_cost);
		levelGenRate1= (TextView) view.findViewById(R.id.detail_first_level_gen_rate);
		levelCost2 = (TextView) view.findViewById(R.id.detail_sec_level_cost);
		levelGenRate2= (TextView) view.findViewById(R.id.detail_sec_level_gen_rate);
		levelCost3 = (TextView) view.findViewById(R.id.detail_third_level_cost);
		levelGenRate3= (TextView) view.findViewById(R.id.detail_third_level_gen_rate);
		Button cancelBtn = (Button) view.findViewById(R.id.detail_cancel_button);
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
		
		int cost = building.GetCurrentCost();
		int genrate = building.GetCurrentGenRate();
		if(building.GetLevel() == 2){
			cost = cost/2;
			genrate = (genrate/2);
		}
		else if(building.GetLevel() == 3){
			cost = cost/3;
			genrate = (genrate/3);
		} 
	levelCost1.setText(Integer.toString(cost) + "$");
		levelCost2.setText(Integer.toString(cost*2) + "$");
		levelCost3.setText(Integer.toString(cost*3) + "$");
		levelGenRate1.setText(Integer.toString(genrate) + "$/hr");
		levelGenRate2.setText(Integer.toString(genrate*2) + "$/hr");
		levelGenRate3.setText(Integer.toString(genrate*3) + "$/hr");
		
		//level.setText("Level "+ Integer.toString(levelnumber));
		// levelGenRate.setText(building.GetCurrentGenRate()+"$");
		
		
		
	}
}
