package com.infintyloop.buckeyebuilder;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class UpgradeDialogFragment extends DialogFragment {
	static UpgradeDialogFragment newInstance(int num) {
		UpgradeDialogFragment upgradefragment = new UpgradeDialogFragment();
		Bundle args = new Bundle();
		args.putInt("Num", num);
		upgradefragment.setArguments(args);
		
		return upgradefragment;
	}
}
