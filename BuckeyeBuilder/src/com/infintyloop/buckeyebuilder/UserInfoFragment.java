package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserInfoFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View viewRoot= inflater.inflate(R.layout.user_info_fragment, container,false);
		return viewRoot;
	}
	@Override
	public void onStart()
	{
		super.onStart();
		
		IUser user = ((MainActivity)getParentFragment().getActivity()).user;
		int money= user.GetMoney();
		int cap=user.GetCap();
		TextView moneyView = (TextView) getView().findViewById(R.id.textMoneyAmount);
		moneyView.setText(money+"$ /"+cap+"$");
		
		
	}
}
