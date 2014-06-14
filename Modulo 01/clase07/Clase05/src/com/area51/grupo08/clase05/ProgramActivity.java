package com.area51.grupo08.clase05;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.area51.grupo08.adapters.VideoAdapter;
import com.area51.grupo08.custom.VideoListview;
import com.area51.grupo08.library.ImageLoader;
import com.area51.grupo08.models.VideoModel;
import com.area51.grupo08.utils.Constants;

public class ProgramActivity extends Activity {

	int itemPosition;
	ScrollView scrolitoItem;
	ImageView imageItem;
	TextView nameItem;

	VideoListview videoListview;
	ArrayList<VideoModel> arrayVideo;
	VideoAdapter adapter;
	String urlImagen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_program);

		// Relacionamos variables con los componentes de diseño
		scrolitoItem = (ScrollView) findViewById(R.id.scrolitoItem);
		imageItem = (ImageView) findViewById(R.id.imageItem);
		nameItem = (TextView) findViewById(R.id.nameItem);

		videoListview = (VideoListview) findViewById(R.id.videoListview);

	}

	@Override
	protected void onResume() {

		super.onResume();

		// Recibimos el parametro "position"
		Bundle b = getIntent().getExtras();
		itemPosition = b.getInt("position");

		// Asignamos el nombre del elemento recibido
		nameItem.setText(Constants.ArrayProgram.get(itemPosition)
				.getNameProgram());

		// Asignamos la imagen del programa usando el ImageLoader
		ImageLoader imageloader = new ImageLoader(this);

		urlImagen = Constants.ArrayProgram.get(itemPosition).getPathProgram();

		// usando el imageloader asignamos la imagen
		imageloader.DisplayImage(urlImagen, imageItem);

		if (arrayVideo == null) {

			arrayVideo = new ArrayList<VideoModel>();

			// llenamos de datos el arreglo arrayVideo
			for (int i = 0; i < 10; i++) {

				VideoModel vm = new VideoModel();

				vm.setNameVideo("Video " + i);
				vm.setDescriptionVideo("Descripcion del video " + i);
				vm.setImageVideo(urlImagen);

				arrayVideo.add(vm);

			}// fin del for

		}// fin del if

		// Inicializamos el adapter
		adapter = new VideoAdapter(this, R.layout.item_listview, arrayVideo);

		// Enviamos el adapter al listview

		videoListview.setAdapter(adapter);

		scrolitoItem.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {

						scrolitoItem.fullScroll(ScrollView.FOCUS_UP);

					}
				});

	}

}
