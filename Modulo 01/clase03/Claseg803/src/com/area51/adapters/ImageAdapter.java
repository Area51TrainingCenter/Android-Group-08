package com.area51.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.area51.claseg803.R;
import com.area51.models.ImageModel;

public class ImageAdapter extends ArrayAdapter<ImageModel> {

	ArrayList<ImageModel> arreglo;
	Context context;

	public ImageAdapter(Context context, int resource,
			ArrayList<ImageModel> arreglo) {
		super(context, resource, arreglo);

		this.arreglo = arreglo;
		this.context = context;

	}

	static class ViewHolder {

		public ImageView imageItem;

	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {

		ViewHolder vh;

		if (vista == null) {

			vista = LayoutInflater.from(context).inflate(R.layout.item, parent,
					false);

			vh = new ViewHolder();

			vh.imageItem = (ImageView) vista.findViewById(R.id.imageItem);

			vista.setTag(vh);

		} else {

			vh = (ViewHolder) vista.getTag();

		}
		
		int ruta = 0;
		
		vh.imageItem.setImageResource(ruta);
		

		return vista;
	}

}
