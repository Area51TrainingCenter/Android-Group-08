package com.area51.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkApp {

	Context context;
	NetworkInfo network;
	boolean wifiConnection = false;
	boolean MobileConnection = false;
	String TAG = "NetworkApp";

	public NetworkApp(Context context) {
		super();
		this.context = context;
	}

	public boolean getNetwork() {

		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		network = con.getActiveNetworkInfo();
		if (network != null && network.isConnected()) {

			wifiConnection = network.getType() == ConnectivityManager.TYPE_WIFI;
			MobileConnection = network.getType() == ConnectivityManager.TYPE_MOBILE;

			if (wifiConnection) {

				return true;
			} else if (MobileConnection) {

				return true;
			}
		} else {

		}

		return false;
	}

}
