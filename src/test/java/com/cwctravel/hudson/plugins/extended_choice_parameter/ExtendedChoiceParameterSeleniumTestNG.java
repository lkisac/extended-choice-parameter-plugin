package com.cwctravel.hudson.plugins.extended_choice_parameter;

//import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class ExtendedChoiceParameterSeleniumTestNG {
	// @Test
	// public void f() {
	// }

	public static void main(String[] args) throws InterruptedException {
		// Testing Selenium WebDriver
		File file = new File("C:/WebDrivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element;
		List<WebElement> elements;
		Select select;

		// Open Jenkins home page
		driver.get("http://localhost:9090");
		Thread.sleep(2000);

		// Login
		driver.findElement(By.linkText("log in")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("j_username")).sendKeys("admin");
		// Thread.sleep(2000);
		driver.findElement(By.name("j_password")).sendKeys("admin");
		driver.findElement(By.id("yui-gen1-button")).click();
		// Thread.sleep(2000);

		// Create new "Extended-Test" job
		driver.findElement(By.linkText("New Item")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("name")).sendKeys("Extended-Test-Auto");
		driver.findElement(By.xpath("//input[@value='hudson.model.FreeStyleProject']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ok-button")).click();
		Thread.sleep(2000);

		// Configure job
		driver.findElement(By.name("parameterized")).click(); // Check 'This build is parameterized'
		Thread.sleep(2000);
		driver.findElement(By.className("hetero-list-add")).click(); // Add parameter
		Thread.sleep(2000);
		driver.findElement(By.linkText("Extended Choice Parameter")).click(); // Select parameter
		Thread.sleep(2000);
		driver.findElement(By.name("_.name")).sendKeys("Country_2_Levels");
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//input[@type='radio'][./ancestor-or-self::label[contains(., 'Complex Parameter Types')]]"))
				.click(); // Select 'Complex Parameter Types'
		Thread.sleep(2000);
		int size = driver.findElements(By.xpath("//select[@name='type']")).size();
		for (int i = 0; i < size; i++) { // find the visible select list, not invisible ones that exist on the page
			element = driver.findElements(By.xpath("//select[@name='type']")).get(i);
			if (element.isDisplayed()) {
				select = new Select(element);
				select.selectByVisibleText("Multi-Level Multi Select");
				Thread.sleep(2000);
				
				// Property File
				elements = driver.findElements(By.name("_.propertyFile"));
				Iterator<WebElement> iter = elements.iterator();
				while(iter.hasNext()) {
				    element = iter.next();
				}				
				//((JavascriptExecutor)driver).executeScript("document.getElementByXpath(\"//input[@name='_.propertyFile']\")");
				Thread.sleep(1000);
				element.sendKeys("http://localhost:9090/job/Extended-Test-Auto/ws/countries.txt");
				
				// Property Value
				Thread.sleep(2000);
				elements = driver.findElements(By.name("_.propertyValue"));
				iter = elements.iterator();
				while(iter.hasNext()) {
				    element = iter.next();
				}				
				//((JavascriptExecutor)driver).executeScript("document.getElementByXpath(\"//input[@name='_.propertyFile']\")");
				Thread.sleep(1000);
				element.sendKeys("Country,City");				
				break;
			}
		}
		Thread.sleep(2000);

		// Add Build Step 'Execute shell'
		element = driver.findElement(By
				.xpath("//button[@class='hetero-list-add'][contains(., 'Add build step')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Execute shell")).click(); // Shell script to create multi-level properties file
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@name='command']"))
				.sendKeys(
						"# Create config files\necho 'Country\\tCity\\nUnited States\\tSan Francisco\\nUnited States\\tChicago\\nMexico\\tMexico City\\nMexico\\tCancun' > countries.txt\n");
		Thread.sleep(2000);

		// Save Job
		driver.findElement(By.xpath("//button[contains(., 'Save')]")).click();
		
		Thread.sleep(3000);
		
		// Build Extended-Test job
//		driver.findElement(By.linkText("Extended-Test-Auto")).click();
//		Thread.sleep(2000);
		driver.findElement(By.linkText("Build with Parameters")).click();
		Thread.sleep(2000);
		
		// Select from dropdown lists
		select = new Select(driver.findElement(By
				.id("Country_2_Levels dropdown MultiLevelMultiSelect 0")));
		select.selectByVisibleText("United States");
		Thread.sleep(2000);
		select = new Select(driver.findElement(By
				.id("Country_2_Levels dropdown MultiLevelMultiSelect 0 United States")));
		select.selectByVisibleText("San Francisco");
		Thread.sleep(2000);
		driver.findElement(By.id("Country_2_Levels addAnotherButton")).click();
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
		driver.findElement(By.id("yui-gen1-button")).click();
		Thread.sleep(10000);

		// Check Console Output
		driver.findElement(By.className("build-status-link")).click();
		Thread.sleep(10000);

		// Done
		driver.close();
		driver.quit();
	}

}
