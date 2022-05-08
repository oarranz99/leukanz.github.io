package com.leukanz.response;

import java.util.List;

import com.sun.xml.bind.v2.schemagen.xmlschema.Any;

public class Response {
	
	private String status;
	private Object data;
	private String message;
	
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	public Response(String status, String message ) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.status = status;
	}
	public Response(String status, String message , Object data) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.status = status;
		this.data = data;
	}
	@Override
	public String toString() {
		return super.toString();
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


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
}
