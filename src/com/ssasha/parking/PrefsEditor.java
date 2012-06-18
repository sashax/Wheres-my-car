package com.ssasha.parking;

import java.util.HashMap;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class PrefsEditor {
	public int lat;
	public int lng;
	public String address;
	public long time;
	SharedPreferences mPrefs;
	
	public void write(ContextWrapper cw, int plat, int plng, String paddress, long ptime) {
		lat = plat;
		lng = plng;
		address = paddress;
		time = ptime;
		
		if (mPrefs == null) {
			mPrefs = cw.getSharedPreferences(IParkingConstants.PREFS, Context.MODE_WORLD_READABLE);
		}
		
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(IParkingConstants.LAT, lat);
		editor.putInt(IParkingConstants.LNG, lng);
		editor.putLong(IParkingConstants.MILLIS, time);
		editor.putString(IParkingConstants.ADDRESS, address);
		editor.commit();
	}
	
	public void clearPrefs(ContextWrapper cw) {
		if (mPrefs == null) {
			mPrefs = cw.getSharedPreferences(IParkingConstants.PREFS, Context.MODE_WORLD_READABLE);
		}
		
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.clear();
		editor.commit();
	}
	
	public HashMap<String, Object> read(ContextWrapper cw) {
		if (mPrefs == null) {
			mPrefs = cw.getSharedPreferences(IParkingConstants.PREFS, Context.MODE_WORLD_READABLE);
		}
		lat = mPrefs.getInt(IParkingConstants.LAT, 0);
		lng = mPrefs.getInt(IParkingConstants.LNG, 0);
		time = mPrefs.getLong(IParkingConstants.MILLIS, (long) 0.0);
		address = mPrefs.getString(IParkingConstants.ADDRESS, "address unknown");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(IParkingConstants.LAT, lat);
		map.put(IParkingConstants.LNG, lng);
		map.put(IParkingConstants.MILLIS, time);
		map.put(IParkingConstants.ADDRESS, address);
		
		return map;
	}
	
}
