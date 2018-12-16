package com.example.demo.util;

import lombok.Data;

@Data
public class MyHttpResponse {
	private String status;
	private Object data;
	private Object message;
}
