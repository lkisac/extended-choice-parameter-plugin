/**
 * 
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

import hudson.EnvVars;
import hudson.model.*;
import hudson.slaves.EnvironmentVariablesNodeProperty;
import hudson.tasks.Shell;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.kohsuke.stapler.StaplerRequest;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import net.sf.json.JSONObject;

/**
 * @author lkisac
 *
 */
public class ExtendedChoiceParameterDefinitionTest {
	private static String name = "QA";
	private static String type = "PT_MULTI_LEVEL_MULTI_SELECT";
	private static String value = "{1:cfxunix,delinked,ALL:2:ifdev1,linked,aamqa:}";
	private static String propertyFile,
			groovyScript,
			groovyScriptFile,
			bindings,
			propertyKey,
			defaultValue,
			defaultPropertyFile,
			defaultGroovyScript,
			defaultGroovyScriptFile,
			defaultBindings,
			defaultPropertyKey = propertyFile = groovyScript = groovyScriptFile = bindings = propertyKey = defaultValue = defaultPropertyFile = defaultGroovyScript = defaultGroovyScriptFile = defaultBindings = "";
	private static boolean quoteValue = false;
	private static Integer visibleItemCount = 5;
	private static String description = "";
	private static String multiSelectDelimiter = ",";

	// Class object - use to call ExtendedChoiceParameterDefinition methods
	public static ExtendedChoiceParameterDefinition obj;

	// Used to call createValue(StaplerRequest, JSONObjecT)
	StaplerRequest staplerObj;
	JSONObject jsonObj = new JSONObject();

