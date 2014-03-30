package com.infintyloop.buckeyebuilder;
//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class BuildFragment extends Fragment{
	final static String ARG_POSITION = "position";
	int mCurrentPosition =1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		return inflater.inflate(R.layout.build_fragment, container,false);
		
	}
	@Override
	public void onStart()
	{
		super.onStart();
	/*	IUser user = getActivity();
		int money= user.GetMoney();
		int cap=user.GetCap();
		TextView moneyView = (TextView) getView().findViewById(R.id.textMoneyAmount);
		moneyView.setText(money+"$ /"+cap+"$");*/
		
		
	}
	@Override
	public void onPause()
	{
		super.onPause();
	}

}
