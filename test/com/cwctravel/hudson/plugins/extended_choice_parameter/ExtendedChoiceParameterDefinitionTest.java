/**
 * 
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import java.util.Map;

import hudson.model.*;
import hudson.tasks.Shell;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.kohsuke.stapler.StaplerRequest;
import org.jvnet.hudson.test.HudsonTestCase;
import org.jvnet.hudson.test.JenkinsRule;
import net.sf.json.JSONObject;

/**
 * @author lkisac
 *
 */
public class ExtendedChoiceParameterDefinitionTest extends HudsonTestCase {
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
	}

	@After
	public void tearDown() throws Exception {
		// TODO:
	}

	/**
	 * 
	 */
	@Test
	public void testSetMultipleStrings() {
		assertEquals("", this.defaultPropertyKey);
	}

//	public void testSetEnvironmentVariables() throws IOException {
//		EnvironmentVariablesNodeProperty prop = new EnvironmentVariablesNodeProperty();
//		EnvVars envVars = prop.getEnvVars();
//		envVars.put("sampleEnvVarKey", "sampleEnvVarValue");
//		j.jenkins.getGlobalNodeProperties().add(prop);
//	}
	
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

	/**
	 * Test method for
	 * {@link com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition#ExtendedChoiceParameterDefinition(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, int, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testNewInstance() {

		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition#createValue(org.kohsuke.stapler.StaplerRequest, net.sf.json.JSONObject)}
	 * .
	 */
	@Test
	public void testCreateValueStaplerRequestJSONObject() {
		final Map<Integer, String> allCols = null;
		ExtendedChoiceParameterValue expectedObj = new ExtendedChoiceParameterValue("SDLC", "{1:ifdev1a_test,linked,aaaqa:2:ifdev1,delinked,ALL:3:ifdev1,linked,aagqa:}", 9, allCols);
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
	@Test(expected = AssertionError.class)
	public void testCreateValueStaplerRequestJSONObject1() {
		obj.createValue(staplerObj, jsonObj);
	}

}
