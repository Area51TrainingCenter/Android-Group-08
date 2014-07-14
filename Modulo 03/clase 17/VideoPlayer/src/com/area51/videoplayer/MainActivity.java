package com.area51.videoplayer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.area51.utils.Utilitarios;

public class MainActivity extends Activity implements OnPreparedListener,
		OnBufferingUpdateListener, OnCompletionListener, OnErrorListener,
		OnInfoListener, OnVideoSizeChangedListener, Callback,
		OnSeekBarChangeListener {

	SurfaceView surface;
	SurfaceHolder holder;
	MediaPlayer mp;

	ImageView btnplay;
	ImageView btnpause;
	SeekBar seekbar;
	TextView txtcontador;

	String TAG = "Videoplayer";

	// Variables de reproducción
	Handler handler;
	int delay = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		surface = (SurfaceView) findViewById(R.id.videoSurface);
		btnplay = (ImageView) findViewById(R.id.btnplay);
		btnpause = (ImageView) findViewById(R.id.btnpause);
		seekbar = (SeekBar) findViewById(R.id.seekBar);
		txtcontador = (TextView) findViewById(R.id.contador);

		handler = new Handler();
		Utilitarios.tiempo = 0;
		Utilitarios.reproduccion = 0;		

	}

	@Override
	protected void onResume() {
		super.onResume();

		holder = surface.getHolder();
		holder.addCallback(this);

		mp = new MediaPlayer();

		mp.setOnPreparedListener(this);
		mp.setOnBufferingUpdateListener(this);
		mp.setOnCompletionListener(this);
		mp.setOnErrorListener(this);
		mp.setOnInfoListener(this);
		mp.setOnVideoSizeChangedListener(this);

		// Para la calidad del audio
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

		try {
			// Ruta de internet
			// mp.setDataSource( Utilitarios.ruta_video );

			mp.setDataSource(this, Uri.parse(Utilitarios.ruta_video));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Instanciamos botones
		btnplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!mp.isPlaying()) {

					mp.start();
					btnplay.setVisibility(View.GONE);
					btnpause.setVisibility(View.VISIBLE);
					mp.seekTo(Utilitarios.tiempo);

					// Iniciamos el hilo de reproducción
					handler.postDelayed(hilo, delay);
					Utilitarios.reproduccion = 1;
				}

			}
		});

		btnpause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (mp.isPlaying()) {

					mp.pause();
					btnpause.setVisibility(View.GONE);
					btnplay.setVisibility(View.VISIBLE);
					Utilitarios.tiempo = mp.getCurrentPosition();

					// Quitamos el hilo de reproducción
					handler.removeCallbacks(hilo);
					Utilitarios.reproduccion = 0;

				}

			}
		});

	}

	public Runnable hilo = new Runnable() {

		@Override
		public void run() {

			if (mp.isPlaying()) {

				// Obtenemos el tiempo de reproducción
				// para asignarlo al seekbar y mostrarlo en el textview
				Utilitarios.tiempo = mp.getCurrentPosition();
				handler.postDelayed(hilo, delay);
				seekbar.setProgress(Utilitarios.tiempo);
				contador();

			}

		}
	};

	public void contador() {
		// Aquí se especifica el tiempo del videos

		// Asignamos el tiempo al textview
		txtcontador.setText(texto(Utilitarios.tiempo) + " / "
				+ texto_java(mp.getDuration()));
	}

	public String texto_java( int time) {

		String tiempo = String.format("%02d : %02d : %02d",
				TimeUnit.MILLISECONDS.toHours(time),
				TimeUnit.MILLISECONDS.toMinutes(time),
				TimeUnit.MILLISECONDS.toSeconds(time));

		return tiempo;
	}

	public String texto(int tiempo) {
		// Forma 01
		String texto;
		int psegundos = tiempo / 1000;
		// Actualizamos minutos
		int pminutos = psegundos / 60;
		psegundos = psegundos % 60;
		// Actualizamos horas
		int phoras = pminutos / 60;
		pminutos = pminutos % 60;
		texto = String
				.format("%02d : %02d : %02d", phoras, pminutos, psegundos);

		return texto;
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mp.isPlaying()) {
			mp.pause();
			btnpause.setVisibility(View.GONE);
			btnplay.setVisibility(View.VISIBLE);
			Utilitarios.tiempo = mp.getCurrentPosition();
		}

		// Quitamos el hilo de reproducción
		handler.removeCallbacks(hilo);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Quitamos el hilo de reproducción
		handler.removeCallbacks(hilo);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		mp.setDisplay(holder);
		// mp.prepareAsync();

		try {

			mp.prepare();

		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onPrepared(MediaPlayer mp) {

		if (Utilitarios.tiempo != 0) {
			mp.seekTo(Utilitarios.tiempo);
		}

		if( Utilitarios.reproduccion == 1 ){
			mp.start();
			handler.postDelayed(hilo, delay);
			btnpause.setVisibility(View.VISIBLE);
			btnplay.setVisibility(View.GONE);
		}

	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

		seekbar.setMax(mp.getDuration());
		seekbar.setProgress(Utilitarios.tiempo);
		seekbar.setOnSeekBarChangeListener(this);

	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {

		try {

			if (what == MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING) {
				Log.d(TAG, "Media Info, Media Info Bad Interleaving " + extra);
			} else if (what == MediaPlayer.MEDIA_INFO_NOT_SEEKABLE) {
				Log.d(TAG, "Media Info, Media Info Not Seekable " + extra);
			} else if (what == MediaPlayer.MEDIA_INFO_UNKNOWN) {
				Log.d(TAG, "Media Info, Media Info Unknown " + extra);
			} else if (what == MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING) {
				Log.d(TAG, "MediaInfo, Media Info Video Track Lagging " + extra);
			} else if (what == MediaPlayer.MEDIA_INFO_METADATA_UPDATE) {
				Log.d(TAG, "MediaInfo, Media Info Metadata Update " + extra);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {

		try {

			if (what == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
				Log.d(TAG, "Media Error , Murió el servidor " + extra);
			} else if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
				Log.d(TAG, "Media Error, Error desconocido " + extra);
			} else {
				Log.d(TAG, " || " + what + " || " + extra);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// Este evento se dispará al terminar la reproducción del video
		// ya streaming o video dentro de la aplicación

		// Quitamos el hilo de reproducción
		handler.removeCallbacks(hilo);

	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int porcentaje) {

		// Este evento se dispará cuando se hace una reproducción
		// usando el protocolo RTSP

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		if( fromUser ){
			Utilitarios.tiempo = seekBar.getProgress();
			contador();			
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
		//Empieza el arrastre
		handler.removeCallbacks(hilo);
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
		//Iniciamos el hilo
		handler.postDelayed(hilo, delay);
		//Ubicamos el video en ese tiempo
		mp.seekTo(Utilitarios.tiempo);
		
	}

}
