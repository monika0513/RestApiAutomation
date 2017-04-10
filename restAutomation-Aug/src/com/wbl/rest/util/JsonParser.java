package com.wbl.rest.util;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonParser {
	private HttpResponse response;

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}
	public int getStatusCode(){
		return response.getStatusLine().getStatusCode();
	}
	public String getResponseMessage(){
		return response.getStatusLine().getReasonPhrase();
	}
	public String getHeaderData(String key){
		return response.getFirstHeader(key).getValue();
	}
	public HashMap<String,String> getAllHeaders(){
		Header[] headers=response.getAllHeaders();
		HashMap<String,String>headerMap =new HashMap<String,String>();
		for(int i=0;i<headers.length;i++){
			headerMap.put(headers[i].getName(),headers[i].getValue());
		}
		return headerMap;
		
	}
	public JSONObject getJsonobject(int index){
		JSONObject value= null;
		try{
		JSONArray json= new JSONArray(response.getEntity().getContent());
		value=json.getJSONObject(index);}
		catch(IOException e){
			e.printStackTrace();
		}return value;
	
		
	}
	

}
