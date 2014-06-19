package com.area51.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.models.BanderaModel;
import com.area51.mundial.R;

public class BanderaAdapter extends ArrayAdapter<BanderaModel> {

	Context context;
	ArrayList<BanderaModel> arreglo;

	public BanderaAdapter(Context context, int resource,
			ArrayList<BanderaModel> arreglo) {
		super(context, resource, arreglo);

		this.arreglo = arreglo;
		this.context = context;

	}

	static class ViewHolder {

		public ImageView imgbandera;

	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {

		ViewHolder vh;

		if (vista == null) {

			vista = LayoutInflater.from(context).inflate(R.layout.rowbandera,
					parent, false);

			vh = new ViewHolder();

			vh.imgbandera = (ImageView) vista.findViewById(R.id.imgbandera);

			vista.setTag(vh);

		} else {
			vh = (ViewHolder) vista.getTag();
		}

		vh.imgbandera.setBackgroundResource(arreglo.get(position)
				.getImagenBandera());

		return vista;
	}
}
