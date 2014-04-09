package com.infintyloop.buckeyebuilder;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;

public class MoneyGenerator{
	private int genRate;
	private long genTime; //last time it generated money
	/** Start of Singleton Stuff **/
	private static final MoneyGenerator instance = new MoneyGenerator();

	private MoneyGenerator() {
		genRate=0;
		genTime=System.currentTimeMillis();//Initializing the first generation time to the system time
	}
	public static MoneyGenerator getInstance() {
		return instance;
	}
	/** End of Singleton Stuff **/
	
	public void UpdateGenRate(int _genRate) {
		genRate=_genRate;
	}
	/* 
	 * In the following method, it gets the last time that user looked at the page, it get's the current time and 
	 * it subtract the two from each other to calculate the hours and then it multiplies it with genRate to get the money amount
	 * 
	 * This method must be called each time user look at the user info page, or manage page for the first time
	 * */
	public int GenerateMoney(long viewTime){
		int cash=(int) (((viewTime-genTime)/3600000)*genRate);
		genTime=System.currentTimeMillis();
		return cash;
	}
	public long GetLastGenerationTime(){
		return genTime;
	}
	/* Parcelable stuff 
	 * 
	 
	private MoneyGenerator(Parcel in){
		readFromParcel(in);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	private void readFromParcel(Parcel in){
		genRate=in.readInt();
		genTime=in.readLong();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(genRate);
		dest.writeLong(genTime);
		dest.writeParcelable(instance, flags);	
	}
	
	*/
	
}