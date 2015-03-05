package com.cwctravel.hudson.plugins.extended_choice_parameter;

import net.sf.json.JSONObject;

import org.easymock.EasyMock;
import org.kohsuke.stapler.StaplerRequest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ExtendedChoiceParameterMock {
	@Test(enabled = false)
	public void createValue() {				
		ExtendedChoiceParameterDefinition definitionObj = EasyMock.createMock(ExtendedChoiceParameterDefinition.class);
		StaplerRequest request = null;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("Country_2_Levels",
				"[\"United States\",\"San Francisco\",\"Street1\",\"Mexico\",\"Mexico City\",\"Street3\"]");
		
		ExtendedChoiceParameterValue valueObj = (ExtendedChoiceParameterValue) definitionObj.createValue(request, jsonObj);
		Assert.assertEquals("1:United States,San Francisco,Street1:2:Mexico,Mexico City,Street3:", valueObj.value);
	}
}
