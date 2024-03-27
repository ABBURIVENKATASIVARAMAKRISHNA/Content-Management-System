package com.example.cms.utility;

import org.springframework.stereotype.Component;

@Component
public class ResponseStructure<T> {

	private int stutusCode;

	private String message;
	
	private T data;

	public int getStutusCode() {
		return stutusCode;
	}

	public ResponseStructure<T> setStutusCode(int stutusCode) {
		this.stutusCode = stutusCode;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}
	
}
