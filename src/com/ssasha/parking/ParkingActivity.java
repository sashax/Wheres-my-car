package com.ssasha.parking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class ParkingActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onButtonClick(View view) {
    	Intent intent;
    
    	switch (view.getId()) {
    	case R.id.park_car_btn :
    		intent = new Intent(this, WheresmycarActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.where_btn :
    		intent = new Intent(this, RetrieveActivity.class);
    		startActivity(intent);
    		break;
    	case R.id.forget_btn :
    		clearPrefs();
    		break;
    	}
    }
    
    //forget location of car and cleaning time
    //prefs stuff should be factored out
    private void clearPrefs() {
    	SharedPreferences mPrefs = getSharedPreferences(IParkingConstants.PREFS, MODE_WORLD_READABLE);
    	SharedPreferences.Editor editor = mPrefs.edit();
    	editor.clear();
    	editor.commit();
    }
}
