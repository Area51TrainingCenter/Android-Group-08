package com.area51.adapters;

import java.util.ArrayList;

import com.area51.claseg82b.R;
import com.area51.models.ListaModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaBaseAdapter extends BaseAdapter {

	ArrayList<ListaModel> lista;
	Context context;

	public ListaBaseAdapter(ArrayList<ListaModel> lista, Context context) {
		super();
		this.lista = lista;
		this.context = context;
	}

	@Override
	public int getCount() {

		return lista.size();
	}

	@Override
	public Object getItem(int position) {

		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {

		ViewHolder vh;

		if (vista == null) {

			vista = LayoutInflater
					.from(context)
					.inflate(R.layout.item_lista,
					parent, false);

			vh = new ViewHolder();
			vh.txtItem = (TextView) vista.findViewById(R.id.txtItem);

			vista.setTag(vh);

		} else {

			vh = (ViewHolder) vista.getTag();

		}

		vh.txtItem.setText(lista.get(position).getNombreLista());

		return vista;
	}

	static class ViewHolder {

		public TextView txtItem;

	}

}
