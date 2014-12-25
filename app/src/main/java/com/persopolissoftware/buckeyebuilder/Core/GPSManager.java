package com.persopolissoftware.buckeyebuilder.Core;

/**
 * A service that get's user's current GPS location
 */

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSManager extends Service implements LocationListener {
	private final Context mContext;
	//GPS status
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;
	
	Location location;
	double lat;
	double longi;
	
	//Min distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES= 10;
	
	//Min time between updates in miliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
	
	protected LocationManager locationManager;
	public GPSManager (Context context) {
		this.mContext = context;
	}
	
	public Location getLocation() {
		try {
			locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
			//Getting GPS and Network Status
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if(!isGPSEnabled && !isNetworkEnabled)
			{
				//no network provider is enabled
			}
			else 
			{
				this.canGetLocation = true;
				//First get Location from network
				if(isNetworkEnabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network","Network");
					if(locationManager != null) {
						location= locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if(location != null)
						{
							lat = location.getLatitude();
							longi = location.getLongitude();
						}
					}
				}
				//get the GPS from network
				if(isGPSEnabled){
					if(location==null){
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if(locationManager != null) {
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if(location!=null) {
								lat = location.getLatitude();
								longi = location.getLongitude();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}
	public double getLatitude(){
		if(location!=null){
			lat = location.getLatitude();
		}
		return lat;
	}
	public double getLongitude(){
		if(location!=null){
			longi=location.getLongitude();
		}
		return longi;
	}
	public boolean canGetLocation(){
		return this.canGetLocation;
	}
	
	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
		
		//Dialog title
		alertDialog.setTitle("GPS Error");
		
		//Dialog message
		alertDialog.setMessage("GPS is not enabled, do you want to turn GPS on?");
		alertDialog.setPositiveButton("Enable GPS",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mContext.startActivity(intent);
			}
		});
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				dialog.cancel();
			}
		});
		alertDialog.show();
	}
	public void stopUsingGPS(){
		if(locationManager != null) {
			locationManager.removeUpdates(GPSManager.this);
		}
	}
	
	@Override
	public void onLocationChanged(Location _location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		//Added
		showSettingsAlert();
		
	}
	

}
