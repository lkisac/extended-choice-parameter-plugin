/**
 * This Test will test an existing Extended-Test job with Extended Choice Parameter
 * multi-level select list to test storing multiple values
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import java.io.IOException;

import hudson.model.FreeStyleProject;

import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

/**
 * @author Len Isac
 *
 */
public class ExtendedChoiceParameterTest {
	@Rule
	public JenkinsRule j = new JenkinsRule();
	
	@Test
	public void testExtendedChoiceParameterCreateValue() throws IOException {
		FreeStyleProject p = j.createFreeStyleProject();
		p.setDisplayName("Extended-Test");
	}
}
