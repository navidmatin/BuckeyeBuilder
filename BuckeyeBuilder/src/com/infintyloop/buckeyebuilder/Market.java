package com.infintyloop.buckeyebuilder;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Market implements Parcelable{

	public static void PayUser(ArrayList<IBuilding> buildings, IUser user){
		int paycheck = 0;
		for (IBuilding building : buildings){
			if(building.GetLevel() > 0){
				paycheck = paycheck + building.GetCurrentGenRate();
			}
		}
		user.MakeMoney(paycheck);
	}
	
	public Market() {
	};
	public Market(Parcel in){
		readFromParcel(in);
	}
	
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void readFromParcel(Parcel in) {
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}
	
	public static final Parcelable.Creator<Market> CREATOR = 
			new Parcelable.Creator<Market>() {
		public Market createFromParcel (Parcel in) {
			return new Market(in);
		}
		public Market[] newArray (int size) {
			return new Market[size];
		}
	};
 
 
	
	
	
	
}
