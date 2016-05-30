package com.lihao.suishoufanyi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class NoteHelper {
	
	private Context mContext;
	public NoteHelper(Context context){
		mContext = context;
	}

	public static boolean isEnglishWord(String word) {
		char[] wordArr = word.toCharArray();
		if(wordArr.length == 0){
			return false;
		}
		for (int i = 0; i < wordArr.length; i++) {
			if ((wordArr[i] <= 'z' && wordArr[i] >= 'a') || (wordArr[i] <= 'Z' && wordArr[i] >= 'A')
					|| wordArr[i] == ' ') {
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean saveWord(final String word, final Context context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FileOutputStream fos = null;
				BufferedWriter writer = null;
				try {
					fos = context.openFileOutput("word.txt", context.MODE_APPEND);
					writer = new BufferedWriter(new OutputStreamWriter(fos));
					writer.write(word);
					writer.write("\r\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

		}).start();
		return true;
	}

	public static List<String> loadWords(final Context context) throws Exception {
		final List<String> wordList = new ArrayList<String>();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				FileInputStream fis;
				try {
					fis = context.openFileInput("word.txt");
					BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
					String line = "";
					while ((line = reader.readLine()) != null) {
						wordList.add(line);
					}
					reader.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}).start();
		return wordList;
	}
}
