package com.cwctravel.hudson.plugins.extended_choice_parameter;

import hudson.model.ParameterValue;
import hudson.model.StringParameterValue;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.easymock.EasyMock;
import org.junit.Assert;
//import org.easymock.EasyMock;
import org.junit.Test;
//import org.junit.runner.RunWith;
import org.kohsuke.stapler.StaplerRequest;
//import org.powermock.api.mockito.PowerMockito;
//import org.testng.annotations.Test;
//import org.testng.Assert;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest( { ExtendedChoiceParameterDefinition.class } )
public class ExtendedChoiceParameterMock {
	
	@Test
	public void testCreateValueTwoLevels() {
		//ExtendedChoiceParameterDefinition definitionObj = EasyMock.createMock(ExtendedChoiceParameterDefinition.class);
		//ExtendedChoiceParameterDefinition mock = PowerMockito.mock(ExtendedChoiceParameterDefinition.class);
		ExtendedChoiceParameterDefinition definitionObj = new ExtendedChoiceParameterDefinition("Country_2_Levels", "PT_MULTI_LEVEL_MULTI_SELECT", "Country,City", null, null, null, null, null, null, null, null, null, null, null, false, 0, null, null);
		StaplerRequest request = null;
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		jsonObj.put("name", "Country_2_Levels");
		//jsonObj.put("value", "[\"United States\",\"San Francisco\",\"Street1\",\"Mexico\",\"Mexico City\",\"Street3\"]");
		//EasyMock.expect( definitionObj.createValue(request, jsonObj)).andReturn( null );
		//System.out.println("name: " + definitionObj.getName());
		//definitionObj.setType("PT_MULTI_LEVEL_MULTI_SELECT");
		//System.out.println("type: " + definitionObj.getType());
		jsonArr.add("United States");
		jsonArr.add("San Francisco");
		jsonArr.add("Mexico");
		jsonArr.add("Mexico City");
		jsonObj.put("value", jsonArr);
		//System.out.println("jsonObj: " + jsonObj.get("value").toString());
		System.out.println("jsonArr: " + jsonArr.toString());
		//Object valueObj = definitionObj.createValue(request, jsonObj);
		
		ParameterValue strParamValue = EasyMock.createMock(ParameterValue.class);
		//strParamValue = definitionObj.createValue(request, jsonObj);
		strParamValue = definitionObj.createValue(request, jsonObj);
		
		//System.out.println("valueObj: " + valueObj.toString());
		System.out.println("strParamValue: " + strParamValue.getValue());

//		ExtendedChoiceParameterValue valueObj = (ExtendedChoiceParameterValue) definitionObj.createValue(request, jsonObj);
		Assert.assertEquals("1:United States,San Francisco:2:Mexico,Mexico City:", strParamValue.getValue().toString());
	}

	
	@Test
	public void testCreateValueThreeLevels() {
		//ExtendedChoiceParameterDefinition definitionObj = EasyMock.createMock(ExtendedChoiceParameterDefinition.class);
		//ExtendedChoiceParameterDefinition mock = PowerMockito.mock(ExtendedChoiceParameterDefinition.class);
		ExtendedChoiceParameterDefinition definitionObj = new ExtendedChoiceParameterDefinition("Country_3_Levels", "PT_MULTI_LEVEL_MULTI_SELECT", "Country,City,Street", null, null, null, null, null, null, null, null, null, null, null, false, 0, null, null);
		StaplerRequest request = null;
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		jsonObj.put("name", "Country_2_Levels");
		//jsonObj.put("value", "[\"United States\",\"San Francisco\",\"Street1\",\"Mexico\",\"Mexico City\",\"Street3\"]");
		//EasyMock.expect( definitionObj.createValue(request, jsonObj)).andReturn( null );
		//System.out.println("name: " + definitionObj.getName());
		//definitionObj.setType("PT_MULTI_LEVEL_MULTI_SELECT");
		//System.out.println("type: " + definitionObj.getType());
		jsonArr.add("United States");
		jsonArr.add("San Francisco");
		jsonArr.add("Street1");
		jsonArr.add("Mexico");
		jsonArr.add("Mexico City");
		jsonArr.add("Street3");
		jsonObj.put("value", jsonArr);
		//System.out.println("jsonObj: " + jsonObj.get("value").toString());
		System.out.println("jsonArr: " + jsonArr.toString());
		//Object valueObj = definitionObj.createValue(request, jsonObj);
		
		ParameterValue strParamValue = EasyMock.createMock(ParameterValue.class);
		//strParamValue = definitionObj.createValue(request, jsonObj);
		strParamValue = definitionObj.createValue(request, jsonObj);
		
		//System.out.println("valueObj: " + valueObj.toString());
		System.out.println("strParamValue: " + strParamValue.getValue());

//		ExtendedChoiceParameterValue valueObj = (ExtendedChoiceParameterValue) definitionObj.createValue(request, jsonObj);
		Assert.assertEquals("1:United States,San Francisco,Street1:2:Mexico,Mexico City,Street3:", strParamValue.getValue().toString());
	}
}
