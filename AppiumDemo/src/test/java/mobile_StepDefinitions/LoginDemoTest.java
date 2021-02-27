package mobile_StepDefinitions;

import org.testng.Assert;

import cucumber.api.java.en.*;
import mobile_PageObject.LoginDemo;

public class LoginDemoTest {

	   @When("^I enter username as \"([^\"]*)\"$")
	    public void iEnterUsernameAs(String username) throws InterruptedException {
	        new LoginDemo().enterUserName(username);
	    }

	    @When("^I enter password as \"([^\"]*)\"$")
	    public void iEnterPasswordAs(String password) {
	        new LoginDemo().enterPassword(password);
	    }

	    @When("^I login$")
	    public void iLogin() {
	        new LoginDemo().pressLoginBtn();
	    }
	    
	    @Then("^I should see Products page with title \"([^\"]*)\"$")
	    public void iShouldSeeProductsPageWithTitle(String title) {
	        Assert.assertEquals(new LoginDemo().getTitle(), title);
	    }
}
