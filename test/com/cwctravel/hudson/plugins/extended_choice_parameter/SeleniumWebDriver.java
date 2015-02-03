/**
 * This class uses Selenium's WebDriver to demonstrate
 * the functionality of storing multiple values from a multi-level
 * multi select list by running the "Extended-Test" job which contains
 * the "Extended Choice Parameter" multi-level multi-select list.
 * It then selects values and displays all selected values in the Console Output
 * to ensure storing of multiple entries works
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumWebDriver {

	public static void main(String[] args) throws InterruptedException {
		// Testing Selenium WebDriver
		File file = new File("C:/WebDrivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver driver = new ChromeDriver();

		// Open Jenkins home page
		driver.get("http://localhost:9090");
		Thread.sleep(2000);

		// Login
		WebElement link;
		link = driver.findElement(By.linkText("log in"));
		link.click();
		Thread.sleep(2000);
		WebElement textBox;
		textBox = driver.findElement(By.id("j_username"));
		textBox.sendKeys("admin");
		Thread.sleep(2000);
		textBox = driver.findElement(By.name("j_password"));
		textBox.sendKeys("admin");
		link = driver.findElement(By.id("yui-gen1-button"));
		link.click();
		Thread.sleep(2000);

		// Build Extended-Test job
		link = driver.findElement(By.linkText("Extended-Test"));
		link.click();
		Thread.sleep(2000);
		link = driver.findElement(By.linkText("Build with Parameters"));
		link.click();
		Thread.sleep(2000);
		// Select from dropdown lists
		Select select = new Select(driver.findElement(By
				.id("Country_2_Levels dropdown MultiLevelMultiSelect 0")));
		select.selectByVisibleText("United States");
		Thread.sleep(2000);
		select = new Select(driver.findElement(By
				.id("Country_2_Levels dropdown MultiLevelMultiSelect 0 United States")));
		select.selectByVisibleText("San Francisco");
		Thread.sleep(2000);
		link = driver.findElement(By.id("Country_2_Levels addAnotherButton"));
		link.click();
		Thread.sleep(2000);
		select = new Select(driver.findElement(By
				.id("Country_2_Levels dropdown MultiLevelMultiSelect 1")));
		select.selectByVisibleText("Mexico");
		Thread.sleep(4000);
		select = new Select(driver.findElement(By
				.id("Country_2_Levels dropdown MultiLevelMultiSelect 1 Mexico")));
		select.selectByVisibleText("Mexico City");
		Thread.sleep(2000);
		// Build
		link = driver.findElement(By.id("yui-gen1-button"));
		link.click();
		Thread.sleep(10000);

		// Check Console Output
		link = driver.findElement(By.className("build-status-link"));
		link.click();
		Thread.sleep(10000);

		// Done
		driver.close();
		driver.quit();
	}

}
