package com.infinityloop.buckeyebuilderUtilities.adapter;

/**
 * Adapter for tabs!
 */
import com.infinityloop.buckeyebuilder.BuildFragment;
import com.infinityloop.buckeyebuilder.BuildPlanFragment;
import com.infinityloop.buckeyebuilder.ManageFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new BuildFragment();
		case 1:
			// Games fragment activity
			return new ManageFragment();
		case 2:
			// Movies fragment activity
			return new BuildPlanFragment();
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
