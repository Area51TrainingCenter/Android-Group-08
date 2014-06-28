package com.area51.fragments;

import com.area51.adapters.MenuAdapter;
import com.area51.fifaapp.R;
import com.area51.models.MenuModel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.fragment_menu, null);
	}
	
	
	@Override
	public void onAttach(Activity activity) {
	
		super.onAttach(activity);
		
		MenuAdapter adapter = new MenuAdapter(getActivity());

		adapter.add(new MenuModel(
				R.drawable.ic_action_time,
				getResources().getString(R.string.menu_inicio)	
				));
		
		adapter.add(
				new MenuModel( R.drawable.ic_action_dial_pad,
		getResources().getString(R.string.menu_graficas)
		));
				
		adapter.add(new MenuModel(R.drawable.ic_action_go_to_today,
				getResources().getString(R.string.menu_grupos)
				));
		
		adapter.add(new MenuModel(R.drawable.ic_action_settings,
				getResources().getString(R.string.menu_configuration)
				));
		
		setListAdapter(adapter);
		
	}
	
}
