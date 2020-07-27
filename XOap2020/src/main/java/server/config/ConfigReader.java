package server.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader extends Properties{
	private  static ConfigReader configReader;
	private ConfigReader() {
		try {
			readConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ConfigReader getinsisit() {
		if(configReader ==null)
			configReader=new ConfigReader();
		return configReader;
	}
	public int getServer() {
		return Integer.parseInt(getProperty("serverPort"));
	}
	public void readConfig() throws Exception {
		InputStream inputStream= new  FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\server\\config\\config.properties");
		load(inputStream);
	}
}
