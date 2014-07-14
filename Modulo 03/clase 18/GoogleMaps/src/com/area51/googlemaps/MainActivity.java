package com.area51.googlemaps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity implements LocationListener,
		ConnectionCallbacks, OnConnectionFailedListener {

	GoogleMap map;
	String TAG = "Mapas";

	LocationClient locationClient;

	Context context;

	LocationRequest REQUEST = LocationRequest.create().setInterval(2000)
			.setFastestInterval(16)
			.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

	}

	@Override
	protected void onResume() {
		super.onResume();

		context = this;

		loadMap();
		loadClient();
		locationClient.connect();
	}

	public void loadClient() {

		locationClient = new LocationClient(getApplicationContext(), this, this);

	}

	public void loadMap() {

		Log.d(TAG, "loadMap");

		if (map == null) {

			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			map.setBuildingsEnabled(true);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		switch (id) {
		case R.id.btnnormal:
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.btnhibrido:
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.btnsatelite:
			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.btnterreno:
			map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {

	}

	public void MyLocation(Location location) {

		location.getLatitude();
		location.getLongitude();

		LatLng ltnlng = new LatLng(location.getLatitude(),
				location.getLongitude());
		
		//Camera
		

	}

	@Override
	public void onConnected(Bundle bundle) {

		locationClient.getLastLocation();

	}

	@Override
	public void onDisconnected() {

	}

	@Override
	public void onLocationChanged(Location location) {

		// locationClient.getLastLocation();
	}

}
