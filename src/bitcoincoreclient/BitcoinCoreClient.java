/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitcoincoreclient;

import java.io.IOException;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author doalf
 */
public class BitcoinCoreClient {

    public BitcoinCoreClient() {}
    
    public String newAddress; 

    public void checkBalance() throws org.json.simple.parser.ParseException, IOException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("username", "password"));
        CloseableHttpClient  httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("id", "0");
        json.put("method", "getbalance");
        
        JSONObject responseJsonObj = null;
        try {
            StringEntity myEntity = new StringEntity(json.toJSONString());
            HttpPost httppost = new HttpPost("http://127.0.0.1:18443");
            httppost.setEntity(myEntity);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            responseJsonObj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
        } finally {
            httpclient.close();
        }
        
        System.out.println("Your balance is: " + responseJsonObj.get("result"));
    }

    public void createNewAddress() throws org.json.simple.parser.ParseException, IOException {            
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("username", "password"));
        CloseableHttpClient  httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("id", "0");
        json.put("method", "getnewaddress");
        
        JSONObject responseJsonObj = null;
        try {
            StringEntity myEntity = new StringEntity(json.toJSONString());
            HttpPost httppost = new HttpPost("http://127.0.0.1:18443");
            httppost.setEntity(myEntity);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            responseJsonObj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
        } finally {
            httpclient.close();
        }
        
        newAddress = (String) responseJsonObj.get("result");
        System.out.println("Your new address is : " + responseJsonObj.get("result"));
    }
    
    public void sendToAdress(List<String> params) throws org.json.simple.parser.ParseException, IOException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("username", "password"));
        CloseableHttpClient  httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("id", "0");
        json.put("method", "sendtoaddress");
        
        JSONArray paramsArray = new JSONArray();
        paramsArray.addAll(params);
        json.put("params", paramsArray);
        
        JSONObject responseJsonObj = null;
        try {
            StringEntity myEntity = new StringEntity(json.toJSONString());
            HttpPost httppost = new HttpPost("http://127.0.0.1:18443");
            httppost.setEntity(myEntity);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            responseJsonObj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
        } finally {
            httpclient.close();
        }
        
        System.out.println("Your transaction ID is : " + responseJsonObj.get("result"));
    }
    
    public void listUnspentTransactions(List<Integer> params) throws org.json.simple.parser.ParseException, IOException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("username", "password"));
        CloseableHttpClient  httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("id", "0");
        json.put("method", "listunspent");
        
        JSONArray paramsArray = new JSONArray();
        paramsArray.addAll(params);
        json.put("params", paramsArray);
        
        JSONObject responseJsonObj = null;
        try {
            StringEntity myEntity = new StringEntity(json.toJSONString());
            HttpPost httppost = new HttpPost("http://127.0.0.1:18443");
            httppost.setEntity(myEntity);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            responseJsonObj = (JSONObject) new JSONParser().parse(EntityUtils.toString(entity));
        } finally {
            httpclient.close();
        }
        String[] splitStr = responseJsonObj.get("result").toString().split("}");
        String strToPrint = "";
        for(int i = 0; i < splitStr.length; i++){
            strToPrint = strToPrint + splitStr[i] + "}" + "\n";
        }
        System.out.println("Your unspent transactions are : \n" + strToPrint);
    }
}
