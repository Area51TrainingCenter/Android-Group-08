package com.area51.grupo08.adapters;

import java.util.ArrayList;
import java.util.List;

import com.area51.grupo08.clase05.R;
import com.area51.grupo08.library.ImageLoader;
import com.area51.grupo08.models.VideoModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoAdapter extends ArrayAdapter<VideoModel> {

	ArrayList<VideoModel> arrayVideo;
	Context context;

	public VideoAdapter(Context context, int resource,
			ArrayList<VideoModel> arrayVideo) {
		super(context, resource, arrayVideo);

		this.arrayVideo = arrayVideo;
		this.context = context;

	}

	static class ViewHolder {

		public ImageView imageVideo;
		public TextView titleVideo;
		public TextView descriptionVideo;

	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {

		ViewHolder vh;

		if (vista == null) {

			vista = LayoutInflater.from(context).inflate(
					R.layout.item_listview, parent, false);

			// Instanciamos el ViewHolder
			vh = new ViewHolder();

			// Relacionamos las variables con el xml
			vh.imageVideo = (ImageView) vista.findViewById(R.id.imageVideo);
			vh.titleVideo = (TextView) vista.findViewById(R.id.titleVideo);
			vh.descriptionVideo = (TextView) vista
					.findViewById(R.id.descriptionVideo);

			// Guardamos las referencia del viewholder en el tag de la vista
			vista.setTag(vh);

		} else {
			vh = (ViewHolder) vista.getTag();
		}

		// Mandamos los datos recibidos

		// Usamos el imageloader para asignar la imagen al imageVideo
		ImageLoader imageloader = new ImageLoader(context);

		imageloader.DisplayImage(arrayVideo.get(position).getImageVideo(),
				vh.imageVideo);

		vh.titleVideo.setText(arrayVideo.get(position).getNameVideo());
		
		vh.descriptionVideo.setText(arrayVideo.get(position)
				.getDescriptionVideo());

		return vista;
	}

}
