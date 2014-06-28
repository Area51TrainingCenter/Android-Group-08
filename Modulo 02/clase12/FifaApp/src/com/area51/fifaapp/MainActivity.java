package com.area51.fifaapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.area51.adapters.MainFSPA;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity implements OnPageChangeListener 
 {

	ViewPager viewpager;
	MainFSPA adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewpager = (ViewPager) findViewById(R.id.viewpager);

		// Para habilitar navegación
		// tipo slidingcontent
		setSlidingActionBarEnabled(false);

	}

	@Override
	protected void onResume() {

		super.onResume();

		adapter = new MainFSPA(getSupportFragmentManager());

		viewpager.setOnPageChangeListener(this);
		viewpager.setAdapter(adapter);

	}

	@Override
	public void onPageScrollStateChanged(int position) {
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int position) {
		

		switch (position) {
		case 0:
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			break;

		default:
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN );
			break;
		}
		
	}



}
