package com.area51.fifaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class LauncherActivity extends Activity {

	Handler handler;
	int contador = 0;
	int delay = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
	}

	@Override
	protected void onResume() {

		super.onResume();

		contador = 0;
		handler = new Handler();
		handler.postDelayed(evalua, delay);

	}

	@Override
	protected void onPause() {

		super.onPause();
		handler.removeCallbacks(evalua);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		handler.removeCallbacks(evalua);

	}

	Runnable evalua = new Runnable() {

		@Override
		public void run() {

			if (contador < 5) {
				contador++;
				handler.postDelayed(evalua, delay);
			} else {
				handler.removeCallbacks(evalua);
				iniciaApp();
			}
			
			Log.d("TAG","contador: " + contador);
			
		}
	};

	public void iniciaApp() {

		Intent intent = new Intent(LauncherActivity.this, MainActivity.class);

		startActivity(intent);

	}
	
	
	
	

}
