package com.area51.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.area51.adapters.ResultadosAdapter;
import com.area51.fifaapp.R;
import com.area51.models.ResultadoModel;
import com.area51.utils.NetworkApp;
import com.area51.utils.RESTClient;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

public class ResultadoFragment extends ListFragment implements
		OnDismissCallback, OnRefreshListener {

	ResultadosAdapter adapter;
	NetworkApp network;
	SwingBottomInAnimationAdapter swingBottomInAnimationAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_resultados, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		network = new NetworkApp(getActivity());

		if (network.getNetwork()) {
			// lamamos al api
			// y el resultado
			// lo guardamos en sqlite
			new RequestResultado()
					.execute("http://worldcup.sfg.io/teams/results");

		} else {
			// sqlite

		}

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);

	}

	public void CargaDatos(String json) {

		adapter = new ResultadosAdapter(getActivity());

		swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
				new SwipeDismissAdapter(adapter, this));

		swingBottomInAnimationAdapter.setInitialDelayMillis(300);
		swingBottomInAnimationAdapter.setAbsListView(getListView());

		try {

			JSONArray data = new JSONArray(json);
			int total = data.length();
			JSONObject item;

			for (int i = 0; i < total; i++) {

				item = (JSONObject) data.get(i);

				ResultadoModel model = new ResultadoModel();
				model.setCountryR(item.getString("country"));
				model.setFifa_codeR(item.getString("fifa_code"));

				model.setGroup_letterR(item.getString("group_letter"));
				model.setWinsR(item.getString("wins"));
				model.setDrawsR(item.getString("draws"));
				model.setLossesR(item.getString("losses"));

				// Agregamos al adapter
				adapter.add(model);

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		setListAdapter(swingBottomInAnimationAdapter);

	}

	class RequestResultado extends AsyncTask<String, Void, String> {

		private ProgressDialog dialogo;

		@Override
		protected void onCancelled() {
			
			super.onCancelled();			
			
		}
		
		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			dialogo = new ProgressDialog(getActivity());
			dialogo.setMessage(getResources().getString(R.string.cargando));
			dialogo.setCancelable(false);
			dialogo.show();
			
		}

		@Override
		protected String doInBackground(String... params) {

			String resultado = "";

			for (String url : params) {

				try {
					resultado = RESTClient.connectAndReturnResponse(url);
				} catch (Exception e) {

				}

			}

			return resultado;
		}

		@Override
		protected void onPostExecute(String result) {

			super.onPostExecute(result);

			dialogo.dismiss();
			
			Log.d("app", "" + result);

			// Guardar los datos en sqlite

			CargaDatos(result);

		}
	}

	@Override
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {

	}

	@Override
	public void onRefresh() {
		
		
	}


}
