package com.area51.grupo08.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.grupo08.clase05.R;
import com.area51.grupo08.models.ProgramModel;

public class ProgramAdapter extends ArrayAdapter<ProgramModel> {

	ArrayList<ProgramModel> ArrayProgram;
	Context context;

	public ProgramAdapter(Context context, int resource,
			ArrayList<ProgramModel> ArrayProgram) {
		super(context, resource, ArrayProgram);

		this.ArrayProgram = ArrayProgram;
		this.context = context;

	}

	static class ViewHolder {

		public ImageView pathProgram;
		public TextView nameProgram;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder vh;

		if (view == null) {

			view = LayoutInflater.from(context).inflate(
					R.layout.item_gridview_descatados, parent, false);

			vh = new ViewHolder();

			vh.nameProgram = (TextView) view.findViewById(R.id.nameProgram);
			vh.pathProgram = (ImageView) view.findViewById(R.id.pathProgram);

			view.setTag(vh);

		} else {

			vh = (ViewHolder) view.getTag();

		}

		vh.nameProgram.setText(ArrayProgram.get(position).getNameProgram());
		//Falta añadir la imagen :D
		

		return view;

	}

}
