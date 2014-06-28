package com.area51.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.area51.adapters.ResultadosAdapter;
import com.area51.fifaapp.R;
import com.area51.models.ResultadoModel;
import com.area51.utils.RESTClient;

public class ResultadoFragment extends ListFragment {
	
	
	ResultadosAdapter adapter;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_resultados, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		new RequestResultado().execute("http://worldcup.sfg.io/teams/results");

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);

	}

	public void CargaDatos(String json) {
		
		adapter = new ResultadosAdapter(getActivity());
		
		try {
			
			JSONArray data = new JSONArray(json);
			int total = data.length();
			JSONObject item;			
					
			for (int i = 0; i < total; i++) {
				
				item = (JSONObject)data.get(i);		
				
				ResultadoModel model = new ResultadoModel();
				model.setCountryR(  item.getString("country") );
				
				//Agregamos al adapter
				adapter.add(model);
				
			}
			
			
			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		
		setListAdapter(adapter);
		
	}

	class RequestResultado extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();
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
			
			Log.d("app","" + result);

			CargaDatos(result);

		}
	}

}
