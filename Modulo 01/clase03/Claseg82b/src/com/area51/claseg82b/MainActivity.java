package com.area51.claseg82b;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.area51.adapters.ListaBaseAdapter;
import com.area51.models.ListaModel;

public class MainActivity extends Activity {

	ListView lista;
	ArrayList<ListaModel> arreglo;
	ListaBaseAdapter adapter;
	EditText txtNombre;
	Button btnAgrega;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtNombre = (EditText) findViewById(R.id.txtNombre);
		btnAgrega = (Button) findViewById(R.id.btnAgrega);
		lista = (ListView) findViewById(R.id.lista);

	}

	@Override
	protected void onResume() {

		super.onResume();

		btnAgrega.setTag(-1);

		btnAgrega.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int evalua = Integer.parseInt("" + btnAgrega.getTag());
				// int evalua = Integer.parseInt(
				// btnAgrega.getTag().toString());

				if (evalua == -1) {
					// Agrega un elemento

					if (!txtNombre.getText().toString().equals("")) {

						// Agregamos al arreglo el texto ingreso
						arreglo.add(new ListaModel(arreglo.size(), txtNombre
								.getText().toString()));

						adapter.notifyDataSetChanged();

					} else {

						Toast.makeText(getApplicationContext(),
								"Por favor ingrese texto válido",
								Toast.LENGTH_SHORT).show();

					}

				} else {
					// Edita un elemento

					if (!txtNombre.getText().toString().equals("")) {

						// Editamos el valor de la posicion del
						// elemento al que le hicimos clic :D
						arreglo.get(evalua).setNombreLista(
								txtNombre.getText().toString());

						adapter.notifyDataSetChanged();
						btnAgrega.setTag(-1);

					} else {

						Toast.makeText(getApplicationContext(),
								"Por favor ingrese texto válido",
								Toast.LENGTH_SHORT).show();

					}

				}
				
				txtNombre.setText("");

			}
		});

		if (arreglo == null) {

			arreglo = new ArrayList<ListaModel>();

			/*
			 * for (int i = 0; i < 10; i++) {
			 * 
			 * arreglo.add(new ListaModel(i, "Elemento"));
			 * 
			 * }
			 */
		}

		adapter = new ListaBaseAdapter(arreglo, this);
		lista.setAdapter(adapter);

		// Instanciamos el evento clic de un elemento del listview
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				AlertDialog.Builder dialogo = new AlertDialog.Builder(
						MainActivity.this);

				dialogo.setTitle("Opción").setItems(R.array.opciones,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								switch (which) {
								case 0:
									// Le avisamos al boton que
									// va a editar :D
									btnAgrega.setTag(position);

									txtNombre.setText(arreglo.get(position)   
											.getNombreLista());

									break;
								case 1:
									//Eliminamos un elemento
									//del arreglo :D 
									arreglo.remove(position);
									adapter.notifyDataSetChanged();

									break;

								}

							}
						}).show();

			}

		});

	}

}
