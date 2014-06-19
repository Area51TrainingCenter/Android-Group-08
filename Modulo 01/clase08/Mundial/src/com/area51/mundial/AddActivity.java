package com.area51.mundial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.models.EquipoModel;
import com.area51.utils.Constants;

public class AddActivity extends Activity {

	Button btngrabar;
	TextView txtnombre;
	TextView txtdetalle;
	ImageView imgelegir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		btngrabar = (Button) findViewById(R.id.btngrabar);
		txtnombre = (TextView) findViewById(R.id.txtnombre);
		txtdetalle = (TextView) findViewById(R.id.txtdetalle);
		imgelegir = (ImageView) findViewById(R.id.imgelegir);

	}

	@Override
	protected void onResume() {

		if (Constants.imagen != 0) {
			imgelegir.setBackgroundResource(Constants.imagen);
		}

		if (!Constants.nombre.equals("")) {
			txtnombre.setText(Constants.nombre);
		}

		if (!Constants.detalle.equals("")) {
			txtdetalle.setText(Constants.detalle);
		}

		imgelegir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (!txtnombre.getText().toString().equals("")) {
					Constants.nombre = txtnombre.getText().toString();
				}
				if (!txtdetalle.getText().toString().equals("")) {
					Constants.detalle = txtdetalle.getText().toString();
				}

				Intent i = new Intent(AddActivity.this, BanderasActivity.class);
				startActivity(i);

			}
		});

		btngrabar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				EquipoModel em = new EquipoModel();

				if (Constants.imagen != 0) {
					em.setImagenEquipo(Constants.imagen);
				} else {
					em.setImagenEquipo(R.drawable.b1);

				}

				em.setNombreEquipo(txtnombre.getText().toString());
				em.setDetalleEquipo(txtdetalle.getText().toString());

				Constants.arregloEquipo.add(em);
				finish();

			}
		});

		super.onResume();
	}

}
