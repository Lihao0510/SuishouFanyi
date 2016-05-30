package com.lihao.suishoufanyi;

public class RespondObject {
	
	private String[] translation;
	private String query;
	private int errorCode;

	public String[] getTranslation() {
		return translation;
	}

	public void setTranslation(String[] translation) {
		this.translation = translation;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
