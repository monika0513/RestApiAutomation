package com.wbl.rest.test;

import static org.testng.Assert.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wbl.rest.util.ExcelUtil;
import com.wbl.rest.util.JsonParser;
import com.wbl.restapi.main.Basetest;
import com.wbl.restapi.main.RestApiExecutor;
public class CandidatesapiTest extends Basetest{

  @DataProvider(name="rest-data")
	public Object[][] getData(){
		return ExcelUtil.getData();
	}
	
	@Test(dataProvider="rest-data")
	public void testGetCandidates(String resource,String requestData) throws ClientProtocolException, IOException, JSONException{
		RestApiExecutor rs = new RestApiExecutor();
		HttpResponse response= rs.httpGet(resource,null,null,requestData);
		JsonParser parser= rs.getJson(response);
		assertEquals(200,parser.getStatusCode());//statuscode
		assertNotNull(parser.getJsonobject(0));
		assertEquals("ok",parser.getResponseMessage());
       System.out.println("response message::"+response.getStatusLine().getStatusCode());//status code
       System.out.println("status code::"+response.getStatusLine().getReasonPhrase());//this will give response message
		assertEquals("application/json;charset=utf-8",response.getHeaders("content-Type"));

		JSONArray jsonArr= new JSONArray(IOUtils.toString(response.getEntity().getContent()));
		System.out.println("response::"+jsonArr);
		//headers data assertion
		System.out.println("size of array::"+jsonArr.length());

		JSONObject jsonObj= (JSONObject)jsonArr.get(0);
		System.out.println("key value-id::"+jsonObj.get("id"));//actual data
		System.out.println("contact-id"+jsonObj.get("contactid"));
		
		
	}
	
	
	
	

}
