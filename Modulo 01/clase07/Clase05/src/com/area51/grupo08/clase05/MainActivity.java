package com.area51.grupo08.clase05;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ScrollView;

import com.area51.grupo08.adapters.ProgramAdapter;
import com.area51.grupo08.custom.ProgramGridview;
import com.area51.grupo08.models.ProgramModel;
import com.area51.grupo08.utils.Constants;

public class MainActivity extends Activity {

	ProgramAdapter adapter;
	ProgramGridview gridview;
	ScrollView scrolito;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridview = (ProgramGridview) findViewById(R.id.gridview);
		scrolito = (ScrollView) findViewById(R.id.scrolito);

	}

	@Override
	protected void onResume() {
		super.onResume();

		// Primero inicializamos el arreglo :D
		if (Constants.ArrayProgram == null) {
			Constants.ArrayProgram = new ArrayList<ProgramModel>();

			// Ahora llenamos el arreglo :D

			for (int i = 0; i < 11; i++) {

				ProgramModel pm = new ProgramModel();

				pm.setIdProgram(i);
				pm.setNameProgram("Programa " + i);
				pm.setPathProgram(Constants.url_image + i
						+ Constants.url_image_extension);

				Constants.ArrayProgram.add(pm);

			}

		}

		// Ahora inicializamos el adapter :D
		adapter = new ProgramAdapter(this, R.layout.item_gridview_descatados,
				Constants.ArrayProgram);

		// Ahora mandamos al gridview
		gridview.setAdapter(adapter);

		// Hacemos que el scrollview se ubique siempre arriba

		scrolito.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {

						scrolito.fullScroll(ScrollView.FOCUS_UP);

					}
				});

		// Evento clic del gridview
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Intent intent = new Intent(MainActivity.this,
						ProgramActivity.class);

				// Usamos un Bundle para guardar la posición
				// al que le hicimos clic
				Bundle b = new Bundle();
				b.putInt("position", position);

				// Asignamos al intent la variable Bundle
				intent.putExtras(b);

				// Iniciamos el cambio de actividad
				startActivity(intent);

			}

		});

	}

}
