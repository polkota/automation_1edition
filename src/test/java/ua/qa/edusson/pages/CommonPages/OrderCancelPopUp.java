package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.WebElement;

import static ua.qa.edusson.tests.tools.TestBase.app;

public class OrderCancelPopUp {


public static String popUp = "//div[@id='popup_customer_order_cancel']";
public static String reason = "//ul[@data-atest='atest_order_popup_cancel_elem_reason_case']";
public static String otherReason = "//ul[@data-atest='atest_order_popup_cancel_elem_reason_case']/label[@for='cancel_order_reason_7']";
public static String textfield = "//textarea[@data-atest='atest_order_popup_cancel_elem_reason_description']";
public static String apply = "//button[@data-atest='atest_order_popup_cancel_elem_apply_btn']";
public static String cancel = "//button[@data-atest='atest_order_popup_cancel_elem_cancel_btn']";



public void chooseReason(){
	app.getHelper().randomChoiceFromDropdown("//ul[@data-atest='atest_order_popup_cancel_elem_reason_case']/label[@for]");
		
	}
	
public void clickApply(){
	WebElement apply_button = app.getHelper().cyclicElementSearchByXpath(apply);
			apply_button.click();
}
public void clickCancel(){
	WebElement cancel_button = app.getHelper().cyclicElementSearchByXpath(cancel);
	cancel_button.click();
}


public void cancelOrder(String strreason){
	this.chooseReason();
	WebElement other_reason = app.getHelper().cyclicElementSearchByXpath(otherReason);
	if ( other_reason.isSelected()){
		app.getHelper().cyclicElementSearchByXpath(textfield).sendKeys(strreason);
		this.clickApply();
			}
				else {
this.clickApply();
}
}
}

