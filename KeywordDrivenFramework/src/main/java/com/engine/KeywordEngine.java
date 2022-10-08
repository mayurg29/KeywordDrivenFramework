package com.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;

import com.base.Base;

public class KeywordEngine extends Base{

	public void startExecution(String sheetName) {
		try {
			excelFile = new FileInputStream(FILE_PATH);
			workbook = WorkbookFactory.create(excelFile); 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = workbook.getSheet(sheetName);
		int lastRow = sheet.getLastRowNum();
		int k = 0;
		
		/**
		 * For loop for iterating excel sheet data
		 */
		for (int i = 0; i < lastRow; i++) {
			try {
				String locatorData = sheet.getRow(i+1).getCell(k+1).toString().trim();
				if (!locatorData.equalsIgnoreCase("NA")) {
					locatorName = locatorData.split("=")[0].trim();
					locatorValue = locatorData.split("=")[1].trim();
				}
				
				String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
				String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
				
				/**
				 * switch case for executing test steps
				 */
				switch (action) {
				case "open browser":
					init_Properties();
					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
						init_Driver(properties.getProperty("browser"));
					} else {
						init_Driver(value);
					}
					break;
					
				case "enter url":
					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
						driver.get(properties.getProperty("url"));
					} else {
						driver.get(value);
					}
					break;
					
				case "sendKeys":
					element = driver.findElement(By.xpath(locatorValue));
					element.clear();
					element.sendKeys(value);
					break;
					
				case "click":
					element = driver.findElement(By.xpath(locatorValue));
					element.click();
					break;
					
				case "isDisplayed":
					element = driver.findElement(By.xpath(locatorValue));
					element.isDisplayed();
					break;
					
				case "quit":
					driver.quit();
					break;
					
				default:
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}