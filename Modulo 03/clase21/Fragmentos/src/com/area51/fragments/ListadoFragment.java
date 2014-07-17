package com.area51.fragments;

import com.area51.fragmentos.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListadoFragment extends Fragment {
	
	String TAG = "FRAGMENTO";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, "onAttach");

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");

		View v = new View(getActivity());

		v = inflater.from(getActivity()).inflate(R.layout.fragment_a,
				container, false);

		TextView texto = (TextView) v.findViewById(R.id.texto);

		return v;
		// return
		// LayoutInflater.from(getActivity()).inflate(R.layout.fragment_a,
		// container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		Log.d(TAG, "onActivityCreated");
	}

	@Override
	public void onStart() {

		super.onStart();

		Log.d(TAG, "onStart");
	}

	@Override
	public void onResume() {

		super.onResume();
		Log.d(TAG, "onResume");
	}

	@Override
	public void onPause() {

		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	public void onStop() {

		super.onStop();
		Log.d(TAG, "onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, "onDestroyView");

	}

}
