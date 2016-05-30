package com.lihao.suishoufanyi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

public class DownloadJsonTask extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		StringBuffer jsonResult = new StringBuffer();
		try {
			URL url = new URL(params[0]);
			InputStream is = url.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while((line = reader.readLine())!=null){
				jsonResult.append(line);
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return jsonResult.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		
	}

}
