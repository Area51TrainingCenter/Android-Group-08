package com.area51.adapters;

import java.util.ArrayList;

import com.area51.clase09.R;
import com.area51.models.UsuarioModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UsuarioAdapter extends ArrayAdapter<UsuarioModel> {

	Context context;
	ArrayList<UsuarioModel> arreglo;

	public UsuarioAdapter(Context context, int resource,
			ArrayList<UsuarioModel> arreglo) {
		super(context, resource, arreglo);

		this.context = context;
		this.arreglo = arreglo;

	}

	static class ViewHolder {

		public TextView nombre;
		public TextView usuario;

	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {

		ViewHolder vh;

		if (vista == null) {

			vista = LayoutInflater.from(context).inflate(R.layout.row_usuario,
					parent, false);

			vh = new ViewHolder();
			vh.nombre = (TextView) vista.findViewById(R.id.nombre);
			vh.usuario = (TextView) vista.findViewById(R.id.usuario);

			vista.setTag(vh);

		} else {
			vh = (ViewHolder) vista.getTag();
		}

		vh.nombre.setText(arreglo.get(position).getNombreUsuario());
		vh.usuario.setText(arreglo.get(position).getCorreoUsuario());

		return vista;
	}

}
