package com.infintyloop.buckeyebuilder;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import com.infintyloop.buckeyebuilder.Market;

public class User implements IUser {

	private String name;
	private int moneyCap;
	// private Location location;
	private int money;
	private long genTime;
	private MoneyGenerator mGenerator;
	private int genRate;
	private int ownedBuildings;
	
	
	//Constructors
	public User() {
		ownedBuildings=0;
		genRate=0;
		genTime=System.currentTimeMillis();
		mGenerator=new MoneyGenerator();
	}
	public MoneyGenerator mGenerator(){
		return mGenerator;
	}
	public User(Parcel in){
		readFrmParce(in);
	}

	public void GiveValuesToUser(String userName, int cap, int cash){
		name = userName;
		moneyCap = cap;
		money = cash;
		// somehow set value to all variables
	}
	
	public String GetUsername(){
		return name;
	}
	
	  
	public int GetMoney(){
		return money;
	}
	
	public void Pay(int amount){
		if(amount<=money)
		{
			money=money-amount;
			moneyCap += 50;
		}
	}
	  
	public int GetCap(){
		return moneyCap;
	}
	
	public int CalculateCurrentGenRate(ArrayList<Building> buildingList)
	{
		int _genRate=0;
		for(Building building : buildingList)
		{
			_genRate+=building.GetCurrentGenRate();
		}
		genRate=_genRate;
		return _genRate;
	}
	/** WRONG IMPLEMENTATION OF MONEY GENERATION, JUST FOR CHECKING **/
	public void MakeMoney() {
		mGenerator.UpdateGenRate(genRate);
		genTime=System.currentTimeMillis();
		int amount=mGenerator.GenerateMoney(genTime);
		if(money+amount < moneyCap){
			money = money + amount;
		}
		else 
			money=moneyCap;
		
	// if money cap is not hit.. then call generate money
	// ODO Auto-generated method stub
	}
	
	public int NumberofBuildingsOwned(){
		return ownedBuildings;
	}
	public void IncreaseNumberofBuildingsOwned(int i){
		ownedBuildings+=i;
	}
	 
	public void Walk() {
	// TODO Auto-generated method stub
	}
	
	 @Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void readFrmParce(Parcel in) {		
			name=in.readString();
			moneyCap=in.readInt();
			money=in.readInt();	
			mGenerator= in.readParcelable(MoneyGenerator.class.getClassLoader());
			ownedBuildings = in.readInt();
			genTime=in.readLong();
			genRate=in.readInt();
	}
	@Override 
	public void writeToParcel(Parcel dest, int flags) {		
		dest.writeString(name);
		dest.writeInt(moneyCap);
		dest.writeInt(money);
		dest.writeParcelable(mGenerator, flags);
		dest.writeInt(ownedBuildings);
		dest.writeLong(genTime);
		dest.writeInt(genRate);
	}
	
	public static final Parcelable.Creator<User> CREATOR = 
			new Parcelable.Creator<User>() {
		public User createFromParcel (Parcel in) {
			return new User(in);
		}
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}