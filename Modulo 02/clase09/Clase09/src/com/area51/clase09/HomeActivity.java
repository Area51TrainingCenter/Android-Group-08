package com.area51.clase09;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.area51.adapters.UsuarioAdapter;
import com.area51.models.UsuarioModel;
import com.area51.sqlite.ManageSqlite;
import com.area51.utils.Constants;

public class HomeActivity extends Activity {

	TextView textoUsuario;
	ListView listaUsuarios;

	UsuarioAdapter adapter;
	ArrayList<UsuarioModel> arreglo;

	ManageSqlite db;
	SQLiteDatabase query;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		textoUsuario = (TextView) findViewById(R.id.textoUsuario);
		listaUsuarios = (ListView) findViewById(R.id.listaUsuarios);

	}

	@Override
	protected void onResume() {

		super.onResume();

		db = new ManageSqlite(this);
		query = db.getReadableDatabase();

		// Mostramos el nombre del usuario logueado
		textoUsuario.setText(Constants.textoUsuario);

		if (arreglo == null) {

			arreglo = new ArrayList<UsuarioModel>();

			// Traemos los datos de sqlite

			String sql = "SELECT * FROM " + Constants.TABLE_USUARIOS;

			Log.d(Constants.TAG_APP, "sql: " + sql);

			Cursor cursor = query.rawQuery(sql, null);

			if (cursor.moveToFirst()) {

				do {

					UsuarioModel um = new UsuarioModel();

					um.setCorreoUsuario(cursor
							.getString(Constants.COL_USUARIO_USUARIO_INDEX));
					um.setNombreUsuario(cursor
							.getString(Constants.COL_USUARIO_NOMBRE_INDEX));

					arreglo.add(um);

				} while (cursor.moveToNext());

			}

		}

		adapter = new UsuarioAdapter(this, R.layout.row_usuario, arreglo);
		listaUsuarios.setAdapter(adapter);

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		// Cerramos el registro del usuario ( logueado )
		Constants.textoUsuario = "";
		Constants.registroUsuario = 0;

	}

}
