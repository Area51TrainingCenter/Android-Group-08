package com.area51.mundial;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.area51.adapters.BanderaAdapter;
import com.area51.models.BanderaModel;
import com.area51.utils.Constants;

public class BanderasActivity extends Activity {

	GridView gridBanderas;
	BanderaAdapter adapter;
	ArrayList<BanderaModel> banderas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banderas);

		gridBanderas = (GridView) findViewById(R.id.gridBanderas);

	}

	@Override
	protected void onResume() {
		super.onResume();

		if (banderas == null) {
			banderas = new ArrayList<BanderaModel>();

			banderas.add(new BanderaModel(0, R.drawable.b1));
			banderas.add(new BanderaModel(1, R.drawable.b2));
			banderas.add(new BanderaModel(2, R.drawable.b1));
			banderas.add(new BanderaModel(3, R.drawable.b2));
			banderas.add(new BanderaModel(4, R.drawable.b1));
			banderas.add(new BanderaModel(5, R.drawable.b2));
			banderas.add(new BanderaModel(6, R.drawable.b1));

		}

		adapter = new BanderaAdapter(this, R.layout.rowbandera, banderas);

		gridBanderas.setAdapter(adapter);

		gridBanderas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Constants.imagen = banderas.get(position).getImagenBandera();
				// terminamos la actividad
				finish();

			}
		});

	}

}
