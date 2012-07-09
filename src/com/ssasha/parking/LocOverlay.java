package com.ssasha.parking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class LocOverlay extends Overlay {
	private GeoPoint myPoint;
	public LocOverlay(GeoPoint gpoint) {
		myPoint = gpoint;
	}
	
	@Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
        Paint paint = new Paint();

        super.draw(canvas, mapView, shadow);
        // Converts lat/lng-Point to OUR coordinates on the screen.
        Point myScreenCoords = new Point();

        mapView.getProjection().toPixels(myPoint, myScreenCoords);

        paint.setStrokeWidth(1);
        paint.setARGB(255, 255, 255, 255);
        paint.setStyle(Paint.Style.STROKE);

        Bitmap bmp = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.wmc_icon);

        canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
        canvas.drawText("Estoy Aqui...", myScreenCoords.x, myScreenCoords.y, paint);
        return true;
    }
}
