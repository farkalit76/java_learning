package com.farkalit.test.security;

import java.util.Map;

/**
 * @author farkalitusman
 *
 */
public class TestModel {

	private Map<String, String> test;

	private String app;

	private String name;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getName() {
		return name;
	}

	public static void setName(String name) {
		
		int i=0;
		while (i< 10) {
			i++;
			System.out.println("Testnnnn:"+name);
		}
		
	}
	
	
	public static void main(String[] args) {
		setName("Hello");
		
	}

}
