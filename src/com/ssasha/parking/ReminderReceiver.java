/**
 * 
 */
package com.ssasha.parking;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author smagee
 *
 */
public class ReminderReceiver extends BroadcastReceiver {

	private static String TAG="BroadcastReceiver";
	
	private String address;
	private double lat, lng;

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		lat =  intent.getDoubleExtra(IParkingConstants.LAT, 0.0);
		lng = intent.getDoubleExtra(IParkingConstants.LNG, 0.0);
		String s = "received notice! " + lat
				+ ", " + lng;
		Log.i(TAG,s);

 		address = intent.getStringExtra(IParkingConstants.ADDRESS);
		this.sendNotification(context, intent); 
	}
	
	private void sendNotification(Context context, Intent intent) {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager nm =  
		            (NotificationManager)context.getSystemService(ns);
		int icon = R.drawable.ic_note;
		CharSequence tickerText = "Street Cleaning";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
		
		//intent and contentView
		Intent intent2 = new Intent("com.ssasha.parking.intent.retrieve");
		intent2.putExtras(intent);
		PendingIntent pi= PendingIntent.getActivity(context, 0, intent2, 0);
		if (address == null)
	 		address = intent.getStringExtra(IParkingConstants.ADDRESS);
		notification.setLatestEventInfo(context, tickerText, "Car near " + address 
				+ " needs to be moved", pi);
		
		//actually send notification
		nm.notify(1, notification);
	}

}
