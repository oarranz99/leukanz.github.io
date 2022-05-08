package com.example.errors.products;

public class IncorrectParametersException extends RuntimeException{
	
	private String msg;
	
	
	public IncorrectParametersException(String msg) {
		super();
		this.msg = msg;
	}
	public IncorrectParametersException() {
		// TODO Auto-generated constructor stub
	}
}
