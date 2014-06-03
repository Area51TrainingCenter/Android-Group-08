package com.area51.claseg82b;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
		
		btnAgrega.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if( !txtNombre.getText().equals("") ){
					
					//Agregamos al arreglo el texto ingreso
					
				}else{
					
					Toast.makeText(getApplicationContext(), 
							"Por favor ingrese texto válido", 
							Toast.LENGTH_SHORT)
							.show();
					
				}
				
			}
		} );
		

		if (arreglo == null) {

			arreglo = new ArrayList<ListaModel>();

			for (int i = 0; i < 10; i++) {

				arreglo.add(new ListaModel(i, "Elemento"));

			}
		}
		
		adapter = new ListaBaseAdapter(arreglo, this);
		lista.setAdapter(adapter);

	}

}
