package com.wbl.restapi.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.wbl.rest.util.JsonParser;
import com.wbl.rest.util.configutil;

public class RestApiExecutor {
	public HttpResponse httpGet(String resource,HashMap<String,String> headers,String authorisation,String requestData) throws ClientProtocolException, IOException{
		HttpClient client= HttpClientBuilder.create().build();
		HttpGet get= new HttpGet(configutil.URI+resource+"?"+requestData);
		get.addHeader("Accept","application/json");
		get.addHeader("Content-Type","application/json");
		if(null!=headers){
			for(Map.Entry<String, String>entry: headers.entrySet()){
				get.setHeader(entry.getKey(),entry.getValue());}}
		if(null!=authorisation){
			get.addHeader(HttpHeaders.AUTHORIZATION,authorisation);
		}
		
		HttpResponse response= client.execute(get);
		return response;
		
	}
	public HttpResponse Post(String resource,HashMap<String,String> headers,String authorisation,HashMap<String,String> requestData) throws ClientProtocolException, IOException{
		HttpClient client= HttpClientBuilder.create().build();
		HttpPost post= new HttpPost(configutil.URI+resource);
		post.addHeader("Accept","application/json");
		post.addHeader("Content-Type","application/json");
		if(null!=headers){
			for(Map.Entry<String, String>entry: headers.entrySet()){
				post.setHeader(entry.getKey(),entry.getValue());}}
		if(null!=authorisation){
			post.addHeader(HttpHeaders.AUTHORIZATION,authorisation);
		}
		ArrayList<NameValuePair>reqData = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> entry:requestData.entrySet()){
			reqData.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		}
		//StringEntity entity= new StringEntity("{id:29119,workstatusid:56}");
		HttpEntity entity= new UrlEncodedFormEntity(reqData);
		post.setEntity(entity);
		
		HttpResponse response= client.execute(post);
		return response;}
	
	public JsonParser getJson(HttpResponse response){
		JsonParser jsonParser = new JsonParser();
		jsonParser.setResponse(response);
		return jsonParser;
	}


}