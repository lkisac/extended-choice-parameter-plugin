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
import org.testng.Assert;
import org.testng.annotations.*;

public class ExtendedChoiceParameterSeleniumTestNG {

	File file;
	ChromeOptions options;
	private static WebDriver driver;
	JavascriptExecutor js;
	WebElement element;
	List<WebElement> elements;
	Select select;
	String jenkinsUrl = "http://localhost:9090";

	@BeforeTest
	public void init() {
		file = new File("C:/WebDrivers/chromedriver.exe");
		// Set the system property indicated by the specified key.
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		js = (JavascriptExecutor) driver;
	}

	@Test
	public void loadJenkinsInstance() throws InterruptedException {
		driver.get(jenkinsUrl);
		Assert.assertEquals("Dashboard [Jenkins]", driver.getTitle());
		Thread.sleep(2000);
	}

	@Test
	public void login() throws InterruptedException {
		driver.findElement(By.linkText("log in")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("j_username")).sendKeys("admin");
		Thread.sleep(1000);
		driver.findElement(By.name("j_password")).sendKeys("admin");
		driver.findElement(By.id("yui-gen1-button")).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElements(By.linkText("log out")).size() != 0);
	}

	@Test(dependsOnMethods = { "login" })
	public void createNewJob() throws InterruptedException {
		driver.findElement(By.linkText("New Item")).click();
		Thread.sleep(2000);
		Assert.assertEquals("New Item [Jenkins]", driver.getTitle());
		driver.findElement(By.name("name")).sendKeys("Extended-Test-Auto");
		driver.findElement(By.xpath("//input[@value='hudson.model.FreeStyleProject']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ok-button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Extended-Test-Auto Config [Jenkins]", driver.getTitle());
	}

	@Test(dependsOnMethods = { "createNewJob" })
	public void configureJob() throws InterruptedException {
		// Check 'This build is parameterized'
		driver.findElement(By.name("parameterized")).click();
		Thread.sleep(2000);
		// Add parameter
		driver.findElement(By.className("hetero-list-add")).click();
		Thread.sleep(2000);
		// Select parameter
		driver.findElement(By.linkText("Extended Choice Parameter")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("_.name")).sendKeys("Country_2_Levels");
		Thread.sleep(2000);
		// Select 'Complex Parameter Types'
		driver.findElement(
				By.xpath("//input[@type='radio'][./ancestor-or-self::label[contains(., 'Complex Parameter Types')]]"))
				.click();
		Thread.sleep(2000);
		int size = driver.findElements(By.xpath("//select[@name='type']")).size();
		// find the visible select list, not invisible ones that exist on the page
		for (int i = 0; i < size; i++) {
			element = driver.findElements(By.xpath("//select[@name='type']")).get(i);
			if (element.isDisplayed()) {
				select = new Select(element);
				select.selectByVisibleText("Multi-Level Multi Select");
				Thread.sleep(2000);

				// Property File
				elements = driver.findElements(By.name("_.propertyFile"));
				Iterator<WebElement> iter = elements.iterator();
				while (iter.hasNext()) {
					element = iter.next();
				}
				Thread.sleep(1000);
				element.sendKeys("http://localhost:9090/job/Extended-Test-Auto/ws/countries.txt");

				// Property Value
				Thread.sleep(2000);
				elements = driver.findElements(By.name("_.propertyValue"));
				iter = elements.iterator();
				while (iter.hasNext()) {
					element = iter.next();
				}
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
		// Shell script to create multi-level properties file
		driver.findElement(By.linkText("Execute shell")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@name='command']"))
				.sendKeys(
						"# Create config files\necho -e 'Country\\tCity\\nUnited States\\tSan Francisco\\nUnited States\\tChicago\\nMexico\\tMexico City\\nMexico\\tCancun' > countries.txt\n");
		driver.findElement(By.xpath("//textarea[@name='command']")).sendKeys(
				"\n# Display select choices in second build\necho ${Country_2_Levels}\n");
		Thread.sleep(2000);
		// Add Build Step 'Execute Windows batch command'
		element = driver.findElement(By
				.xpath("//button[@class='hetero-list-add'][contains(., 'Add build step')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(2000);
		element.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Execute Windows batch command")).click();
		Thread.sleep(2000);
		size = driver.findElements(By.xpath("//textarea[@name='command'][contains(., '')]")).size();
		element = driver.findElements(By.xpath("//textarea[@name='command']")).get(1);
		element.sendKeys(":: In case Shell doesn't print anything\necho %Country_2_Levels%\n");

		Thread.sleep(2000);

		// Save Job
		driver.findElement(By.xpath("//button[contains(., 'Save')]")).click();

		Thread.sleep(3000);

	}
	
	@Test(dependsOnMethods = { "configureJob" })
	public void buildJob() throws InterruptedException {
		 // Build to create config file
		 driver.findElement(By.linkText("Build with Parameters")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//button[contains(., 'Build')]")).click();
		 Thread.sleep(7000);
		
		 // Build job with Extended Choice Parameters
		 driver.findElement(By.linkText("Build with Parameters")).click();
		 Thread.sleep(2000);
		
		 // Select from multi-level dropdown lists
		 select = new Select(driver.findElement(By.id("Country_2_Levels dropdown MultiLevelMultiSelect 0")));
		 select.selectByVisibleText("United States");
		 Thread.sleep(2000);
		 select = new Select(driver.findElement(By.id("Country_2_Levels dropdown MultiLevelMultiSelect 0 United States")));
		 select.selectByVisibleText("San Francisco");
		 Thread.sleep(2000);
		 driver.findElement(By.id("Country_2_Levels addAnotherButton")).click();
		 Thread.sleep(2000);
		 select = new Select(driver.findElement(By.id("Country_2_Levels dropdown MultiLevelMultiSelect 1")));
		 select.selectByVisibleText("Mexico");
		 Thread.sleep(2000);
		 select = new Select(driver.findElement(By.id("Country_2_Levels dropdown MultiLevelMultiSelect 1 Mexico")));
		 select.selectByVisibleText("Mexico City");
		 Thread.sleep(2000);
		 // Build
		 driver.findElement(By.id("yui-gen1-button")).click();
		 Thread.sleep(10000);	
	}
	
	@Test(dependsOnMethods = { "buildJob" })
	public void checkConsoleOutput() throws InterruptedException {
		 // Check Console Output
		 driver.findElement(By.className("build-status-link")).click();
		 Thread.sleep(10000);
		

	}

	 @AfterTest
	 public void deleteJobAndFinish() throws InterruptedException {
		 // Delete Extended-Test-Auto job
		 driver.findElement(By.linkText("Extended-Test-Auto")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.linkText("Delete Project")).click();
		 Thread.sleep(2000);
		 driver.switchTo().alert().accept();
		 Thread.sleep(3000);
		 
		 // Done
		 driver.close();
		 driver.quit();
	 }

}
