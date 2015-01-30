/**
 * This Test Class will use mock objects and Configure a new Jenkins Job 
 * to test multi-level values
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.mockito.Mock;

/**
 * @author lkisac
 *
 */
public class ExtendedChoiceParameterDefinitionTestTwo {

	// mock creation
	//@Mock
	private static ExtendedChoiceParameterDefinition mockedExtendedChoiceObj = EasyMock.createMock(ExtendedChoiceParameterDefinition.class);
	
	@Test
	public void testLogin() {
		
	}
	
	public void testConfigure() {
//		FreeStyleProject project = j.createFreeStyleProject();
//		FreeStyleProject p = j.createFreeStyleProject();
//		Builder before = new Builder("a", "b", true, 100);
//
//		p.getBuildersList().add(before);
//
//		j.submit(j.createWebClient().getPage(p, "configure").getFormByName("config"));
//
//		Builder after = p.getBuildersList().get(Builder.class);
//
//		j.assertEqualBeans(before, after, "prop1,prop2,prop3,...");

		// project.getBuildersList().add(new Shell("echo hello"));
		// FreeStyleBuild build = project.scheduleBuild2(0).get();
		// System.out.println(build.getDisplayName() + " completed");
		// String s = FileUtils.readFileToString(build.getLogFile());
		// logger.info("s = " + s + " <------------------------ s");
		// // Assert.assertThat("Contains string \"echo hello\"", s,
		// containsString("Legacy code"));
		// // System.exit(0);
		// Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit",
		// page.getTitleText());
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition#createValue(org.kohsuke.stapler.StaplerRequest, net.sf.json.JSONObject)}.
	 */
	@Test
	public void testCreateValueStaplerRequestJSONObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link hudson.model.ParameterDefinition#createValue(org.kohsuke.stapler.StaplerRequest, net.sf.json.JSONObject)}.
	 */
	@Test
	public void testCreateValueStaplerRequestJSONObject1() {
		fail("Not yet implemented");
	}

}
