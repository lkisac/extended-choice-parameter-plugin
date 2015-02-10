/**
 * This Test Class will use mock objects and Configure a new Jenkins Job 
 * to test multi-level values
 */
package com.cwctravel.hudson.plugins.extended_choice_parameter;

import static org.junit.Assert.*;

import java.io.IOException;

import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.FreeStyleBuild;
import hudson.model.AbstractBuild;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;
import hudson.util.OneShotEvent;

import org.apache.commons.io.FileUtils;
import org.easymock.EasyMock;
import org.junit.Rule;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.jvnet.hudson.test.HudsonTestCase;
//import org.mockito.Mock;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.TestBuilder;

/**
 * @author lkisac
 *
 */
public class ExtendedChoiceParameterDefinitionTestTwo {

	// mock creation
	// @Mock
	// private static ExtendedChoiceParameterDefinition mockedExtendedChoiceObj
	// = EasyMock.createMock(ExtendedChoiceParameterDefinition.class);

	// Rule
	@Rule
	public JenkinsRule j = new JenkinsRule();

	@Test
	public void first() throws Exception {
		FreeStyleProject project = j.createFreeStyleProject();
		project.getBuildersList().add(new Shell("echo hello"));
		FreeStyleBuild build = project.scheduleBuild2(0).get();
		System.out.println(build.getDisplayName() + " completed");
		// TODO: change this to use HtmlUnit
		//String s = FileUtils.readFileToString(build.getLogFile());
		//assertThat(s, contains("+ echo hello"));
	}

	@Test
	public void testCustomBuilder() throws IOException, InterruptedException {
		final OneShotEvent buildStarted = new OneShotEvent();

		FreeStyleProject project = j.createFreeStyleProject("Extended-Test-Custom");
		project.getBuildersList().add(new TestBuilder() {

			@Override
			public boolean perform(AbstractBuild<?, ?> build, Launcher launcher,
					BuildListener listener) throws InterruptedException, IOException {
				build.getWorkspace().child("abc.txt").write("hello", "UTF-8");
				return true;
			}
		});

		project.scheduleBuild2(0);
		buildStarted.block();

		// assertTrue(buildStarted.equals(true));
	}

	@Test
	public void testAdd() {
		String str = "test";
		assertEquals("test", str);
	}

	@Test
	public void testLogin() {

	}

	public void testConfigure() {
		// FreeStyleProject project = j.createFreeStyleProject();
		// FreeStyleProject p = j.createFreeStyleProject();
		// Builder before = new Builder("a", "b", true, 100);
		//
		// p.getBuildersList().add(before);
		//
		// j.submit(j.createWebClient().getPage(p,
		// "configure").getFormByName("config"));
		//
		// Builder after = p.getBuildersList().get(Builder.class);
		//
		// j.assertEqualBeans(before, after, "prop1,prop2,prop3,...");

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

}
