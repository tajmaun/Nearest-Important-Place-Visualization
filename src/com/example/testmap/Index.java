package com.example.testmap;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class Index extends Activity {

	Button button1, button2, button3, button4, button5;
	public String RankBy;
	public String temp;
	public final static String Restaurant = "restaurant|food";
	public final static String Hospital = "hospital|doctor";
	public final static String Establishment = "establishment";
	public final static String Police = "police";
	public final static String Entertainment = "park|amusement_park";
	public static final String[] option = { "Distace", "Importance" };

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_item);
		button1 = (Button) findViewById(R.id.resturant);
		button2 = (Button) findViewById(R.id.hotel);
		button3 = (Button) findViewById(R.id.hospital);
		button4 = (Button) findViewById(R.id.polic_station);
		button5 = (Button) findViewById(R.id.historical_place);

		ActionBar actionbar = getActionBar();
		actionbar.hide();
	}

	/*************************************************************************************************************************************************************************/
	public void sendMessage1(View v) {
		showDialog(Restaurant).show();
	}

	public void sendMessage2(View v) {
		showDialog(Establishment).show();
	}

	public void sendMessage3(View v) {
		showDialog(Hospital).show();
	}

	public void sendMessage4(View v) {
		showDialog(Police).show();

	}

	public void sendMessage5(View v) {
		showDialog(Entertainment).show();
	}

	/*******************************************************************************************************************************/

	public AlertDialog showDialog(final String s) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Sort the place according to ").setItems(option,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (which == 0) {
							RankBy = "distance";
						} else
							RankBy = "prominence";

						Intent i = new Intent(getApplicationContext(),
								Thread_Test.class);
						i.putExtra("tag", s);
						i.putExtra("_rank", RankBy);
						startActivity(i);
					}
				});

		return builder.create();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
}// ********************end of Index class***********************/
