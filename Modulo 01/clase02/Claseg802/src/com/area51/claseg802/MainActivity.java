package com.area51.claseg802;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);

		for (int i = 0; i < 5; i++) {

			menu.getItem(0).getSubMenu().add(i, i, i, "Elemento " + i)
					.setIcon(R.drawable.ic_launcher)
					.setOnMenuItemClickListener(new OnMenuItemClickListener() {

						@Override
						public boolean onMenuItemClick(MenuItem item) {

							Toast.makeText(getApplicationContext(),
									item.getTitle(), Toast.LENGTH_SHORT)
									.show();

							return false;
						}
					});
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

}
