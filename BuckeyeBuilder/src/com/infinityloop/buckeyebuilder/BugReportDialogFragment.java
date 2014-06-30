package com.infinityloop.buckeyebuilder;

import com.parse.ParseObject;
import com.parse.ParseUser;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * A Dialog fragment that opens up through BuildPlan Fragment, and shows building general information
 */
public class BugReportDialogFragment extends DialogFragment{
	private EditText bugDescription;
	private Button button;
	public 	BugReportDialogFragment(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.bug_report_dialog, container);
		button = (Button) view.findViewById(R.id.send_bug_report);
		bugDescription = (EditText) view.findViewById(R.id.bug_description);
		
		
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				ParseObject bug = new ParseObject("Bug");
				bug.add("user", ParseUser.getCurrentUser().getUsername());
				bug.add("bug", bugDescription.getText().toString());
				bug.saveInBackground();
				Toast.makeText(getActivity(), "Thank you for sending us the error, we will look into it as soon as possible", Toast.LENGTH_LONG).show();
				getDialog().dismiss();
			}
			
		});
		return view;	
	}
}
