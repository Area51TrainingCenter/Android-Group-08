package com.area51.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import com.area51.fragments.EquiposFragment;
import com.area51.fragments.ResultadoFragment;

public class MainFSPA extends FragmentStatePagerAdapter {

	ListFragment[] fragment;

	public MainFSPA(FragmentManager fm) {
		super(fm);
		fragment = new ListFragment[] { new ResultadoFragment(),
				new EquiposFragment() };

	}

	@Override
	public Fragment getItem(int position) {

		return fragment[position];
	}

	@Override
	public int getCount() {

		return fragment.length;
	}

}
