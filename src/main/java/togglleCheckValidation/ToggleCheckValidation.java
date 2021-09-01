package togglleCheckValidation;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToggleCheckValidation {
	// declaring a global variable
	WebDriver driver;

	@Before
	public void init() {
		// getting the webdriver
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://techfios.com/test/101/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	 //To Validate when the toggle all check box is CHECKED,
	// all check boxes for list items are also CHECKED ON.

	@Test
	public void toggleClickValidation() {
		WebElement Toggle_Check_Element;
		Toggle_Check_Element = driver.findElement(By.name("allbox"));
		Toggle_Check_Element.click();
		WebElement Toggle_CAR_MODEL_Element;
		Toggle_CAR_MODEL_Element = driver.findElement(By.name("todo[8]"));

		// Validate when the toggle
		// all check box is CHECKED, all check boxes for list items are
		// also CHECKED ON
		Assert.assertEquals(true, Toggle_CAR_MODEL_Element.isSelected());
		System.out.println("car model toggle button is selected – Assert passed");

	}
	
	
	// To Validate that a single list item could be removed 
	//using the remove button when a single checkbox is selected.
	@Test
	
	public void removeCheckedListItem() throws InterruptedException {
		
		// check list element and iterative action
		WebElement CHECKED_LIST_Element;
		CHECKED_LIST_Element = driver.findElement(By.name("todo[3]"));
		CHECKED_LIST_Element.click();
		
		// Revome button element and iterative action
		WebElement REMOVE_Element;
		REMOVE_Element = driver.findElement(By.cssSelector("input[name ='submit'][value = 'Remove']"));
		REMOVE_Element.click();
		
		// assertions to validated 
		 String expectedText = "people";
		String actualText = CHECKED_LIST_Element.getText();
		Assert.assertEquals(expectedText, actualText);
		System.out.println("todo[3] text is not expected – Assert passed");
		
	}

	// To Validate that all list items could be removed using the remove
	// button when "Toggle All" functionality is on
	@Test
	public void removeAllListItems() {

		// Toggle is checked or on
		WebElement Toggle_Check_Element;
		Toggle_Check_Element = driver.findElement(By.name("allbox"));
		Toggle_Check_Element.click();

		// All List Element should be checked and when i click Remove
		// button all list element will all be deleted
		WebElement REMOVE_Element;

		REMOVE_Element = driver.findElement(By.cssSelector("input[name ='submit'][value = 'Remove']"));
		REMOVE_Element.click();

		// assert that all list item element is not found(deleted)
		WebElement ALLLIST_Element;
		ALLLIST_Element = driver.findElement(By.cssSelector("ul[style ='list-style-type: none; padding-left:0']"));
		WebElement Toggle_CAR_MODEL_Element;
		Toggle_CAR_MODEL_Element = driver.findElement(By.name("todo[8]"));
		Assert.assertEquals(true, Toggle_CAR_MODEL_Element.isSelected());
		System.out.println("car model toggle button is selected – Assert passed");
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
