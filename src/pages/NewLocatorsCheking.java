package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import pages.CommonPages.UserAuthorizationPage;
import utils.Config;
import utils.Helper;

public class NewLocatorsCheking {
	//XPath ��� ���������� �������� �����������
		public static String login_link = "//a[@data-atest='atest_login_elem_popup_open']";
		public static String usre_Name_field = "//input[@data-atest='atest_login_form_email']";
		public static String continue_button = "//button[@data-atest='atest_login_form_submit']";
		public static String user_Password_field = "//input[@data-atest='atest_login_form_password']";
		public static String login_button = "//button[@data-atest='atest_login_form_submit']";
		// ����� �������������� ������
		public static String forgot_password_link = "//a[@data-atest='atest_forgot_pass_elem_popup_open']";
		public static String email_for_change_password_field = "//input[@data-atest='atest_forgot_pass_form_email']";
		public static String submit_button = "//button[@data-atest='atest_forgot_pass_form_submit']";
		public static String success_pass_change = "f";
		public static String error_pass_change = "//div[@class='errorText js_forgot_pass_error']";
		public static String errorMessage = "//div[@class='errorText']";
		
		@Before
		public void setUp() throws Exception {
			Helper.driverSetUp("http://edusson.com.test18/");
		}
		@After
		public void theEnd(){
				Helper.quit();
					
			}
		@Test
		public void auth(){
			this.logIn(Config.customer1, Config.password);
			Helper.sleep(1);
			if (Helper.driver.getTitle().equals("Edusson.com - My Orders")){
			
				System.out.println("Test passed");
			}
		else {
			System.out.println("Title does not match");
		}
		}
		// ������ ������ ����� �� ������� - ��������� ����� �����������
		public void LogClick(){
			WebElement openAuthorizationPopUp = Helper.cyclicElementSearchByXpath(login_link);
			openAuthorizationPopUp.click();
		}
	
		// ����� ����� ���������
		public void logIn(String strUserName, String strPassword) {
			
			this.LogClick();
			Helper.sleep(1);
			this.setUserName(strUserName);
			this.continueClick();
			this.setPassword(strPassword);
			this.clickLoginButton();
		}

		

		// ���� ������ � ���� ����� �����������
		public void setUserName(String strUserName) {
			WebElement userEmail = Helper.cyclicElementSearchByXpath(usre_Name_field);
			userEmail.click();
			userEmail.sendKeys(strUserName);
		}
	public void continueClick(){
		WebElement continueButton = Helper.cyclicElementSearchByXpath(continue_button);
		continueButton.click();
	}
		// ���� ������ � ���� ����� �����������
		public void setPassword(String strPassword) {
			WebElement userPassword = Helper.cyclicElementSearchByXpath(user_Password_field);
			userPassword.click();
			userPassword.sendKeys(strPassword);
		}

		// ������ ������ ����� � ����� �����������
		public void clickLoginButton() {
			WebElement submit = Helper.cyclicElementSearchByXpath(login_button);
			submit.click();
		}
		
		
		
		// Forgot Password methods
			// ������� �� ����� �������������� ������
			public void clickForgotPasswordlink() {
				WebElement forgotPassword = Helper.cyclicElementSearchByXpath(forgot_password_link);
				forgotPassword.click();
			}

			
			public void clickForgotPasswordSubmit() {
				WebElement submit = Helper.cyclicElementSearchByXpath(submit_button);
				submit.click();
				
			}

			// �������� ����������� ��������� ��� ������ ���� �����
			public void assertErrorForgotPassword() {
				WebElement errorText = Helper.cyclicElementSearchByXpath(error_pass_change);
				errorText.getText()
						.contains("This is an obligatory field.");
			}

			// ���� ������
			public void setEmail(String strEmail) {
				WebElement email = Helper.cyclicElementSearchByXpath(email_for_change_password_field);
				email.sendKeys(strEmail);
			}

			// �������� ��������� ��� �������� �������� ����� forgot password
			public void assertSuccessPasswordChange() {
				WebElement successMessage = Helper.cyclicElementSearchByXpath(success_pass_change);
				successMessage.getText()
						.contains("We have just sent temporary password to your email.Use these details to login.");
			}

			
			
			
			// �������� �������������� ������
			public void forgotPasswordSuccess(String strEmail) {
				this.LogClick();
				Helper.sleep(1);
				this.clickForgotPasswordlink();
				this.setEmail(strEmail);
				this.assertSuccessPasswordChange();

			}

			// �������� ����� � ������ �����
			public void forgotPassNoEmail(String strEmail) {
				this.LogClick();
				Helper.sleep(1);
				this.clickForgotPasswordlink();
				this.setEmail(strEmail);
				this.clickForgotPasswordSubmit();
				this.assertErrorForgotPassword();
				}
			
		

		// ����� ��������� �� ������ ��� ����� ���������� ������
		public boolean checkErrorMessagePresent() {
			
			try {
				Helper.cyclicElementSearchByXpath(errorMessage);
				return true;
			} catch (ElementNotVisibleException ex) {
				return false;
			}
		}

		

		
}
