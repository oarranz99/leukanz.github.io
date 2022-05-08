package com.example.errors.products;

public class ProductNotFoundException extends RuntimeException{

	private String msg;
	
	public ProductNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
	public ProductNotFoundException() {
	}
	
}
