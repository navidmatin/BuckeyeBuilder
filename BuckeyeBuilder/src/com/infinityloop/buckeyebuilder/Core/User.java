package com.infinityloop.buckeyebuilder.Core;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User class which holds all of the user information
 */
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
	// returns user's money generator
	public MoneyGenerator mGenerator(){
		return mGenerator;
	}
	// makes user parcelable
	public User(Parcel in){
		readFrmParce(in);
	}
	// if user is being loaded, loads values
	public void GiveValuesToUser(String userName, int cap, int cash){
		name = userName;
		moneyCap = cap;
		money = cash;
	}
	// returns username
	public String GetUsername(){
		return name;
	}
	// returns money
	public int GetMoney(){
		return money;
	}
	// deducts a certain amount from money and increases the amount of money that
	// can be owned
	public void Pay(int amount){
		if(amount<=money)
		{
			money=money-amount;
			moneyCap += 50;
		}
	}
	// returns cap on money
	public int GetCap(){
		return moneyCap;
	}
	// the total amount that the user is generating
	public int CalculateCurrentGenRate(ArrayList<Building> buildingList)
	{
		int _genRate=0;
		// gets the genrate of each building and adds them for lump sum
		for(Building building : buildingList)
		{
			_genRate+=building.GetCurrentGenRate();
		}
		genRate=_genRate;
		return _genRate;
	}
	public void IncreaseCap(Building building)
	{
		moneyCap+=building.GetCurrentCost()/2;
	}
	/** WRONG IMPLEMENTATION OF MONEY GENERATION, JUST FOR CHECKING **/
	// if the money is not at the current cap, make some amount of money
	public void MakeMoney() {
		mGenerator.UpdateGenRate(genRate);
		genTime=System.currentTimeMillis();
		int amount=mGenerator.GenerateMoney(genTime);
		if(money+amount < moneyCap){
			money = money + amount;
		}
		else 
			money=moneyCap;
	}
	// returns the number of buildings that are owned
	public int NumberofBuildingsOwned(){
		return ownedBuildings;
	}
	// increments your building total
	public void IncreaseNumberofBuildingsOwned(int i){
		ownedBuildings+=i;
	}
	// currently nonfunctional
	public void Walk() {
	}
	// makes serializable
	 @Override
	public int describeContents() {
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