package com.area51.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.models.EquipoModel;
import com.area51.mundial.R;

public class EquipoAdapter extends ArrayAdapter<EquipoModel> {

	Context context;
	ArrayList<EquipoModel> arreglo;

	public EquipoAdapter(Context context, int resource,
			ArrayList<EquipoModel> arreglo) {
		super(context, resource, arreglo);

		this.arreglo = arreglo;
		this.context = context;

	}

	static class ViewHolder {

		public ImageView imgEquipo;
		public TextView nombreEquipo;
		public TextView detalleEquipo;

	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {

		ViewHolder vh;

		if (vista == null) {

			vista = LayoutInflater.from(context).inflate(R.layout.rowequipo,
					parent, false);

			vh = new ViewHolder();

			vh.imgEquipo = (ImageView) vista.findViewById(R.id.imgEquipo);
			vh.nombreEquipo = (TextView) vista.findViewById(R.id.nombreEquipo);
			vh.detalleEquipo = (TextView) vista
					.findViewById(R.id.detalleEquipo);
			vista.setTag(vh);

		} else {
			vh = (ViewHolder) vista.getTag();
		}

		vh.imgEquipo.setBackgroundResource(arreglo.get(position)
				.getImagenEquipo());
		vh.nombreEquipo.setText(arreglo.get(position).getNombreEquipo());
		vh.detalleEquipo.setText(arreglo.get(position).getDetalleEquipo());

		return vista;
	}
}
