package com.example.homeautomation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class HttpCall {
	private static DefaultHttpClient client;
	/*
	 * public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
	 * .compile(".+@.+\\.[a-z]+");
	 */

	public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

	static boolean checkEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	static boolean checkPostCode(String postcode) {
		return POSTCODE_PATTERN.matcher(postcode).matches();
	}

	public final static Pattern POSTCODE_PATTERN = Pattern
			.compile("[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}");

	public synchronized static DefaultHttpClient getThreadSafeClient() {

		if (client != null)
			return client;

		client = new DefaultHttpClient();

		ClientConnectionManager mgr = client.getConnectionManager();

		HttpParams params = client.getParams();
		client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,
				mgr.getSchemeRegistry()), params);

		return client;
	}

	private static StringBuilder inputStreamToString(InputStream is) {

		String line = "";
		StringBuilder total = new StringBuilder();
		try {
			// Wrap a BufferedReader around the InputStream
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					"UTF-8"), (100 * 1024));// ,(100 * 1024)

			// Read response until the end

			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
			rd.close();

			Log.d("Inside i.s.>>", total.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return total;
	}
	
	
	

	public static String[] getDeviceOnOff(String url) {	
		
		int res_code;
		String[] response_string = new String[2];
		HttpResponse response;

		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);
		HttpGet httpget = new HttpGet(url);
		
		

		try {
			
			
			System.out.println("Http call url >> "+url);
			client = getThreadSafeClient();
			response = client.execute(httpget);

			res_code = response.getStatusLine().getStatusCode();

			response_string[1] = inputStreamToString(
					response.getEntity().getContent()).toString();
			response_string[0] = "" + res_code;

		} catch (Exception e) {
			
			System.out.println("CATCH From HttpCall >> ");
			e.printStackTrace();
		}

		return response_string;

	}
}





/*public void sendPostData(URLPostBean bean) {
	 
    try {
        HttpParams httpParams = new BasicHttpParams();
 
        ConnManagerParams.setTimeout(httpParams, 10000);
        HttpConnectionParams.setConnectionTimeout(httpParams,50000);
        HttpConnectionParams.setSoTimeout(httpParams, 50000);
 
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost httpPost = new HttpPost(bean.getAddressURL());
 
        httpPost.setEntity(new UrlEncodedFormEntity(bean.getValuePairs()));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
 
        if (httpEntity != null) {
            InputStream inputStream = httpEntity.getContent();
            if (inputStream != null) {
                //Do Someting if input-string not null
            }
        }
 
    } catch (ConnectTimeoutException e) {
        //Here Connection TimeOut excepion
    } catch (Exception e) {
        e.printStackTrace();
    }
 
}*/
