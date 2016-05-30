package com.lihao.suishoufanyi;

public interface JsonCallbackListenner {
	
	void onFinish(String response);
	void onGO(int i);
	void onError(Exception e);
}
