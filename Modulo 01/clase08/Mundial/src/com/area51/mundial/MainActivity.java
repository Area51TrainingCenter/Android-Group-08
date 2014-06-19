package com.area51.mundial;

import java.util.ArrayList;

import com.area51.adapters.EquipoAdapter;
import com.area51.models.EquipoModel;
import com.area51.utils.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView listaEquipos;
	EquipoAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listaEquipos = (ListView) findViewById(R.id.listaEquipos);

	}

	@Override
	protected void onResume() {

		super.onResume();

		if (Constants.arregloEquipo == null) {

			Constants.arregloEquipo = new ArrayList<EquipoModel>();

		}

		adapter = new EquipoAdapter(this, R.layout.rowequipo,
				Constants.arregloEquipo);

		listaEquipos.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		switch (id) {
		case R.id.btnagrega:

			break;
		case R.id.btnsalir:

			break;

		}

		return super.onOptionsItemSelected(item);
	}

}
