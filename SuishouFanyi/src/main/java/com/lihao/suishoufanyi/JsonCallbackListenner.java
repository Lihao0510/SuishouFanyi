package com.lihao.suishoufanyi;

public interface JsonCallbackListenner {
	
	public void onFinish(String response);

	public void onError(Exception e);
}
