package com.area51.clase09;

import com.area51.sqlite.ManageSqlite;
import com.area51.utils.Constants;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

		lnkregistro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent i = new Intent(MainActivity.this, RegistroActivity.class);
				startActivity(i);

			}
		});

	}

	public void logueoUsuario() {

		String usuario = "";
		String clave = "";

		String sql = "SELECT * FROM " + Constants.TABLE_USUARIOS + " WHERE "
				+ Constants.COL_USUARIO_USUARIO + " = '" + usuario + "' "
				+ Constants.COL_USUARIO_CLAVE + " = '" + clave + "' ";		

				
		Cursor cursor = query.rawQuery(sql, null);
		
		if(cursor.moveToFirst()){
			
			
			
		}
		
		
		// query.execSQL(sql, bindArgs)
		// query.insert(table, nullColumnHack, values)
		// query.delete(table, whereClause, whereArgs)
		// query.update(table, values, whereClause, whereArgs)

	}

	public void registroUsuario() {

	}

}