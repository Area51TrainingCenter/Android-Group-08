package com.area51.clase09;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends Activity {

	EditText txtrusuario;
	EditText txtrnombre;
	EditText txtrclave;
	Button btngraba;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);

		txtrusuario = (EditText) findViewById(R.id.txtrusuario);
		txtrnombre = (EditText) findViewById(R.id.txtrnombre);
		txtrclave = (EditText) findViewById(R.id.txtrclave);
		btngraba = (Button) findViewById(R.id.btngraba);

	}
}
