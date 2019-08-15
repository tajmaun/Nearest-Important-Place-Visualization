package com.example.testmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Thread_Test extends ListActivity{

	Double a=22.357245;//22.469926;
	Double b=91.837506;//91.790404;
	//CurrentLocation gps;
	StringBuilder sb;
	String type;
	String Rank;
	ArrayList<String>_place=new ArrayList<String>();
	ArrayList<String>Place=new ArrayList<String>();
	ArrayList<String>detail=new ArrayList<String>();
	//List<HashMap<String, String>> list=null;
	ArrayAdapter<String>aa;
	 ListView listView;
	 public static final String[] option = { "Details", "Map","Google Map"};
	private Handler handler = new Handler();

	//List<HashMap<String, String>> places = null;	
	List<HashMap<String, String>> list=null;
/***************************************************************************************************************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.list_view);
		Intent intent=getIntent();
	     //listView = (ListView) findViewById(R.id.sampleListView);
		type=intent.getStringExtra("tag");
		Rank=intent.getStringExtra("_rank");
		aa=new ArrayAdapter<String>(getApplicationContext(), R.layout.light,R.id.label, _place);
	   setListAdapter(aa);
		//gps=new CurrentLocation(this);
		sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
		sb.append("location="+a+","+b);
		if(Rank.contentEquals("prominence"))
		sb.append("&radius=50");
		else
		sb.append("&rankby=distance");
		sb.append("&types="+type);
		sb.append("&sensor=false");
		sb.append("&key=AIzaSyBNKAaUkBRlrOyPBO8Fd5BRADceqyLW0MI");
		Thread t = new Thread(new Runnable() {
			String data=null;
			public void run() {
				try{
				data=downloadUrl(sb.toString());
				}
				catch(Exception e){
					 Log.d("Background Task",e.toString());
				}
				
				list=Parse(data);
			for(int i=0;i<list.size();i++){
				
	            
	            // Getting a place from the places list
	            HashMap<String, String> hmPlace = list.get(i);
	           
	            
	            // Getting name
	            final String name = hmPlace.get("place_name");
	            Place.add(name);
	            handler.post(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(!name.contentEquals("")){
						_place.add(name);
						Place.add(name);

						aa.notifyDataSetChanged();
						}
					}
	            	
	            });
	           
			}
			}
			});
			t.start();
		
			}/****end of oncreate()******/
	
/*************************************************************************************************************************************************************************/
	 /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);                
                

                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();                

                // Connecting to url 
                urlConnection.connect();                

                // Reading data from url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }

                data = sb.toString();

                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }

        return data;
    }
  /**************************************************************************************************************************************************************************/
    protected List<HashMap<String,String>> Parse(String jsonData) {
		JSONObject jObject;
		List<HashMap<String, String>> places = null;	
		PlaceJSONParser placeJsonParser = new PlaceJSONParser();
    
        try{
        	jObject = new JSONObject(jsonData);
        	
            /** Getting the parsed data as a List construct */
            places= placeJsonParser.parse(jObject);
            
        }catch(Exception e){
                Log.d("Exception",e.toString());
        }
        return places;
    }

@Override
public void onListItemClick(ListView parent, View v,  final int position, long id) {
	  /*String item = (String) getListAdapter().getItem(position);
	  Intent in=new Intent(getApplicationContext(),AndroidPaint.class);
	  in.putExtra("places", _place);
	  startActivity(in);*/
	final int d=position;
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Select a Option");
           builder.setItems(option, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
            	   if(which==0)
            	   {
            		   ArrayList<String>detail=new ArrayList<String>();
            		   HashMap<String, String> hmPlace = list.get(position);
            		   detail.add(hmPlace.get("fa"));
            		   detail.add(hmPlace.get("vicinity"));
       	            detail.add(hmPlace.get("fp"));
       	            detail.add(hmPlace.get("web"));
       	            detail.add(hmPlace.get("ip"));
       	            detail.add(hmPlace.get("rtn"));
            		   Intent in=new Intent(getApplicationContext(),list_item.class);
         			  in.putExtra("keep", detail);
         			  startActivity(in);
            	   }
            	   else if(which==1)
            	   {
            		   Intent in=new Intent(getApplicationContext(),AndroidPaint.class);
            			  in.putExtra("places", _place);
            			  //in.putExtra("call", list.get(position));
            			  in.putExtra("call", d);
            			  startActivity(in);   
            	   }
            	   else
            	   {
            		   Intent in=new Intent(getApplicationContext(),Bridge.class);
         			  //in.putExtra("places", _place);
         			  startActivity(in); 
            	   }
            		   
               // The 'which' argument contains the index position
               // of the selected item
           }
    });
           
           AlertDialog dialog = builder.create();
            dialog.show();
	  //Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();

	  // alternatively, use 'position' with a string array defined in your class:
		//selection.setText(projectsAsStrings[position]);
	}
/*public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	// Handle presses on the action bar items
	Log.isLoggable("Background Task",item.getItemId());
	
	
	
	
	
    switch (item.getItemId()) {
        case R.id.action_settings:
        	Toast.makeText(getApplicationContext(), 
        		     "Setting...", 
        		     Toast.LENGTH_SHORT).show();
        	Intent in=new Intent(getApplicationContext(),AndroidPaint.class);
        	in.putExtra("address", sb.toString());
        	startActivity(in);
        	
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }

	//return super.onOptionsItemSelected(item);
}*/

    
    
    /*************************************************************************************************************************************************************************/ 
}/**End of Thread_test***/
