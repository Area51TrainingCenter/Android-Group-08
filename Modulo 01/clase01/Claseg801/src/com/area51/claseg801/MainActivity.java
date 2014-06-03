package com.area51.claseg801;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	String TAG = "consola";	
	TextView texto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(TAG, "onCreate");
		
		Button btnAgrega = (Button)findViewById(R.id.btnAgrega);
		texto = (TextView)findViewById(R.id.texto);	
		
		
		btnAgrega.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				texto.setText("texto " + texto.getText()   );
				
			}
		});		

	}
	
	public void eventoClic(){
		
		
		
	}
	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart");

	}

	@Override
	protected void onResume() {

		super.onResume();
		Log.d(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

}
