/**
 * 
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import hudson.model.*;
import hudson.tasks.Shell;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.kohsuke.stapler.StaplerRequest;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.JenkinsRule.WebClient;
import org.jvnet.hudson.test.recipes.LocalData;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import net.sf.json.JSONObject;

/**
 * @author lkisac
 *
 */
public class ExtendedChoiceParameterDefinitionTest {
	private String name = "SDLC";
	private String type = "PT_SINGLE_SELECT";
	private String value = "One,Two,Three,Four,Five";
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

		// Create web client
		// TODO: fix. (keep getting java.lang.NoSuchFieldError: FIREFOX_2)
		try {
			final WebClient webClient = j.new WebClient();
			webClient.getOptions().setTimeout(1000);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			// final WebClient webClient = j.createWebClient();
			final URL jenkinsURL = new URL("http://localhost:9090");
			final HtmlPage page = webClient.getPage(jenkinsURL);
			Assert.assertEquals("Dashboard [Jenkins]", page.getTitleText());
		} catch (NoSuchFieldError e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@After
	public void tearDown() throws Exception {
		// TODO:
	}

	@Test
	public void first() throws Exception {
		FreeStyleProject project = j.createFreeStyleProject();
		project.getBuildersList().add(new Shell("echo hello"));
		FreeStyleBuild build = project.scheduleBuild2(0).get();
		System.out.println(build.getDisplayName() + " completed");

		// TODO: change this to use HtmlUnit
		// String s = FileUtils.readFileToString(build.getLogFile());
		// assertThat(s, contains("+ echo hello"));
	}

	@Test
	public void testSetMultipleStrings() throws FailingHttpStatusCodeException, IOException {
		assertEquals("", this.defaultPropertyKey);
	}

/*	 public void testSetEnvironmentVariables() throws IOException {
	 EnvironmentVariablesNodeProperty prop = new
	 EnvironmentVariablesNodeProperty();
	 EnvVars envVars = prop.getEnvVars();
	 envVars.put("sampleEnvVarKey", "sampleEnvVarValue");
	 j.jenkins.getGlobalNodeProperties().add(prop);
	 }
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
		System.out.println(allCols);
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
		System.out.println(allCols);
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
		System.out.println(allCols);

		ExtendedChoiceParameterValue expectedObj = new ExtendedChoiceParameterValue("SDLC",
				"1:cfxunix,delinked,ALL:2:ifdev1,linked,aamqa:", 6, allCols);

		// TODO: use HTML Unit for jsonObj
		jsonObj.put("key", "value");
		assertSame(expectedObj, obj.createValue(staplerObj, jsonObj));
		System.out.println(jsonObj.toString());
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
