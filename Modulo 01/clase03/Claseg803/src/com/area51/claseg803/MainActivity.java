package com.area51.claseg803;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.area51.adapters.ImageAdapter;
import com.area51.models.ImageModel;
import com.area51.utils.Constants;

public class MainActivity extends Activity {

	GridView grid;
	ImageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		grid = (GridView) findViewById(R.id.grid);

	}

	@Override
	protected void onResume() {

		super.onResume();

		if (Constants.lista == null) {

			Constants.lista = new ArrayList<ImageModel>();

			// llenamos la lista :D
		}

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Aquí pasamos la posicion del elemento
				// Al otro activity

				Intent i = new Intent(MainActivity.this,
						DetailActivity.class);
				
				Bundle bundle = new Bundle();

				bundle.putInt("position", position);				
				i.putExtras(bundle);
				
				startActivity(i);
				
				
				
				

			}

		});

	}

}
