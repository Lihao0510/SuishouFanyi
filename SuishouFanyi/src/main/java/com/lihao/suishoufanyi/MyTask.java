package com.lihao.suishoufanyi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lihao.tabviewtest.R;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;

public class MyTask extends AsyncTask<String, Integer, String> {
	private View mContext;
	private EditText resultText;

	public MyTask(View context) {
		super();
		mContext = context;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub

		try {
			String sourceText = URLEncoder.encode(params[0],"utf-8");
			Log.d("Lihao", sourceText);
			String target = new String(
					"http://fanyi.youdao.com/openapi.do?keyfrom=online-translate&key=1005650348&type=data&doctype=json&version=1.1&q="
							+ sourceText);
			URL url = new URL(target);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setReadTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			InputStream in = conn.getInputStream();
			/*
			 * byte[] b = new byte[1024]; int len = 0 ;
			 * while((len=in.read(b))!=-1){
			 * 
			 * }
			 */
			StringBuffer reader = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
			String line;
			while ((line = br.readLine()) != null) {
				reader.append(line);
			}
			String jsonString = new String(reader.toString().getBytes("utf-8"));
			Log.d("Lihao", jsonString);
			br.close();
			conn.disconnect();
			Log.d("Lihao", jsonString);
			return reader.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		resultText = (EditText) mContext.findViewById(R.id.result);
		try {
			
			JSONObject js = new JSONObject(result);
			JSONArray arr = js.getJSONArray("translation");
			String str = arr.optString(0);
			resultText.setText(str);
		} catch (Exception e) {  
			e.printStackTrace();
		}

	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
