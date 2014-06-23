package com.area51.clase09;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.area51.sqlite.ManageSqlite;
import com.area51.utils.Constants;

public class RegistroActivity extends Activity {

	EditText txtrusuario;
	EditText txtrnombre;
	EditText txtrclave;
	Button btngraba;
	
	//Para la conexión a base de datos
	ManageSqlite db;
	SQLiteDatabase query;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);

		txtrusuario = (EditText) findViewById(R.id.txtrusuario);
		txtrnombre = (EditText) findViewById(R.id.txtrnombre);
		txtrclave = (EditText) findViewById(R.id.txtrclave);
		btngraba = (Button) findViewById(R.id.btngraba);

	}

	@Override
	protected void onResume() {

		super.onResume();
		
		//Inicializamos las variables de conexión
		db = new ManageSqlite(this);
		//Llamamos al getWritableDatabase() para obtener permisos
		//de escritura en la base de datos
		query = db.getWritableDatabase();
		
		

	}

	public void registroUsuario(View view) {

		String usuario = txtrusuario.getText().toString();
		String nombre = txtrnombre.getText().toString();
		String clave = txtrclave.getText().toString();

		if (!usuario.equals("") 
				&& !nombre.equals("") 
					&& !clave.equals("")) {

			
			//Validamos existencia del usuario en la BD (SQLite)
			String sql = "SELECT * FROM " + Constants.TABLE_USUARIOS
					+ " WHERE " + Constants.COL_USUARIO_USUARIO + " = '" + usuario + "'";
			
			Cursor cursor = query.rawQuery(sql, null);
			
			if( cursor.getCount() < 1 ){
				
				//Registramos
				
				ContentValues values = new ContentValues();
				values.put(Constants.COL_USUARIO_USUARIO, usuario);
				values.put(Constants.COL_USUARIO_NOMBRE, nombre);
				values.put(Constants.COL_USUARIO_CLAVE, clave);							
				
				//Hacemos el insert
				if(query.insert(Constants.TABLE_USUARIOS, null, values) > 0 ){
					
					Toast.makeText(this, R.string.registro_usuario, Toast.LENGTH_SHORT)
						.show();				
					//Guardamos en una variable el registro del usuario
					//y el nombre también
					
					Constants.textoUsuario = nombre;
					Constants.registroUsuario = 1;				
					
					//Registro guardado con éxito
					finish();
										
				}else{
					
					Toast.makeText(this, R.string.registro_usuario_error, Toast.LENGTH_SHORT) 
						.show();
					
				}	
							
				
			}else{
				//Mostramos mensaje de existencia de usuario
				
				Toast.makeText(this, R.string.error_usuario_existente, Toast.LENGTH_SHORT)
					.show();
				
			}
			
			
			
			
			
		}else{
			
			Toast.makeText(this, R.string.error_usuario_registro, Toast.LENGTH_SHORT)
				.show();
			
		}

	}

}
