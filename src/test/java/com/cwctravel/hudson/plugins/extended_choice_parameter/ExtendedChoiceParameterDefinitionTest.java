/*
 *Copyright (c) 2015 Len Isac
 *See the file license.txt for copying permission.
 */


package com.cwctravel.hudson.plugins.extended_choice_parameter;

import hudson.model.ParameterValue;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.kohsuke.stapler.StaplerRequest;

public class ExtendedChoiceParameterDefinitionTest {
	
	/**
	 * Check for concatenated value output from 
	 * Multi-level select list 2 levels (Country,City)
	 */
	@Test
	public void testCreateValueTwoLevels() {
		ExtendedChoiceParameterDefinition definitionObj = new ExtendedChoiceParameterDefinition("Country_2_Levels", "PT_MULTI_LEVEL_MULTI_SELECT", "Country,City", null, null, null, null, null, null, null, null, null, null, null, false, 0, null, null);
		StaplerRequest request = EasyMock.createMock(StaplerRequest.class);
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();

		jsonObj.put("name", "Country_2_Levels");
		jsonArr.add("United States");
		jsonArr.add("San Francisco");
		jsonArr.add("Mexico");
		jsonArr.add("Mexico City");
		jsonObj.put("value", jsonArr);
		
		//System.out.println("jsonArr: " + jsonArr.toString());
		
		ParameterValue strParamValue = EasyMock.createMock(ParameterValue.class);
		strParamValue = definitionObj.createValue(request, jsonObj);
		
		//System.out.println("strParamValue: " + strParamValue.getValue());
		Assert.assertEquals("1:United States,San Francisco:2:Mexico,Mexico City:", strParamValue.getValue().toString());
	}

	/**
	 * Multi-level select list 3 levels (Country,City,Hotel)
	 */
	@Test
	public void testCreateValueThreeLevels() {
		ExtendedChoiceParameterDefinition definitionObj = new ExtendedChoiceParameterDefinition("Country_3_Levels", "PT_MULTI_LEVEL_MULTI_SELECT", "Country,City,Hotel", null, null, null, null, null, null, null, null, null, null, null, false, 0, null, null);
		StaplerRequest request = EasyMock.createMock(StaplerRequest.class);
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		
		jsonObj.put("name", "Country_3_Levels");
		jsonArr.add("United States");
		jsonArr.add("San Francisco");
		jsonArr.add("Redwood Inn");
		jsonArr.add("Mexico");
		jsonArr.add("Mexico City");
		jsonArr.add("Gran Hotel");
		jsonObj.put("value", jsonArr);
		
		//System.out.println("jsonArr: " + jsonArr.toString());
		
		ParameterValue strParamValue = EasyMock.createMock(ParameterValue.class);
		strParamValue = definitionObj.createValue(request, jsonObj);
		
		//System.out.println("strParamValue: " + strParamValue.getValue());

		Assert.assertEquals("1:United States,San Francisco,Redwood Inn:2:Mexico,Mexico City,Gran Hotel:", strParamValue.getValue().toString());
	}
}
