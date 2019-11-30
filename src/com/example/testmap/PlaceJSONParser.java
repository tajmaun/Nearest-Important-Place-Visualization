package com.example.testmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceJSONParser {
	///** Receives a JSONObject and returns a list */
	public List<HashMap<String,String>> parse(JSONObject jObject){		

		JSONArray jPlaces = null;
		try {			
			//	 Retrieves all the elements in the 'places' array 
			jPlaces = jObject.getJSONArray("results");
		} catch (JSONException e) {
			e.printStackTrace();
		}


		return  getPlaces(jPlaces);
	}


	private List<HashMap<String, String>> getPlaces(JSONArray jPlaces){
		int placesCount = jPlaces.length();
		List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> place = null;	

		///** Taking each place, parses and adds to list object */
		for(int i=0; i<placesCount;i++){
			try {
				/** Call getPlace with place JSON object to parse the place */
				place = getPlace((JSONObject)jPlaces.get(i));
				placesList.add(place);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return placesList;
	}

	/** Parsing the Place JSON object */
	private HashMap<String, String> getPlace(JSONObject jPlace){

		HashMap<String, String> place = new HashMap<String, String>();
		String placeName = "-NA-";
		String vicinity="-NA-";
		String latitude="";
		String longitude="";

		//String vicinity="-NA-";
		//String latitude="";
		//String longitude="";
		String formatted_address="Not Found";
		String formatted_phone="+8801711079522";
		String website="www.sadiakitchen.com";
		String rating="3.9";
		String international_phone_number="Not Found";
		//  String url="-NA-";


		try {
			// Extracting Place name, if available
			if(!jPlace.isNull("name")){
				placeName = jPlace.getString("name");
			}

			// Extracting Place Vicinity, if available
			if(!jPlace.isNull("vicinity")){
				vicinity = jPlace.getString("vicinity");
			}	
			if(!jPlace.isNull("formatted_address")){
				formatted_address = jPlace.getString("formatted_address");
			}

			// Extracting Place formatted_phone, if available
			if(!jPlace.isNull("formatted_phone_number")){
				formatted_phone = jPlace.getString("formatted_phone_number");
			}

			// Extracting website, if available
			if(!jPlace.isNull("website")){
				website = jPlace.getString("website");
			}

			// Extracting rating, if available
			if(!jPlace.isNull("rating")){
				rating = jPlace.getString("rating");
			}

			// Extracting rating, if available
			if(!jPlace.isNull("international_phone_number")){
				international_phone_number = jPlace.getString("international_phone_number");
			}
			latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
			longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");			


			place.put("place_name", placeName);
			place.put("vicinity", vicinity);
			place.put("lat", latitude);
			//place.put("lng", longitude);
			place.put("ip", international_phone_number);
			place.put("rtn",  rating);
			place.put("web", website);
			place.put("fp", formatted_phone);
			place.put("fa", formatted_address);



		} catch (JSONException e) {			
			e.printStackTrace();
		}		
		return place;
	}

}
