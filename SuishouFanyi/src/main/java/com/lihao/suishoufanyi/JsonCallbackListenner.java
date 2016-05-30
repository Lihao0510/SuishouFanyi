package com.lihao.suishoufanyi;

public interface JsonCallbackListenner {
	
	void onFinish(String response);

	void whatTheFuck(String fuck);

	void onError(Exception e);
}
