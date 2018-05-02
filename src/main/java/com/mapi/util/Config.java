package com.mapi.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	public String configPath;

	public Config(String configPath) {
		this.configPath = configPath;
	}

	public  String getString(String key){
		
		return getInstance().getProperty(key);
	}
	
	public Properties getInstance() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configPath);

		Properties pro = new Properties();
		try {
			pro.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}

}
