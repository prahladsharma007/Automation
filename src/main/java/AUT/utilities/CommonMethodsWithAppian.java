//package AUT.utilities;
//
//import AUT.constants.CommonConstants;
//import AUT.listeners.ReportListeners;
//import com.appiancorp.ps.automatedtest.fixture.SitesFixture;
//import org.apache.commons.imaging.Imaging;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.NoSuchElementException;
//import java.util.*;
//
//public class CommonMethodsWithAppian {
//
//	public WebDriver driver;
//	//public SitesFixture fixture;
//
//	private static String screenshotFolderPath = createScreenshotsFolder();
//
//	public CommonMethodsWithAppian(WebDriver driver, SitesFixture fixture) {
//		this.driver = driver;
//		this.fixture = fixture;
//	}
//
//	public void capturePageScreenshot(String methodName) {
//
//		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String threadId = String.valueOf(Thread.currentThread().getId());
//		File destination = new File(screenshotFolderPath + "/Screenshot-" + methodName + "-" + threadId + "-" + CommonConstants.getDateTimeStamp() + ".png");
//		try {
//			FileUtils.copyFile(source, destination);
//			System.out.println("Screenshot copied to " + screenshotFolderPath);
//		} catch (IOException e) {
//			System.out.println("Unable to copy screenshot file to '" + screenshotFolderPath + "'. Error occured :" + e);
//		}
//	}
//
//	public static String createScreenshotsFolder() {
//
//		File screenshotFolder = new File(CommonConstants.getScreenshotFilePath(), "screenshots_" + CommonConstants.getDateTimeStamp());
//
//		if (!screenshotFolder.exists()) {
//			screenshotFolder.mkdir();
//		}
//
//		return screenshotFolder.getAbsolutePath();
//	}
//
//	public String getTimeStamp() {
//		DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
//		Date currentDate = new Date();
//
//		return outputFormat.format(currentDate);
//	}
//
//	public void scroll_to_element(WebElement ele) {
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
//		common.waitForSeconds(2);
//	}
//
//	public void scrollTillTop() {
//		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
//		common.waitForSeconds(3);
//	}
//
//	public void scrollTillBottom() {
//		((JavascriptExecutor) driver)
//				.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//		common.waitForSeconds(3);
//	}
//
//	public void clickOnButtonWithTooltip(String buttonTooltip) {
//		try {
//			fixture.verifyButtonIsEnabled(buttonTooltip);
//			fixture.clickOnButtonWithTooltip(buttonTooltip);
//			common.waitForProgressBar();
//			ReportListeners.logStep("info", "Clicked on Button: " + buttonTooltip);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Button with identifier '" + buttonTooltip + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while clicking on the button '" + buttonTooltip + "' : " + e);
//			throw e;
//		}
//	}
//
//	public void clickOnButton(String strButton) {
//		try {
//			fixture.verifyButtonIsEnabled(strButton);
//			fixture.clickOnButton(strButton);
//			common.waitForProgressBar();
//			common.waitForSeconds(2);
//			ReportListeners.logStep("info", "Clicked on Button: " + strButton);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Button with identifier '" + strButton + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while clicking on the button '" + strButton + "' : " + e);
//			throw e;
//		}
//	}
//
//	/*
//	Note: New version of appian has clickDialogButton method
//	 */
//	public void clickOnDialogButton(String buttonName) {
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			WebElement dialogButton = wait.until(ExpectedConditions.elementToBeClickable(
//					By.xpath("//div[@role='dialog']//button[.//span[normalize-space()='" + buttonName + "']]")
//			));
//			dialogButton.click();
//			ReportListeners.logStep("info", "Successfully clicked the dialog button: '" + buttonName + "'");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to click dialog button: '" + buttonName + "'. Exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
//			throw new RuntimeException("Could not click dialog button: '" + buttonName + "'", e);
//		}
//	}
//
//	public void clickOnCard(String strCardLink) {
//		try {
//			fixture.clickOnCard(strCardLink);
//			ReportListeners.logStep("info", "Clicked on Card: " + strCardLink);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Card with identifier '" + strCardLink + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while clicking on the card '" + strCardLink + "' : " + e);
//			throw e;
//		}
//	}
//
//	public void clickOnMenu(String strMenu) {
//		try {
//			fixture.clickOnMenu(strMenu);
//			ReportListeners.logStep("info", "Clicked on Menu: " + strMenu);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Menu with identifier '" + strMenu + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while clicking on the Menu '" + strMenu + "' : " + e);
//			throw e;
//		}
//	}
//
//	public void populateFieldNameWithValue(String fieldName, String value) {
//		try {
//			fixture.populateFieldWithValue(fieldName, value);
//			common.waitForSeconds(3);
//			ReportListeners.logStep("info", "Populated '" + fieldName + "' with '" + value + "'");
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Field with identifier '" + fieldName + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while populating '" + fieldName + "' field: " + e);
//			throw e;
//		}
//	}
//
//	public String getSystemDate() {
//		Date currentDate = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		String currentDateTime = dateFormat.format(currentDate);
//		return currentDateTime;
//	}
//
//	public void selectAllFromKeyboard(WebElement ele) {
//		ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//	}
//
//	public void hitEnterFromKeyboard(WebElement ele) {
//		ele.sendKeys(Keys.RETURN);
//	}
//
//	public void populateFieldIndexWithValue(Integer index, String fieldName, String value) {
//		try {
//			fixture.populateFieldWithValue("[" + index + "]", value);
//			ReportListeners.logStep("info", "Populated *" + fieldName + "' with '" + value + "'");
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Field with identifier '" + fieldName + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while populating '" + fieldName + "' field: " + e);
//			throw e;
//		}
//	}
//
//	public void createFolder(String foldername) {
//		File theDir = new File(System.getProperty("user.dir") + "/" + foldername);
//		if (!theDir.exists()) {
//			theDir.mkdirs();
//		}
//	}
//
//	public Boolean verifyTextIsPresent(String text) {
//		boolean result = false;
//		try {
//			result = fixture.verifyTextIsPresent(text);
//			if (result) {
//				ReportListeners.logStep("pass", "Text Presence verified: " + text);
//			} else {
//				ReportListeners.logStep("fail", "Text is not Present " + text);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Text Presence failed for '" + text + "' : " + e);
//			throw e;
//		}
//		return result;
//	}
//
//	public void clickOnLinkText(String linkText) {
//		try {
//			fixture.clickOnLink(linkText);
//			ReportListeners.logStep("info", "Clicked on Link Text: " + linkText);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Link Text with identifier '" + linkText + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while clicking on the link text '" + linkText + "' : " + e);
//			throw e;
//		}
//	}
//
//	public void verifyAvailabilityOfText(String strText) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
//		String xpath = String.format("//*[text()='%s']", strText);
//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//		if (element.isDisplayed()) {
//			ReportListeners.logStep("pass", strText + " Text is available on Webpage ");
//		} else {
//			ReportListeners.logStep("fail", strText + " Text is Not available on Webpage ");
//			ReportListeners.logScreenshotStep(driver, strText);
//
//		}
//	}
//
//	public void verifyAvailabilityOfTextWithContains(String strText) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//		String xpath = String.format("//*[contains(text(),'%s')]", strText);
//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//		if (element.isDisplayed()) {
//			ReportListeners.logStep("pass", strText + " Text is available on Webpage ");
//		} else {
//			ReportListeners.logStep("fail", strText + " Text is Not available on Webpage ");
//			ReportListeners.logScreenshotStep(driver, strText);
//
//		}
//	}
//
//	public void clickOnText(String strText) {
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			String xpath = String.format("//*[text()='%s']", strText);
//			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//			element.click();
//			common.waitForSeconds(2);
//			ReportListeners.logStep("info", "Clicked on Text: " + strText);
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Clicked on Text *" + strText + "*: " + e);
//			throw e;
//		}
//	}
//
//	public WebElement getWebElementWithAttribute(String tagName, String attributeName, String strText) {
//		WebElement element = null;
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			String xpath = String.format("//%s[@%s='%s']", tagName, attributeName, strText);
//			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//			ReportListeners.logStep("info", "Clicked on Text: " + strText);
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Clicked on Text *" + strText + "*: " + e);
//			throw e;
//		}
//		return element;
//	}
//
//	public void clickOnTextByIndex(String strText, int index) {
//		try {
//			index = index - 1;
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			String xpath = String.format("//*[text()='%s']", strText);
//			List<WebElement> all = driver.findElements(By.xpath(xpath));
//			for (int i = 0; i < all.size(); i++) {
//				if (i == index) {
//					all.get(i).click();
//				}
//			}
//			ReportListeners.logStep("info", "Clicked on Text: " + strText);
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Clicked on Text *" + strText + "*: " + e);
//			throw e;
//		}
//	}
//
//	public int elementCount(String strText) {
//		try {
//			String xpath = String.format("//*[text()='%s']", strText);
//			List<WebElement> all = driver.findElements(By.xpath(xpath));
//			ReportListeners.logStep("info", "Total Text on screen" + strText + "is:  " + all.size());
//			return all.size();
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Clicked on Text *" + strText + "*: " + e);
//			throw e;
//		}
//	}
//
//	public String getFieldNameValue(String fieldName) {
//		String value = null;
//		try {
//			value = fixture.getFieldValue(fieldName);
//			ReportListeners.logStep("info", "Fetched *" + fieldName + "* value: " + value);
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to get value for field *" + fieldName + "*: " + e);
//			throw e;
//		}
//		return value;
//	}
//
//	public String getFieldIndexValue(Integer index, String fieldName) {
//		String value = null;
//		try {
//			value = fixture.getFieldValue("[" + index + "]");
//			ReportListeners.logStep("info", "Fetched *" + fieldName + "* value: " + value);
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to get value for field *" + fieldName + "*: " + e);
//			throw e;
//		}
//		return value;
//	}
//
//	public void logout() {
//		System.out.println("Starting Log out process");
//		try {
//			fixture.refresh();
//			if (isAlertPresent()) {
//				acceptAlert();
//			}
//			common.waitForProgressBar();
//			fixture.logout();
//		} catch (Exception e) {
//			System.out.println("Error while logging out: " + e);
//			throw e;
//		}
//	}
//
//	public Set<String> getTotalWindows() {
//		Set<String> allWindows = driver.getWindowHandles();
//		return allWindows;
//	}
//
//	public String getCurrentWindow() {
//		return driver.getWindowHandle();
//	}
//
//	public void switchToChildWindow(Set<String> totalWindows, String parent) {
//		Iterator<String> I1 = totalWindows.iterator();
//		while (I1.hasNext()) {
//			String child_window = I1.next();
//			if (!parent.equals(child_window)) {
//				driver.switchTo().window(child_window);
//				System.out.println(driver.switchTo().window(child_window).getTitle());
//			}
//		}
//	}
//
//	public void switchToMainWindow(String windowName) {
//		driver.switchTo().window(windowName);
//	}
//
//	public void populateFilterWithValue(String fieldName, String value) {
//		try {
//			fixture.populateRecordTypeUserFilterWith(fieldName, value);
//			common.waitForSeconds(1);
//			ReportListeners.logStep("info", "Populated filter '" + fieldName + "' with '" + value + "'");
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Filter with identifier '" + fieldName + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while populating '" + fieldName + "' filter: " + e);
//			throw e;
//		}
//	}
//
//	public String getMethodName() {
//		return Thread.currentThread().getStackTrace()[2].getMethodName();
//	}
//
//	public void clickOnSitePage(String siteName) {
//		try {
//			fixture.clickOnSitePage(siteName);
//			ReportListeners.logStep("info", "Clicked on site page: '" + siteName + "'");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Couldn't click on site page: '" + siteName + "' " + e);
//			throw e;
//		}
//	}
//
//	public boolean isElementDisplayed(By locator) {
//		try {
//			WebElement element = driver.findElement(locator);
//			return element.isDisplayed();
//		} catch (Exception e) {
//			System.out.println("No element present");
//			return false;
//		}
//	}
//
//	public void clickOnCheckboxOption(String strCheckboxOption) {
//		try {
//			fixture.clickOnCheckboxOption(strCheckboxOption);
//			common.waitForProgressBar();
//			ReportListeners.logStep("info", "Clicked on Checkbox: " + strCheckboxOption);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Checkbox with identifier '" + strCheckboxOption + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while clicking on the checkbox '" + strCheckboxOption + "' : " + e);
//			throw e;
//		}
//	}
//
//	public BufferedImage readImageWithApacheCommons(String path) {
//		try {
//			File file = new File(path);
//			return Imaging.getBufferedImage(file);
//		} catch (Exception e) {
//			System.out.println("Error: " + e);
//			return null;
//		}
//	}
//
//	public boolean compareImages(BufferedImage img1, BufferedImage img2) {
//		if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
//			return false;
//		}
//
//		for (int y = 0; y < img1.getHeight(); y++) {
//			for (int x = 0; x < img1.getWidth(); x++) {
//				if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	public Alert getAlert() {
//		try {
//			System.out.println("Switching to alert.");
//			ReportListeners.logStep("pass", "Switching to alert.");
//			return driver.switchTo().alert();
//		} catch (NoAlertPresentException e) {
//			ReportListeners.logStep("fail", "Unable to switch to alert. Exception occured : " + e);
//			System.out.println("Unable to switch to alert. Exception occured : " + e);
//			return null;
//		}
//	}
//
//	public boolean isAlertPresent() {
//		try {
//			//ReportListeners.logStep("pass",  "Alert is present.");
//			System.out.println("Alert is present.");
//			return (driver.switchTo().alert() != null);
//		} catch (NoAlertPresentException e) {
//			//ReportListeners.logStep("fail", "Alert not found. Exception occured : " + e);
//			System.out.println("Alert not found. Exception occured : " + e);
//			return false;
//		}
//	}
//
//	public void switchToChildWindowNotAvailableInArrayList(Set<String> totalWindows, ArrayList<String> windows) {
//		for (String child_window : totalWindows) {
//			if (!windows.contains(child_window)) {
//				driver.switchTo().window(child_window);
//				System.out.println(driver.switchTo().window(child_window).getTitle());
//			}
//		}
//	}
//
//	public void closeAllChildWindow(Set<String> totalWindows, String parent) {
//		for (String child_window : totalWindows) {
//			if (!parent.equals(child_window)) {
//				driver.switchTo().window(child_window);
//				driver.close();
//			}
//		}
//	}
//
//	public String getAlertText() {
//		try {
//			driver.switchTo().alert().getText();
//			ReportListeners.logStep("pass", "Text on the Alert popup is : " + driver.switchTo().alert().getText());
//			System.out.println("Text on the Alert popup is : " + driver.switchTo().alert().getText());
//			return driver.switchTo().alert().getText();
//		} catch (NoAlertPresentException e) {
//			ReportListeners.logStep("fail", "Unable to get Alert text. Exception occured : " + e);
//			System.out.println("Unable to get Alert text. Exception occured : " + e);
//			return "";
//		}
//	}
//
//	public void acceptAlert() {
//		try {
//			driver.switchTo().alert().accept();
//			ReportListeners.logStep("pass", "Alert accepted.");
//			System.out.println("Alert accepted.");
//		} catch (NoAlertPresentException e) {
//			ReportListeners.logStep("fail", "Unable to accept Alert. Exception occured : " + e);
//			System.out.println("Unable to accept Alert. Exception occured : " + e);
//		}
//	}
//
//	public void populateDropdownSearchBoxWith(String dropdownName, String value) {
//		try {
//			fixture.populateDropdownSearchBoxWith(dropdownName, value);
//			ReportListeners.logStep("pass", "Value:" + value + "Selected in Dropdown");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Value:" + value + "Not Selected in Dropdown" + e);
//			throw e;
//		}
//	}
//
//	public void wait_for_appearance_of_xpath(String xpathExpression, int timeOutInSeconds) {
//		try {
//			Duration timeoutDuration = Duration.ofSeconds(timeOutInSeconds);
//			WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
//		} catch (Exception e) {
//			ReportListeners.logStep("info", "Required element not found on screen");
//		}
//	}
//
//	public void wait_for_invisibility_of_xpath(String xpathExpression, int timeOutInSeconds) {
//		try {
//			Duration timeoutDuration = Duration.ofSeconds(timeOutInSeconds);
//			WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathExpression)));
//		} catch (Exception e) {
//			ReportListeners.logStep("info", "Required element found on screen");
//		}
//	}
//
//	public void wait_for_appearance_of_css(String className, int timeOutInSeconds) {
//		Duration timeoutDuration = Duration.ofSeconds(timeOutInSeconds);
//		WebDriverWait wait;
//		wait = new WebDriverWait(driver, timeoutDuration);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(className)));
//	}
//
//	public Boolean verifyTextIsNotPresent(String text) {
//		boolean result = false;
//		try {
//			result = fixture.verifyTextIsNotPresent(text);
//			if (result) {
//				ReportListeners.logStep("pass", "Text is not Present: " + text);
//			} else {
//				ReportListeners.logStep("fail", "Text Presence available for *" + text + "*");
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Text Presence available for *" + text + "* : " + e);
//			throw e;
//		}
//		return result;
//	}
//
//	public String getAttribute(WebElement element, String attributeName) {
//		String name = element.getAttribute(attributeName);
//		return name;
//	}
//
//	public Boolean verifyLinkIsPresent(String text) {
//		boolean result = false;
//		try {
//			result = fixture.verifyLinkIsPresent(text);
//			if (result) {
//				ReportListeners.logStep("pass", "Link is Present verified: " + text);
//			} else {
//				ReportListeners.logStep("fail", "Link is not available on screen for " + text);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Link is not available on screen for *" + text + "* : " + e);
//			throw e;
//		}
//
//		return result;
//	}
//
//	public void wait_for_element_to_be_clickable(WebElement element, int timeOutInSeconds) {
//
//		Duration timeoutDuration = Duration.ofSeconds(timeOutInSeconds);
//		WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
//		wait.until(ExpectedConditions.elementToBeClickable(element));
//
//	}
//
//	public void populateFieldWithPlaceholderWith(String strPlaceHolderValue, String[] value) {
//		try {
//			fixture.populateFieldWithPlaceholderWith(strPlaceHolderValue, value);
//			common.waitForProgressBar();
//			ReportListeners.logStep("info", "Value Entered: " + value[0]);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Value Not Entered'" + value[0] + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while Entering Value" + value[0] + e);
//			throw e;
//		}
//	}
//
//	public void populateFieldWithContains(String fieldName, String[] value) {
//		try {
//			fixture.populateFieldWithContains(fieldName, value);
//			ReportListeners.logStep("info", "Value Entered: " + value[0]);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Value Not Entered'" + value[0] + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while Entering Value" + value[0] + e);
//			throw e;
//		}
//	}
//
//	public Boolean verifyButtonIsPresent(String text) {
//		boolean result = false;
//		try {
//			result = fixture.verifyButtonIsPresent(text);
//			if (result) {
//				ReportListeners.logStep("pass", "Button Presence verified: " + text);
//			} else {
//				ReportListeners.logStep("fail", "Button Presence verified, its not present: " + text);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Button Presence failed for *" + text + "* : " + e);
//			throw e;
//		}
//		return result;
//	}
//
//	public Boolean verifyButtonIsNotPresent(String text) {
//		boolean result = false;
//		try {
//			result = fixture.verifyButtonIsNotPresent(text);
//			if (result) {
//				ReportListeners.logStep("pass", "Button is not Present verified: " + text);
//			} else {
//				ReportListeners.logStep("fail", "Button Presence available on screen for " + text);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Button Presence available on screen for *" + text + "* : " + e);
//			throw e;
//		}
//		return result;
//	}
//
//	public void clickOnRadioOption(String radioOptionName) {
//		try {
//			fixture.clickOnRadioOption(radioOptionName);
//			ReportListeners.logStep("info", "Radio Option Clicked: " + radioOptionName);
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Radio Option Not Clicked: " + radioOptionName + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while clicking option" + radioOptionName + e);
//			throw e;
//		}
//	}
//
//	public Boolean verifyFieldIsPresent(String text) {
//		boolean result = false;
//		try {
//			result = fixture.verifyFieldIsPresent(text);
//			if (result) {
//				ReportListeners.logStep("pass", "Field Presence verified: " + text);
//			} else {
//				ReportListeners.logStep("fail", "Field Presence verified, its not present: " + text);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Field Presence failed for *" + text + "* : " + e);
//			throw e;
//		}
//		return result;
//	}
//
//	public void populateFieldWith(String fieldName, String[] value) {
//		try {
//			fixture.populateFieldWith(fieldName, value);
//			common.waitForSeconds(2);
//			ReportListeners.logStep("info", "Populated '" + fieldName + "' with '" + value[0] + "'");
//		} catch (NoSuchElementException e) {
//			ReportListeners.logStep("fail", "Field with identifier '" + fieldName + "' not found: " + e);
//			throw e;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "An error occurred while populating '" + fieldName + "' field: " + e);
//			throw e;
//		}
//	}
//
//	public void waitForElement(WebElement element) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
//		wait.until(ExpectedConditions.elementToBeClickable(element));
//	}
//
//
//	public void waitForElementToDisappear(String xpath) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
//	}
//
//	public void waitForVisibilityOfText(String targetText, long maxWaitTimeMinutes) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(maxWaitTimeMinutes));
//		try {
//			wait.ignoring(org.openqa.selenium.NoSuchElementException.class);
//			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + targetText + "']")));
//			if (element.getText().contentEquals(targetText)) {
//				ReportListeners.logStep("info", "Text is Displayed : " + targetText);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Text is not Displayed : " + targetText);
//		}
//	}
//
//	public void waitForVisibilityOfTextContains(String targetText, long maxWaitTimeMinutes) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(maxWaitTimeMinutes));
//		try {
//			wait.ignoring(org.openqa.selenium.NoSuchElementException.class);
//			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text()='" + targetText + "')]")));
//			if (element.getText().contentEquals(targetText)) {
//				ReportListeners.logStep("info", "Text is Displayed : " + targetText);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Text is not Displayed : " + targetText);
//		}
//	}
//
//	public void clickOnTextContains(String strText) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
//		try {
//			wait.ignoring(org.openqa.selenium.NoSuchElementException.class);
//			WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + strText + "')]"));
//			if (element.isDisplayed()) {
//				element.click();
//				ReportListeners.logStep("info", "Clicked on Text : " + strText);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Not clicked on Text : " + strText);
//		}
//	}
//
//	public void verifyRadiobuttonSelected(String rdoValue, int count) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
//		WebElement rdoChoice = driver.findElement(By.xpath("(//*[@value='" + rdoValue + "'])[" + count + "]"));
//		try {
//			wait.ignoring(org.openqa.selenium.NoSuchElementException.class);
//			if (rdoChoice.isSelected()) {
//				ReportListeners.logStep("info", "Expected radio element is selected having value: " + rdoValue);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "unable to locate element : " + rdoChoice);
//		}
//	}
//
//	public void pressKeyEnter(WebElement element) {
//		common.waitForSeconds(2);
//		Actions action = new Actions(driver);
//		action.moveToElement(element).sendKeys(Keys.ENTER).perform();
//		common.waitForSeconds(2);
//	}
//
//
//	public void pressEnter() {
//		try {
//			ReportListeners.logStep("info", "Pressing Enter key via JavaScript.");
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript(
//					"document.activeElement.dispatchEvent(new KeyboardEvent('keydown', {key: 'Enter', bubbles: true}));"
//			);
//			ReportListeners.logStep("pass", "Successfully pressed Enter key via JavaScript.");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to press Enter key. Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error in pressEnter");
//			throw e;
//		}
//	}
//
//	public void attachFile(int count, String File) {
//		try {
//			ReportListeners.logStep("info", "Attaching file '" + File + "' at file input position: " + count);
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			WebElement input = driver.findElement(By.xpath("(//input[@type='file'])[" + count + "]"));
//			String filepath = CommonConstants.getTestDataFilePath() + File;
//			input.sendKeys(filepath);
//			common.waitForSeconds(10);
//			ReportListeners.logStep("pass", "File '" + File + "' attached successfully.");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to attach file '" + File + "'. Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error in attachFile");
//			throw e;
//		}
//	}
//
//	public String dateStringFormatter() {
//		try {
//			ReportListeners.logStep("info", "Formatting current date to string.");
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
//			String dateString = LocalDateTime.now().format(formatter);
//			ReportListeners.logStep("info", "Date formatted: " + dateString);
//			return dateString;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to format date string. Exception: " + e);
//			throw e;
//		}
//	}
//
//	public void fillTempoPicker(String pickerLabel, String valueToSelect, int count) {
//		try {
//			ReportListeners.logStep("info", "Filling TempoPicker '" + pickerLabel + "' with value: " + valueToSelect);
//
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			WebElement pickerInput = wait.until(ExpectedConditions.elementToBeClickable(
//					By.xpath("//*[contains(text(),'" + pickerLabel + "')]/following::input["+count+"]")
//			));
//
//			pickerInput.click();
//			pickerInput.sendKeys(valueToSelect);
//			wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li/div//p[text() = '" + valueToSelect + "']"))));
//			pickerInput.sendKeys(Keys.ARROW_DOWN);
//			pickerInput.sendKeys(Keys.ENTER);
//			common.waitForSeconds(3);
//
//			ReportListeners.logStep("pass", "TempoPicker '" + pickerLabel + "' filled with value: " + valueToSelect);
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to fill TempoPicker '" + pickerLabel + "'. Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error in fillTempoPicker");
//			throw e;
//		}
//	}
//
//	public void searchItems(String itemType, String searchValue, int count) {
//		try {
//			ReportListeners.logStep("info", "Searching for item in '" + itemType + "' with value: " + searchValue);
//
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			WebElement searchInputBox = wait.until(ExpectedConditions.elementToBeClickable(
//					By.xpath("//*[contains(text(),'" + itemType + "')]/following::input["+count+"]")));
//			searchInputBox.sendKeys(searchValue);
//			common.waitForSeconds(3);
//			pressEnter();
//			common.waitForProgressBar();
//
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to search items for '" + searchValue + "' in '" + itemType + "'. Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error in searchItems");
//			throw e;
//		}
//	}
//
//	public void expandIconBasedOnHeader(String header) {
//		try {
//			ReportListeners.logStep("info", "Expanding section: " + header);
//			WebElement sectionHeader = driver.findElement(By.xpath("//div[contains(@class, 'BoxLayout')]//*[contains(text(), '" + header + "')]"));
//			sectionHeader.click();
//			common.waitForSeconds(5);
//			ReportListeners.logStep("pass", "Expanded section: " + header);
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to expand section: " + header + ". Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error in expandIconBasedOnHeader for " + header);
//			throw e;
//		}
//	}
//
//	public void clickRadioButtonBasedOnQuestions(String question, String radioOptionValue) {
//		try {
//			ReportListeners.logStep("info", "Clicking radio button '" + radioOptionValue + "' for question: '" + question + "'");
//			WebElement chooseOption = driver.findElement(By.xpath("//*[contains(text(), '" + question + "')]//following::label[contains(text(), '" + radioOptionValue + "')]"));
//			chooseOption.click();
//			ReportListeners.logStep("pass", "Clicked radio button '" + radioOptionValue + "' for question: '" + question + "'");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to click radio button '" + radioOptionValue + "' for question: '" + question + "'. Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error clicking radio for " + question + " - " + radioOptionValue);
//			throw e;
//		}
//	}
//
//	public void fillTextArea(String textAreaQuestion, String textAreaValue) {
//		try {
//			ReportListeners.logStep("info", "Filling answer for question: '" + textAreaQuestion + "' with value: '" + textAreaValue + "'");
//			String textAreaXpath = "//*[contains(text(), '" + textAreaQuestion + "')]//following::textarea[@role = 'textbox']";
//			waitForVisibilityOfLocator(textAreaXpath, 10);
//			WebElement textAreaElement = driver.findElement(By.xpath(textAreaXpath));
//			textAreaElement.sendKeys(textAreaValue);
//			ReportListeners.logStep("pass", "Filled answer for question: '" + textAreaQuestion + "' with value: '" + textAreaValue + "'");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to fill answer for question: '" + textAreaQuestion + "'. Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error filling text area for " + textAreaQuestion);
//			throw e;
//		}
//	}
//
//	public void clearTextArea(String textAreaQuestion) {
//		try {
//			ReportListeners.logStep("info", "Clearing the text field: " + textAreaQuestion);
//			String textAreaXpath = "//*[contains(text(), '" + textAreaQuestion + "')]//following::textarea[@role = 'textbox']";
//			waitForVisibilityOfLocator(textAreaXpath, 10);
//			WebElement textAreaElement = driver.findElement(By.xpath(textAreaXpath));
//			textAreaElement.click();
//			textAreaElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//			textAreaElement.sendKeys(Keys.DELETE);
//			ReportListeners.logStep("pass", "Cleared the text field: " + textAreaQuestion );
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to clear the text field: " + textAreaQuestion + ". Exception: " + e);
//			ReportListeners.logScreenshotStep(driver, "Error clearing text area: " + textAreaQuestion);
//			throw e;
//		}
//	}
//
//	public boolean verifyButtonIsEnabled(String buttonName) {
//		try {
//			ReportListeners.logStep("info", "Verifying button is enabled: " + buttonName);
//			fixture.verifyButtonIsEnabled(buttonName);
//			ReportListeners.logStep("pass", "Button '" + buttonName + "' is enabled.");
//			return true;
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Exception while verifying button enabled: " + e.getMessage());
//			throw e;  // Will not return false, caller must handle
//		}
//	}
//
//	public void common.waitForSeconds(Integer seconds) {
//		try {
//			common.waitForSeconds(seconds);
//			ReportListeners.logStep("info", "Waited for " + seconds + " seconds.");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Exception while waiting: " + e.getMessage());
//			throw e;
//		}
//	}
//
//	public void refresh() {
//		try {
//			ReportListeners.logStep("info", "Refreshing the page.");
//			fixture.refresh();
//			ReportListeners.logStep("info", "Page refreshed.");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Exception while refreshing: " + e.getMessage());
//			throw e;
//		}
//	}
//
//	public void waitForProgressBar() {
//		try {
//			ReportListeners.logStep("info", "Waiting for progress bar.");
//			common.waitForProgressBar();
//			ReportListeners.logStep("info", "Progress bar is completed.");
//		} catch (Exception e) {
//			ReportListeners.logStep("info", "Exception while waiting for progress bar: " + e.getMessage());
//			throw e;
//		}
//	}
//
//	public void populateDropdownWithMultipleValues(String dropdownFieldName, String[] listOfOptions) {
//		WebElement dropdownField = driver.findElement(By.xpath("//span[text() = '" + dropdownFieldName + "']//following::div[@role = 'combobox']"));
//		try {
//			for (String option : listOfOptions) {
//				ReportListeners.logStep("info", "Typing and selecting dropdown option: " + option);
//				dropdownField.sendKeys(option);
//				WebElement chooseOption = driver.findElement(By.xpath("//*[text() = '" + option + "']"));
//				chooseOption.click();
//				fixture.clickOnText(dropdownFieldName);
//				ReportListeners.logStep("info", "Selected option: " + option);
//			}
//			ReportListeners.logStep("pass", "All the given options have been selected successfully.");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Exception while choosing option: " + e.getMessage());
//		}
//	}
//
//	public void clearField(String fieldName) {
//		try {
//			fixture.clearField(fieldName);
//			ReportListeners.logStep("pass", "Field '" + fieldName + "' cleared successfully.");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Failed to clear field '" + fieldName + "'. Exception: " + e.getMessage());
//			throw e;
//		}
//	}
//
//	public Boolean verifyFieldIsNotPresent(String fieldName) {
//		boolean result = false;
//		try {
//			result = fixture.verifyFieldIsNotPresent(fieldName);
//			if (result) {
//				ReportListeners.logStep("pass", "Field is not present: " + fieldName);
//			} else {
//				ReportListeners.logStep("fail", "Field is present: " + fieldName);
//			}
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Field Presence failed for *" + fieldName + "* : " + e);
//			throw e;
//		}
//		return result;
//	}
//
//	public boolean verifyFieldContainsValue(String fieldName, String fieldValue) {
//		boolean flag = false;
//		try {
//			fixture.verifyFieldContainsValue(fieldName, fieldValue);
//			flag = true;
//			ReportListeners.logStep("pass", "Field '" + fieldName + "' contains value '" + fieldValue + "'.");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Field '" + fieldName + "' does not contain value '" + fieldValue + "'. Exception: " + e.getMessage());
//			flag = false;
//		}
//		return flag;
//	}
//
//	public void waitForElements(List<WebElement> elements) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		try {
//			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
//			ReportListeners.logStep("pass", "Elements are visible");
//		} catch (Exception e) {
//			ReportListeners.logStep("fail", "Elements not visible: " + e.getMessage());
//		}
//	}
//
//	public void waitForVisibilityOfLocator(String xpath, int timeout) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
//		try {
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//			ReportListeners.logStep("pass", "Locators are visible");
//		} catch (Exception e){
//			ReportListeners.logStep("fail", "Locators are not visible: " + e.getMessage());
//		}
//	}
//
//	public void waitUntilTaskDescriptionAppears(String itemType, String name, String taskDescription) throws InterruptedException {
//		int maxAttempts = 20;
//		boolean isElementFound = false;
//
//		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
//			try {
//				// Always start from first page on each attempt
//				searchItems(itemType, name, 1);
//				common.waitForProgressBar();
//				common.waitForSeconds(3); // Wait for up to 10 seconds after first page load
//
//				// 1. Search first page
//				List<WebElement> requiredElement = driver.findElements(By.xpath(
//						"//*[contains(text(),'" + name + "')]/following::a[contains(text(), '" + taskDescription + "')]"
//				));
//
//				if (!requiredElement.isEmpty()) {
//					ReportListeners.logStep("info", "Element found on FIRST page, attempt " + attempt + ": " + name + " - " + taskDescription);
//					isElementFound = true;
//					break; // Found, stop attempts
//				}
//
//				// 2. Check and search NEXT page if Next button available
//				WebElement nextButton = null;
//				try {
//					nextButton = driver.findElement(By.xpath("//strong[contains(text(), '" + itemType + "')]//following::a[@aria-label='Next page' and @role='button' and contains(@class,'GridFooter---inGridPagingControl')]"));
//				} catch (Exception e) {
//					nextButton = null;
//				}
//
//				if (nextButton != null && nextButton.isDisplayed() && nextButton.isEnabled()) {
//					nextButton.click();
//					common.waitForProgressBar();
//					common.waitForSeconds(10); // Wait for up to 10 seconds after next page load
//
//					// Search only this next page
//					requiredElement = driver.findElements(By.xpath(
//							"//*[contains(text(),'" + name + "')]/following::a[contains(text(), '" + taskDescription + "')]"
//					));
//
//					if (!requiredElement.isEmpty()) {
//						ReportListeners.logStep("info", "Element found on SECOND page (after 'Next'), attempt " + attempt + ": " + name + " - " + taskDescription);
//						isElementFound = true;
//						break; // Found, stop attempts
//					}
//				}
//
//				// Not found in first or next page, refresh and retry
//				driver.navigate().refresh();
//				common.waitForProgressBar();
//				common.waitForSeconds(2);
//				ReportListeners.logStep("info", "Element not found after attempt: " + attempt + ". Refreshed and retrying...");
//
//			} catch (Exception e) {
//				ReportListeners.logStep("error", "Exception on attempt " + attempt + ": " + e.getMessage());
//				break; // Optionally break/continue based on your exception handling needs
//			}
//		}
//		if (!isElementFound) {
//			throw new RuntimeException("Element not found after max attempts or after an exception occurred");
//		}
//	}
//
//	public String switchToNewWindow() {
//		//Get current Window handle
//		String originalWindow = driver.getWindowHandle();
//
//		//Get All Window Handles
//		List<String> allWindows = new ArrayList<>(driver.getWindowHandles());
//
//		//Iterate through the window handles and switch to the new one
//		if (allWindows.size() > 1) {
//			String windowHandle = allWindows.get(allWindows.size() - 1);
//			driver.switchTo().window(windowHandle);
//			return windowHandle;
//		}
//
//		throw new RuntimeException("No new window found");
//	}
//
//	public void verifySessionTimePlaceholder(){
//		verifyTextIsPresent("Session Time");
//		driver.switchTo().frame(0);
//		WebElement elementInsideIframe = driver.findElement(By.xpath("//p[@id='viewer']"));
//		if(elementInsideIframe.isDisplayed()){
//			ReportListeners.logStep("Pass", "As expected, for all the screens the Timeout duration has shown on top right");
//		}else{
//			ReportListeners.logStep("Fail", "Failed as, for all the screens the Timeout duration has shown on top right");
//		}
//		//text need to be verified - enhancement is coming - refer PBI: 126446
//	}
//
//	public void openNewTab(String URL) {
//		String currentWindow = getCurrentWindow();
//		((JavascriptExecutor) driver).executeScript("window.open();");
//		Set<String> totalWindow = getTotalWindows();
//		switchToChildWindow(totalWindow, currentWindow);
//		common.waitForSeconds(5);
//		fixture.open(URL);
//	}
//
//	public void checkForGeneratedReport(String constantName) throws InterruptedException {
//		int maxAttempts = 25;
//		int attempt = 1;
//		boolean isElementFound = false;
//
//		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//		while (attempt <= maxAttempts) {
//			try {
//				shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'" + constantName + "')]")));
//				ReportListeners.logScreenshotStep(driver, "Report found screenshot");
//				System.out.println("Report found on attempt" + attempt);
//				isElementFound = true;
//				break;
//			} catch (NoSuchElementException | TimeoutException e) {
//				System.out.println("Report not found after " + attempt + " attempts");
//				common.waitForSeconds(10);
//				driver.navigate().refresh();
//				common.waitForProgressBar();
//				attempt++;
//			}
//		}
//		if (!isElementFound) {
//			throw new RuntimeException("Report not found after max attempts");
//		}
//	}
//
//	public void switchToDefault() {
//		common.waitForSeconds(1);
//		driver.switchTo().defaultContent();
//	}
//
//	public void switchToNewTab(int tab) {
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(tab));
//	}
//
//	public void navigateToUserManagement(){
//		WebElement navigationMenu = driver.findElement(By.xpath("(//*[@class='Button_SITE_HEADER_LAYOUT_NAME_NAVIGATION_MENU---pulldown'])[2]"));
//		navigationMenu.click();
//		clickOnText("Unified Access Hub");
//		switchToNewWindow();
//		clickOnSitePage("User Management");
//	}
//
//	public void changeRole(String user, String currentRole, String futureRole) {
//		try {
//            navigateToUserManagement();
//			// Starting role change process
//			ReportListeners.logStep("info", "Starting process to change role for user: " + user);
//
//			// Search for user in the input field
//			WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
//			inputField.sendKeys(user);
//			waitForProgressBar();
//
//			// Press Enter to search
//			pressEnter();
//			waitForProgressBar();
//
//			int USER_COLUMN_INDEX = 0;
//			int SYSTEM_ROLE_COLUMN_INDEX = 4;
//			int STATUS_COLUMN_INDEX = 6;
//
//			// Get all table rows, exclude header
//			List<WebElement> rows = driver.findElements(By.xpath("//tr"));
//			rows.remove(0); // remove header
//			boolean found = false;
//
//			for (WebElement row : rows) {
//				List<WebElement> cells = row.findElements(By.tagName("td"));
//				if (cells.size() > Math.max(Math.max(USER_COLUMN_INDEX, SYSTEM_ROLE_COLUMN_INDEX), STATUS_COLUMN_INDEX)) {
//					String userText = cells.get(USER_COLUMN_INDEX).getText().trim();
//					String systemRoleText = cells.get(SYSTEM_ROLE_COLUMN_INDEX).getText().trim();
//					String statusText = cells.get(STATUS_COLUMN_INDEX).getText().trim();
//
//					if (userText.equalsIgnoreCase(user) &&
//							systemRoleText.equalsIgnoreCase(futureRole) &&
//							statusText.equalsIgnoreCase("Onboard")) {
//						found = true;
//						ReportListeners.logStep("info", "Already found user '" + user + "' with future role '" + futureRole + "' and status 'Onboard'. No action required.");
//						break; // stop searching, we found a matching row
//					}
//				}
//			}
//
//			if (!found) {
//				ReportListeners.logStep("info", "No matching onboarded user/role found. Will update role for user: " + user);
//				clickOnLinkText(user);
//
//				removeRole(currentRole);
//				ReportListeners.logStep("info", "Removed current role '" + currentRole + "' from user '" + user + "'.");
//
//				addRole(futureRole);
//				ReportListeners.logStep("pass", "Added new role '" + futureRole + "' to user '" + user + "'.");
//			}
//
//		} catch (Exception ex) {
//			ReportListeners.logStep("FAIL", "Exception during role change process for user '" + user +
//					"'. Exception: " + ex.getMessage());
//			// Optionally: ex.printStackTrace();
//		}
//	}
//
//	private void removeRole(String currentRole) {
//		clickOnButton("OFF-BOARD ACCESS");
//		WebElement removeButton = driver.findElement(By.xpath("//*[text() = '" + currentRole + "']//following::*[name()='svg'][1][@data-owl-icon-name='minus-circle']"));
//		removeButton.click();
//		waitForProgressBar();
//		clickOnDialogButton("Submit");
//		verifyTextIsPresent("Accesss request created sucessfully for removing the role(s)");
//		verifyTextIsPresent("System Profiles Requested:");
//        verifyTextIsPresent(currentRole);
//		WebElement backButton = driver.findElement(By.xpath("//strong[contains(text(), 'BACK')]"));
//		backButton.click();
//	}
//
//	private void addRole(String futureRole) throws InterruptedException {
//		clickOnButton("Onboard User");
//		populateFieldWith("Application", new String[] {"VenGo"});
//		populateFieldWith("System Profile", new String[] {"Clinical Sourcing Manager (VenGo)"});
//		clickOnDialogButton("Submit");
//		verifyTextIsPresent("Access request created for adding roles");
//		verifyTextIsPresent("System Profiles Requested:");
//        verifyTextIsPresent(futureRole);
//		WebElement backButton = driver.findElement(By.xpath("//strong[contains(text(), 'BACK')]"));
//		backButton.click();
//        checkAssignedRole(futureRole);
//	}
//
//    public void checkAssignedRole(String futureRole) throws InterruptedException {
//        int MaxAttempts = 15;
//        int attempt = 1;
//        boolean isElementFound = false;
//        while (attempt <= MaxAttempts) {
//            List<WebElement> element = driver.findElements(By.xpath("//strong[text()='VenGo']//following::div[text()='" + futureRole + "']"));
//            if (element.size() > 0) {
//                ReportListeners.logStep("info", "Element found on attempt " + attempt + ": ");
//                isElementFound = true;
//                break;
//            } else {
//                common.waitForSeconds(10);
//                driver.navigate().refresh();
//                waitForProgressBar();
//                ReportListeners.logStep("info", "Element not found after attempt " + attempt + ". Refreshed and retrying...");
//                attempt++;
//            }
//        }
//        if (!isElementFound) {
//            throw new RuntimeException("Report not found after max attempts");
//        }
//    }
//
//}
//
//