	private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);
	private static URL jenkinsURL = null;
	private static HtmlForm form = null;
	private static HtmlPage page = null;

	// Jenkins login credentials	
	final private String userName = "lisac";
	final private String passWord = "123456";
	final private String fullName = "Len Isac";

	// Logger
	public static Logger logger;
	public static ConsoleHandler handler;

	// Rule
	@Rule
	public JenkinsRule j = new JenkinsRule();

	/**
	 * Initialize new ExtendedChoiceParameterDefinition object to test methods
	 * with
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// ExtendedChoiceParameterDefinition object
		obj = new ExtendedChoiceParameterDefinition(name, type, value, propertyFile, groovyScript,
				groovyScriptFile, bindings, propertyKey, defaultValue, defaultPropertyFile,
				defaultGroovyScript, defaultGroovyScriptFile, defaultBindings, defaultPropertyKey,
				quoteValue, visibleItemCount, description, multiSelectDelimiter);

		logger = Logger.getLogger(ExtendedChoiceParameterDefinitionTest.class.getName());
		handler = new ConsoleHandler();
		logger.addHandler(handler);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// TODO:
		logger.info("done testing");
	}

	/**
	 * Connect webClient to Jenkins Build page, if it successfully connects,
	 * verify that the page title is "Jenkins"
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOne() throws Exception {
		jenkinsURL = new URL("http://localhost:9090/login");
		logger.info(jenkinsURL.toString());

		// Get Jenkins login page
		page = webClient.getPage(jenkinsURL);
		logger.info("page title = " + page.getTitleText());
		Assert.assertEquals("Jenkins", page.getTitleText());

		// FreeStyleProject project = j.createFreeStyleProject();
		// project.getBuildersList().add(new Shell("echo hello"));
		// FreeStyleBuild build = project.scheduleBuild2(0).get();
		// System.out.println(build.getDisplayName() + " completed");
		// String s = FileUtils.readFileToString(build.getLogFile());
		// logger.info("s = " + s + " <------------------------ s");
		// Assert.assertThat("Contains string \"echo hello\"", s,
		// containsString("Legacy code"));
		// System.exit(0);
		// Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit",
		// page.getTitleText());

	}

	/**
	 * Test Jenkins Login
	 * 
	 * @throws IOException
	 */
	@Test
	public void testTwo() throws IOException {
		// Login
		form = page.getFormByName("login");
		form.getInputByName("j_username").setValueAttribute(userName);
		form.getInputByName("j_password").setValueAttribute(passWord);
		final HtmlElement createdElement = (HtmlElement) page.createElement("input");
		createdElement.setAttribute("type", "submit");
		createdElement.setAttribute("name", "submitIt");
		createdElement.setAttribute("onclick", "login.submit();");
		form.appendChild(createdElement);

		final HtmlElement submitButton = form.getInputByName("submitIt");
		page = submitButton.click();
		final HtmlElement loginField = page.getFirstByXPath("//a/@href");
		
		logger.info(loginField.toString());
		System.exit(0);
		
		if (loginField == null || !loginField.getTextContent().contains(fullName))
			throw new RuntimeException("Unable to log on to Jenkins. "); 
		System.out.println("Logged in! ");
		System.in.read();
	}

	/**
	 * Run test build (from Unit Test example on Jenkins page)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFreeStyleProjectBuild() throws Exception {
	}

	/**
	 * Simple test for multiple String declarations (can remove when test class
	 * is fully complete)
	 * 
	 * @throws FailingHttpStatusCodeException
	 * @throws IOException
	 */
	@Test
	public void testSetMultipleStrings() throws FailingHttpStatusCodeException, IOException {
		Assert.assertEquals("", defaultPropertyKey);
		Assert.assertEquals("", groovyScript);
		Assert.assertEquals("", groovyScriptFile);
		Assert.assertEquals("", bindings);
		Assert.assertEquals("", propertyKey);
		Assert.assertEquals("", defaultValue);
		Assert.assertEquals("", defaultPropertyFile);
		Assert.assertEquals("", defaultGroovyScript);
		Assert.assertEquals("", defaultGroovyScriptFile);
		Assert.assertEquals("", defaultBindings);
	}

	/**
	 * Test Set Environment Variables
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSetEnvironmentVariables() throws IOException {
		EnvironmentVariablesNodeProperty prop = new EnvironmentVariablesNodeProperty();
		EnvVars envVars = prop.getEnvVars();
		envVars.put("sampleEnvVarKey", "sampleEnvVarValue");
		j.jenkins.getGlobalNodeProperties().add(prop);
	}

	@Test
	public void testMultiLevelSelectOutput() {

	}

	/**
	 * Test store multiple values algorithm inside createValue method for
	 * successful expected output
	 */
	@Test
	public void testStoreMultipleValues() {

		// Expected result
		String expectedStrCols = "1:cfxunix,delinked,ALL:2:ifdev1,linked,aamqa:";

		// Initialize test variables
		Map<Integer, String> allCols = new HashMap<Integer, String>();
		allCols.put(1, "cfxunix");
		allCols.put(2, "delinked");
		allCols.put(3, "ALL");
		allCols.put(4, "ifdev1");
		allCols.put(5, "linked");
		allCols.put(6, "aamqa");
		logger.info("allCols: " + allCols);
		Integer multiSelectTotal = 6;
		Integer multiLevelColumns = 3;

		// Concatenate all multi-select values into one string
		StringBuilder strCols = new StringBuilder();
		for (int eachSelect = 1, col = 1, row = 1; eachSelect <= multiSelectTotal; eachSelect++, col++) {
			if (col == 1) { // 1st column
				strCols.append(row).append(":").append(allCols.get(eachSelect)).append(",");
			} else if ((eachSelect % multiLevelColumns) == 0) { // last column
				strCols.append(allCols.get(eachSelect)).append(":");
				col = 0;
				row++;
			} else { // columns in between
				strCols.append(allCols.get(eachSelect)).append(",");
			}
		}
		Assert.assertEquals(expectedStrCols, strCols.toString());
	}

	/**
	 * Test store multiple values algorithm inside createValue method for failed
	 * expected output
	 */
	@Test(expected = ComparisonFailure.class)
	public void testStoreMultipleValuesFail() {

		// Expected result
		String expectedStrCols = "1:cfxunix,delinked,ALL:2:ifdev1,linked,aamqa:";

		// Initialize test variables
		Map<Integer, String> allCols = new HashMap<Integer, String>();
		allCols.put(1, "cfxunix");
		allCols.put(2, "delinked");
		allCols.put(3, "ALL");
		allCols.put(4, "ifdev1");
		allCols.put(5, "linked");
		allCols.put(6, "aamqa");
		logger.info("allCols: " + allCols);
		Integer multiSelectTotal = 6;
		Integer multiLevelColumns = 2;

		// Concatenate all multi-select values into one string
		StringBuilder strCols = new StringBuilder();
		for (int eachSelect = 1, col = 1, row = 1; eachSelect <= multiSelectTotal; eachSelect++, col++) {
			if (col == 1) { // 1st column
				strCols.append(row).append(":").append(allCols.get(eachSelect)).append(",");
			} else if ((eachSelect % multiLevelColumns) == 0) { // last column
				strCols.append(allCols.get(eachSelect)).append(":");
				col = 0;
				row++;
			} else { // columns in between
				strCols.append(allCols.get(eachSelect)).append(",");
			}
		}
		Assert.assertEquals(expectedStrCols, strCols.toString());
	}

	/**
	 * Test method for
	 * {@link com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition#ExtendedChoiceParameterDefinition(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, int, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testNewInstance() {

		// fail("Not yet implemented");
	}

	@LocalData
	public void setLocalData() {

	}

	/**
	 * Test method for
	 * {@link com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition#createValue(org.kohsuke.stapler.StaplerRequest, net.sf.json.JSONObject)}
	 * .
	 */
	@Ignore("not ready yet")
	@Test
	public void testCreateValueStaplerRequestJSONObject() {
		Map<Integer, String> allCols = new HashMap<Integer, String>();
		allCols.put(1, "cfxunix");
		allCols.put(2, "delinked");
		allCols.put(3, "ALL");
		allCols.put(4, "ifdev1");
		allCols.put(5, "linked");
		allCols.put(6, "aamqa");
		logger.info("allCols: " + allCols);

		ExtendedChoiceParameterValue expectedObj = new ExtendedChoiceParameterValue("SDLC",
				"1:cfxunix,delinked,ALL:2:ifdev1,linked,aamqa:", 6, allCols);

		ParameterValue expectedObj1 = new StringParameterValue("QA", "");

		jsonObj.put("key", "value");
		// assertSame(expectedObj1, obj.createValue(staplerObj, jsonObj));
		logger.info("jsonObj: " + jsonObj.toString());
		Assert.assertEquals("{\"key\":\"value\"}", jsonObj.toString());
	}

	/**
	 * Test method for
	 * {@link hudson.model.ParameterDefinition#createValue(org.kohsuke.stapler.StaplerRequest, net.sf.json.JSONObject)}
	 * .
	 */
	@Ignore("not ready yet")
	@Test(expected = AssertionError.class)
	public void testCreateValueStaplerRequestJSONObject1() {
		obj.createValue(staplerObj, jsonObj);
	}

}
