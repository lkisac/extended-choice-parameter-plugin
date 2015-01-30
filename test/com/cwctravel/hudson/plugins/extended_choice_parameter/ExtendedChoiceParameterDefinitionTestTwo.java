/**
 * This Test Class will use mock objects
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
