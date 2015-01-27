///**
// * 
// */
//package test;
//
//import hudson.EnvVars;
//import hudson.slaves.EnvironmentVariablesNodeProperty;
//import java.io.IOException;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.jvnet.hudson.test.JenkinsRule;
//
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
//import com.gargoylesoftware.htmlunit.html.HtmlDivision;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//
//public class TestTwo {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Rule
//	public JenkinsRule j = new JenkinsRule();
//	
//	/**
//	 * 
//	 * @throws IOException
//	 */
//	public void setEnvironmentVariables() throws IOException {
//	    EnvironmentVariablesNodeProperty prop = new EnvironmentVariablesNodeProperty();
//	    EnvVars envVars = prop.getEnvVars();
//	    envVars.put("sampleEnvVarKey", "sampleEnvVarValue");
//	    j.jenkins.getGlobalNodeProperties().add(prop);
//	}
//	
//	/**
//	 * Example of finding a 'div' by an ID, and getting an anchor by name
//	 * @throws Exception
//	 */
//	@Test
//	public void getElements() throws Exception {
//	    final WebClient webClient = new WebClient();
//	    final HtmlPage page = webClient.getPage("http://www.google.ca");
//	    //final HtmlDivision div = page.getHtmlElementById("");
//	    //final HtmlAnchor anchor = page.getAnchorByName("anchor_name");
//
//	    webClient.closeAllWindows();
//	}
//}
