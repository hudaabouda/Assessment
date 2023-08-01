package com.stc.managingFiles.exception;

public class ErrorResponse {
	Object data;
	String message;
	int runtimeCode;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRuntimeCode() {
		return runtimeCode;
	}

	public void setRuntimeCode(int runtimeCode) {
		this.runtimeCode = runtimeCode;
	}

}
