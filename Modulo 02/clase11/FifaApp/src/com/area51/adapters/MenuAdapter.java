package com.area51.adapters;

import com.area51.fifaapp.R;
import com.area51.models.MenuModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<MenuModel> {

	Context context;

	public MenuAdapter(Context context) {
		super(context, 0);

		this.context = context;

	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {

		ViewHolder vh;
		
		if (vista == null) {

			vista = LayoutInflater.from(context).inflate(
					R.layout.row_menu, parent, false);

			vh = new ViewHolder();

			vh.menu_image = (ImageView) vista.findViewById(R.id.menu_image);
			vh.menu_text = (TextView)vista.findViewById(R.id.menu_text);

			vista.setTag(vh);
			
		} else {
			vh = (ViewHolder)vista.getTag();
		}
		
		vh.menu_text.setText( getItem(position).getTitulo() );
		vh.menu_image.setImageResource( getItem(position).getImagen());
		
		

		return vista;
	}

	static class ViewHolder {

		public ImageView menu_image;
		public TextView menu_text;

	}

}
