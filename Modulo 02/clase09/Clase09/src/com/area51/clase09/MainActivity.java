package com.area51.clase09;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.area51.sqlite.ManageSqlite;
import com.area51.utils.Constants;

public class MainActivity extends Activity {

	EditText txtusuario;
	EditText txtclave;
	Button btningreso;
	TextView lnkregistro;

	// Para la conexión a sqlite
	ManageSqlite db;
	SQLiteDatabase query;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtusuario = (EditText) findViewById(R.id.txtusuario);
		txtclave = (EditText) findViewById(R.id.txtclave);
		btningreso = (Button) findViewById(R.id.btningreso);
		lnkregistro = (TextView) findViewById(R.id.lnkregistro);

	}

	@Override
	protected void onResume() {

		super.onResume();
		// Abrimos conexión a sqlite
		db = new ManageSqlite(this);
		query = db.getReadableDatabase();
		
		//Validamos si se registro un usuario
		if( Constants.registroUsuario != 0 ){
			
			Intent intent = new Intent(MainActivity.this, HomeActivity.class);
			startActivity(intent);
			
		}
		
		

		lnkregistro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent i = new Intent(MainActivity.this, RegistroActivity.class);
				startActivity(i);

			}
		});

	}

	public void logueoUsuario(View view) {

		// Obtenemos las cadenas ingresadas
		// y las guardamos en 2 variables del tipo String

		String usuario = txtusuario.getText().toString();
		String clave = txtclave.getText().toString();

		if (!usuario.equals("") && !clave.equals("")) {
			// Datos válidos

			String sql = "SELECT * FROM " + Constants.TABLE_USUARIOS
					+ " WHERE " + Constants.COL_USUARIO_USUARIO + " = '"
					+ usuario + "' AND " + Constants.COL_USUARIO_CLAVE + " = '"
					+ clave + "' ";

			// Imprimimos la consulta
			Log.d(Constants.TAG_APP, "sql: " + sql);

			Cursor cursor = query.rawQuery(sql, null);

			// Cantidad filas obtenidas en la consulta
			// cursor.getCount();

			// Nos Ubicamos en el primer registro del resultado de la consulta
			// cursor.moveToFirst();
						
			if (cursor.moveToFirst()) {
				// El usuario existe, ingresa a otra pantalla			
				
				Constants.textoUsuario 
					= cursor.getString(Constants.COL_USUARIO_NOMBRE_INDEX);
				
				//Iniciamos la otra actividad
				Intent intent = new Intent(MainActivity.this, HomeActivity.class);				
				startActivity(intent);
				
				
			} else {
				// El usuario no existe
				Toast.makeText(this, R.string.error_usuario, Toast.LENGTH_SHORT)
						.show();
			}

		} else {
			// Datos inválidos, mostramos un mensaje

			Toast.makeText(this,
					getResources().getString(R.string.error_logueo),
					Toast.LENGTH_SHORT).show();

		}

		
		// query.execSQL(sql, bindArgs)
		// query.insert(table, nullColumnHack, values)
		// query.delete(table, whereClause, whereArgs)
		// query.update(table, values, whereClause, whereArgs)

	}

	public void registroUsuario() {

	}

}