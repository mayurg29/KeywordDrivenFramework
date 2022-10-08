package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.engine.KeywordEngine;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Mayur Gotad
 *
 */
public class Base {

	protected static WebDriver driver;
	protected static WebElement element;
	protected static Properties properties;
	protected static FileInputStream configFile;
	protected static FileInputStream excelFile;
	protected static Workbook workbook;
	protected static Sheet sheet;
	
	protected static String locatorName;
	protected static String locatorValue;
	protected static KeywordEngine keywordEngine;
	
	protected final static String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\KeywordData.xlsx";

	/**
	 * Method to initialize the driver
	 */
	public WebDriver init_Driver(String browser) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		return driver;
	}

	/**
	 * Method to load the property file
	 */
	public Properties init_Properties() {
		properties = new Properties();
		try {
			configFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			properties.load(configFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
