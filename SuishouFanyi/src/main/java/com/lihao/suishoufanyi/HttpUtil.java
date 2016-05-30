package com.lihao.suishoufanyi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
	public static String getJsonString(String address,JsonCallbackListenner listenner){
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(8000);
			conn.setConnectTimeout(8000);
			conn.connect();
			InputStream in= conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while((line = reader.readLine())!=null){
				result.append(line);
			}
			listenner.onFinish(result.toString());
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listenner.onError(e);
		}
		return result.toString();
	}
}
