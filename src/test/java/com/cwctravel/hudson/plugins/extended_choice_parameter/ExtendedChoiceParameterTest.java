/**
 * This Test will test an existing Extended-Test job with Extended Choice Parameter
 * multi-level select list to test storing multiple values
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import hudson.model.FreeStyleBuild;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;

import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.mockito.Mockito;

/**
 * @author Len Isac
 *
 */
public class ExtendedChoiceParameterTest {
	@Rule
	public JenkinsRule j = new JenkinsRule();
	
	@Test
	public void testExtendedChoiceParameterCreateValue() throws IOException, InterruptedException, ExecutionException {
		FreeStyleProject project = j.createFreeStyleProject();
		project.setDisplayName("Extended-Test");
		project.getBuildersList().add(new Shell("echo test"));
		
		FreeStyleBuild build = project.scheduleBuild2(0).get();
		System.out.println(build.getDisplayName() + " completed");
		System.out.println("build variables: " + build.getBuildVariables());	
	}
	
	@Test
	public void mockTest() {
		AbstractBuild<?, ?> build = Mockito.mock(AbstractBuild.class);
		Mockito.when(build.getResult()).thenReturn(Result.FAILURE);
	}
}
