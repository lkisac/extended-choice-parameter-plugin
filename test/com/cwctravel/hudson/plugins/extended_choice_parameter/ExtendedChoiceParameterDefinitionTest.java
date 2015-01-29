/**
 * 
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

import hudson.model.*;
import hudson.tasks.Shell;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.kohsuke.stapler.StaplerRequest;
import org.mockito.Mock;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.containsString;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import net.sf.json.JSONObject;

/**
 * @author lkisac
 *
 */
public class ExtendedChoiceParameterDefinitionTest {
	private String name = "QA";
	private String type = "PT_MULTI_LEVEL_MULTI_SELECT";
	private String value = "{1:cfxunix,delinked,ALL:2:ifdev1,linked,aamqa:}";
	private String propertyFile,
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
	private boolean quoteValue = false;
	private Integer visibleItemCount = 5;
	private String description = "";
	private String multiSelectDelimiter = ",";
	ExtendedChoiceParameterDefinition obj;
	StaplerRequest staplerObj;
	JSONObject jsonObj = new JSONObject();
	final WebClient webClient = new WebClient();
	
	// logger
	Logger logger;
	ConsoleHandler handler;

	// mock creation
	@Mock
	ExtendedChoiceParameterDefinition mockedExtendedChoiceObj;
	// ExtendedChoiceParameterDefinition mockedExtendedChoiceObj = mock(ExtendedChoiceParameterDefinition.class);
	// StaplerRequest mockedStaplerObj = mock(StaplerRequest.class);
	// JSONObject mockJsonObj = mock(JSONObject.class);

	// Rule
	@Rule
	public JenkinsRule j = new JenkinsRule();

	/**
	 * Initialize new ExtendedChoiceParameterDefinition object to test methods
	 * with
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// ExtendedChoiceParameterDefinition object
		this.obj = new ExtendedChoiceParameterDefinition(this.name, this.type, this.value,
				this.propertyFile, this.groovyScript, this.groovyScriptFile, this.bindings,
				this.propertyKey, this.defaultValue, this.defaultPropertyFile,
				this.defaultGroovyScript, this.defaultGroovyScriptFile, this.defaultBindings,
				this.defaultPropertyKey, this.quoteValue, this.visibleItemCount, this.description,
				this.multiSelectDelimiter);
		// System.out.println(this.obj);

		this.logger = Logger.getLogger(ExtendedChoiceParameterDefinitionTest.class.getName());
		this.handler = new ConsoleHandler();
		this.logger.addHandler(handler);
	}

	@After
	public void tearDown() throws Exception {
		// TODO:
	}

	@Test
	public void testFirst() throws Exception {
		final URL jenkinsURL = new URL("http://localhost:9090/job/Dynamic-Extended-Test/build?delay=0sec");
		HtmlPage page;
		this.logger.info(jenkinsURL.toString());
		try {
			page = webClient.getPage(jenkinsURL);
			this.logger.info("page title = " + page.getTitleText());
			Assert.assertEquals("Jenkins", page.getTitleText());
		}
		catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
			//System.exit(1);
		}
		
		FreeStyleProject project = j.createFreeStyleProject();
		project.getBuildersList().add(new Shell("echo hello"));
		FreeStyleBuild build = project.scheduleBuild2(0).get();
		System.out.println(build.getDisplayName() + " completed");
		String s = FileUtils.readFileToString(build.getLogFile());
		this.logger.info("s = " + s + " <------------------------ s");
		//Assert.assertThat("Contains string \"echo hello\"", s, containsString("Legacy code"));
		//System.exit(0);
	}

	@Test
	public void testSetMultipleStrings() throws FailingHttpStatusCodeException, IOException {
		assertEquals("", this.defaultPropertyKey);
	}

	/*
	 * public void testSetEnvironmentVariables() throws IOException {
	 * EnvironmentVariablesNodeProperty prop = new
	 * EnvironmentVariablesNodeProperty(); EnvVars envVars = prop.getEnvVars();
	 * envVars.put("sampleEnvVarKey", "sampleEnvVarValue");
	 * j.jenkins.getGlobalNodeProperties().add(prop); }
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
		this.logger.info("allCols: " + allCols);
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
		assertEquals(expectedStrCols, strCols.toString());
	}

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
		this.logger.info("allCols: " + allCols);
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
		assertEquals(expectedStrCols, strCols.toString());
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
	// @Ignore("not ready yet")
	@Test
	public void testCreateValueStaplerRequestJSONObject() {
		Map<Integer, String> allCols = new HashMap<Integer, String>();
		allCols.put(1, "cfxunix");
		allCols.put(2, "delinked");
		allCols.put(3, "ALL");
		allCols.put(4, "ifdev1");
		allCols.put(5, "linked");
		allCols.put(6, "aamqa");
		this.logger.info("allCols: " + allCols);

		ExtendedChoiceParameterValue expectedObj = new ExtendedChoiceParameterValue("SDLC",
				"1:cfxunix,delinked,ALL:2:ifdev1,linked,aamqa:", 6, allCols);

		ParameterValue expectedObj1 = new StringParameterValue("QA", "");

		jsonObj.put("key", "value");
		// assertSame(expectedObj1, obj.createValue(staplerObj, jsonObj));
		this.logger.info("jsonObj: " + jsonObj.toString());
		assertEquals("{\"key\":\"value\"}", jsonObj.toString());
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
