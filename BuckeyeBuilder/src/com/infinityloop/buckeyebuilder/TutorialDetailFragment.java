package com.infinityloop.buckeyebuilder;

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
public class TutorialDetailFragment extends DialogFragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.tutorial_detail_fragment, container);
		
		Button cancelBtn = (Button) view.findViewById(R.id.tutorial_continue_button);
		
		cancelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
			
		});
		return view;	
	}
}
