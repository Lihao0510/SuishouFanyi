package com.lihao.suishoufanyi;

public interface JsonCallbackListenner {
	
	void onFinish(String response);

	void onError(Exception e);
}
