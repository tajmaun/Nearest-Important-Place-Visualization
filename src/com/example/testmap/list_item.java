package com.example.testmap;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class list_item extends Activity {
	ArrayAdapter<String>aa;
	 ListView listView ;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub=  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		  listView = (ListView) findViewById(R.id.sampleListView);
		Intent i = getIntent();  
        ArrayList<String> list = i.getStringArrayListExtra("keep");
        aa=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(aa); 
		
	}
	
}
