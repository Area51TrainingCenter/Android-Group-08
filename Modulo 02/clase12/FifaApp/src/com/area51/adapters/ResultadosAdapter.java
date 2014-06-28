package com.area51.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.fifaapp.R;
import com.area51.models.ResultadoModel;

public class ResultadosAdapter extends ArrayAdapter<ResultadoModel> {

	Context context;

	public ResultadosAdapter(Context context) {
		super(context, 0);

		this.context = context;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder vh;
		if (view == null) {

			vh = new ViewHolder();
			view = LayoutInflater.from(context).inflate(
					R.layout.row_resultados, parent, false);

			vh.countryR = (TextView) view.findViewById(R.id.countryR);
			vh.image = (ImageView) view.findViewById(R.id.image);

			view.setTag(vh);

		} else {
			vh = (ViewHolder) view.getTag();
		}

		vh.countryR.setText(getItem(position).getCountryR());

		try {

			int imagenTemporal = context.getResources().getIdentifier(
					getItem(position).getFifa_codeR().toLowerCase(),
					"drawable", context.getPackageName());

			vh.image.setImageDrawable(context.getResources().getDrawable(
					imagenTemporal));

		} catch (Exception e) {

		}
		return view;
	}

	static class ViewHolder {

		public TextView countryR;
		public ImageView image;

	}

}