package test;

import hudson.model.*;
import hudson.tasks.Shell;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jvnet.hudson.test.JenkinsRule;
import org.apache.commons.io.FileUtils;

public class TestOne {

	@Test
	public void homePage() throws Exception {
		final WebClient webClient = new WebClient();
		final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
		Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

		final String pageAsXml = page.asXml();
		Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

		final String pageAsText = page.asText();
		Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));

		webClient.closeAllWindows();
	}

	@Rule
	public JenkinsRule j = new JenkinsRule();

	@Test
	public void first() throws Exception {
		FreeStyleProject project = j.createFreeStyleProject();
		project.getBuildersList().add(new Shell("echo hello"));
		FreeStyleBuild build = project.scheduleBuild2(0).get();
		System.out.println(build.getDisplayName() + " completed");
		// TODO: change this to use HtmlUnit
//		String s = FileUtils.readFileToString(build.getLogFile());
//		assertThat(s, containsString("+ echo hello"));
	}

}