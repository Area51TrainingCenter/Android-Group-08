package com.area51.grupo08.clase05;

import java.util.ArrayList;

import com.area51.grupo08.adapters.ProgramAdapter;
import com.area51.grupo08.custom.ProgramGridview;
import com.area51.grupo08.models.ProgramModel;
import com.area51.grupo08.utils.Constants;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	ProgramAdapter adapter;
	ProgramGridview gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridview = (ProgramGridview) findViewById(R.id.gridview);

	}

	@Override
	protected void onResume() {
		super.onResume();

		// Primero inicializamos el arreglo :D
		if (Constants.ArrayProgram == null) {
			Constants.ArrayProgram = new ArrayList<ProgramModel>();
		}

		// Ahora llenamos el arreglo :D

		// Ahora inicializamos el adapter :D
		adapter = new ProgramAdapter(this, R.layout.item_gridview_descatados,
				Constants.ArrayProgram);

		// Ahora mandamos al gridview
		gridview.setAdapter(adapter);
		
		

	}

}
