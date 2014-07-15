package com.area51.googlemaps;

import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivity extends Activity implements LocationListener,
		ConnectionCallbacks, OnConnectionFailedListener {

	GoogleMap map;
	String TAG = "Mapas";

	LocationClient locationClient;

	Context context;
	Locale myLocale;

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
			map.setMyLocationEnabled(true);

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
		case R.id.btnfrances:
			
			break;
		case R.id.btnespanol:
			

			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	
	public void cambiaIdioma(String idioma){

		myLocale = new Locale(idioma);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {

	}

	public void MyLocation(Location location) {

		location.getLatitude();
		location.getLongitude();

		// Recibimos latitud y longitud
		LatLng ltnlng = new LatLng(location.getLatitude(),
				location.getLongitude());

		// Agregamos un marker al mapa con nuestra posicion
		MarkerOptions mo = new MarkerOptions();

		// Le ponemos un titulo
		mo.title(getResources().getString(R.string.ubicacion));
		// Le ponemos una descripción
		// mo.snippet(getResources().getString(R.string.descripcion));
		mo.snippet("" + location.getLatitude() + " - "
				+ location.getLongitude());
		// Le ponemos un icono
		mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
		// Le agregamos la ubicación
		mo.position(ltnlng);

		// Añadimos el marker al mapa :D
		map.addMarker(mo);

		// Nos ubicamos en el mapa usando el CameraPosition
		CameraPosition cp = new CameraPosition(ltnlng, 13, 45, 30);

		// Ubicamos la posición de la cámara en el mapa
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

	}

	@Override
	public void onConnected(Bundle bundle) {

		// Obtenemos la última ubicación detectada
		// locationClient.getLastLocation();

		locationClient.requestLocationUpdates(REQUEST, this);

		new Rutas().execute(Utilitario.API);

	}

	@Override
	public void onDisconnected() {

	}

	@Override
	public void onLocationChanged(Location location) {

		// locationClient.getLastLocation();
		// Enviamos la ubicación al método MyLocation
		// MyLocation(locationClient.getLastLocation());

	}

	public void cargaRuta(String json) {

		try {

			JSONObject data = new JSONObject(json);

			if (data.getString("response").equals("OK")) {

				JSONArray rutas = data.getJSONArray("data");

				int total = rutas.length();

				// Obtenemos la información
				JSONObject itemA = (JSONObject) rutas.get((total - 1));
				LatLng llA = new LatLng(itemA.getDouble("LATITUDE"),
						itemA.getDouble("LONGITUDE"));

				Log.d(TAG,
						"" + itemA.getDouble("LATITUDE") + "/"
								+ itemA.getDouble("LONGITUDE"));

				// Seteamos el marker A
				MarkerOptions moA = new MarkerOptions();
				moA.title("Inicio de recorrido");
				moA.position(llA);
				moA.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher));
				map.addMarker(moA);

				// ========================
				JSONObject itemF = (JSONObject) rutas.get(0);
				LatLng llF = new LatLng(itemF.getDouble("LATITUDE"),
						itemF.getDouble("LONGITUDE"));
				// Seteamos el marker Final
				MarkerOptions moF = new MarkerOptions();
				moF.title("Fin de recorrido");
				moF.position(llF);
				moF.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher));
				map.addMarker(moF);

				

				// Nos ubicamos en el mapa usando el CameraPosition
				CameraPosition cp = new CameraPosition(llA, 13, 45, 30);

				// Ubicamos la posición de la cámara en el mapa
				map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

				PolygonOptions poO = new PolygonOptions();
				poO.strokeColor(Color.RED);
				
				
				for (int i = 0; i < total; i++) {
					
					JSONObject elemento = (JSONObject) rutas.get(i);
					
					LatLng lll = new LatLng(elemento.getDouble("LATITUDE"),
							elemento.getDouble("LONGITUDE") );
					
					poO.add(lll);

				}
				
				map.addPolygon(poO);

			} else {
				Toast.makeText(context, "No hay rutas", Toast.LENGTH_SHORT)
						.show();
			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	public class Rutas extends AsyncTask<String, Void, String> {

		private ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pd = new ProgressDialog(context);
			pd.setTitle(getResources().getString(R.string.mensaje));
			pd.setCancelable(false);
			pd.show();

		}

		@Override
		protected String doInBackground(String... params) {

			String response = "";

			for (String url : params) {

				response = RESTClient.connectAndReturnResponse(url);

			}

			return response;
		}

		@Override
		protected void onPostExecute(String result) {

			super.onPostExecute(result);

			pd.dismiss();

			cargaRuta(result);

		}

	}

}
