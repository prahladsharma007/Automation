//package CTTNotes.pages;
//
//import AUT.listeners.ReportListeners;
//import AUT.utilities.CommonMethods;
//import com.appiancorp.ps.automatedtest.fixture.SitesFixture;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.FindBys;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//public class HomePagewithAppian {
//    public WebDriver driver;
//    public SitesFixture fixture;
//    public CommonMethods common;
//    String startDateValue;
//    String endDateValue;
//    boolean notesPresent;
//    boolean notesPresentCPM;
//
//    @FindBy(xpath = "(//button//span[text()='NOTE'])[last()]")
//    WebElement noteDropdown;
//
//    @FindBy(xpath = "(//li//span[text()='Notifications'])[2]")
//    WebElement notificationTab;
//
//    @FindBy(xpath = "//span[text()='--- Select a Value ---']")
//    WebElement studyDropDown;
//
//    @FindBy(xpath = "//input[contains(@placeholder,'Search Study')]")
//    WebElement searchStudy_textbox;
//
//    @FindBy(xpath = "//span[contains(text(),'Attachments')]")
//    WebElement attachments_Tab;
//
//    @FindBy(xpath = "(//a[contains(@href,'https://nvs-') and contains(@href,'appiancloud.com/suite/sites/note/page/home/record/')])")
//    WebElement studyPresentAndClickable;
//
//    @FindBy(xpath = "(//a[contains(@href,'https://nvs-') and contains(@href,'appiancloud.com/suite/sites/note/page/home/record/')])[1]")
//    WebElement firstActiveStudy;
//
//    @FindBy(xpath = "(//span[text()='Upload']//ancestor::div)[last()-1]//input")
//    WebElement upload_Button;
//
//    @FindBy(xpath = "(//span[text()='Note'])[2]")
//    WebElement noteTab;
//
//    @FindBy(xpath = "(//span[text()='Survey (Pilot)'])[2]")
//    WebElement surveyTab;
//
//    @FindBy(xpath = "//div[@data-testid='CardLayout-cardDiv']/div[@class='ContentLayout---content_layout ContentLayout---padding_less']//div[@class='FieldLayout---field_layout FieldLayout---margin_below_none FieldLayout---margin_above_none']")
//    WebElement notificationPresent;
//
//    @FindBy(xpath = "//span[contains(text(),'_Notes_')]")
//    WebElement notesPresence;
//
//    @FindBy(xpath = "//span[contains(text(),'_CPM_')]")
//    WebElement notesPresenceCPM;
//
//    @FindBy(xpath = "//div[@data-testid='CardLayout-cardDiv' and @role='link' and contains(@class,'CardLayout---margin_above_none CardLayout---border_visible CardLayout---semi_rounded CardLayout---height_auto CardLayout---width_fit CardLayout---linked_card CardLayout---light_background')]")
//    WebElement recentDecisionsPresence;
//
//    @FindBy(xpath = "//div[@data-testid='CardLayout-cardDiv' and @role='link' and contains(@class,'CardLayout---margin_below_standard')]")
//    WebElement myTaskPresence;
//
//    @FindBy(xpath = ("//div[@class='FileInfoView---icon']//a"))
//    WebElement cross_Icon;
//
//    @FindBy(xpath = "(//span[@class='IconWidget---medium_plus'])[last()]")
//    WebElement expandTopicIcon;
//
//    @FindBy(xpath = "//span[text()='Add Attachment']//ancestor::button")
//    WebElement addAttachment_button;
//
//    @FindBy(xpath = "//a[@data-testid='textInput-clearLink']")
//    WebElement clearSearch_Icon;
//
//    @FindBy(xpath = "//a[contains(@href,'notifications')]//span[text()='View All']")
//    WebElement viewAllNotification;
//
//    @FindBy(xpath = "(//span[text()='View All'])[1]")
//    WebElement viewAllStudies;
//
//    @FindBy(xpath = "//nav[@data-testid='VirtualNavigationHeaderLayout-nav']//span[text()='Home']")
//    WebElement home_Tab;
//
//    @FindBy(xpath = "//span[text()='Manage filters']//preceding-sibling::span")
//    WebElement manageFilter_tab;
//
//    @FindBy(xpath = "//div[text()='Study Configuration' and @class='TabButtonWidget---tab_label']")
//    WebElement studyConfiguration_tab;
//
//    @FindBy(xpath = "//span[text()='Clear filters']")
//    WebElement clearFilter_Icon;
//
//    @FindBy(xpath = "//button//span[text()='More actions']")
//    WebElement moreAction;
//
//    @FindBy(xpath = "//input[@placeholder='Search' and @type='text']")
//    WebElement searchTopic;
//
//    @FindBy(xpath = "(//div[@class='CheckboxGroup---choice_group CheckboxGroup---no_label CheckboxGroup---align_start'])[2]")
//    WebElement addTopicCheckbox;
//
//    @FindBy(xpath = "//span[text()='ADD TOPIC']")
//    WebElement addTopicButton;
//
//    @FindBy(xpath = "//input[@placeholder='dd/mm/yyyy']")
//    WebElement decisionMadeDate;
//
//    @FindBy(xpath = "//input[@placeholder='dd/mm/yyyy']")
//    WebElement taskDueDate;
//
//    @FindBy(xpath = "(//*[@data-owl-icon-name='pencil'])[last()]")
//    WebElement editOtherTeam;
//
//    @FindBy(xpath = "(//*[@data-owl-icon-name='trash'])[last()]")
//    WebElement deleteOtherTeam;
//
//    @FindBy(xpath = "//body[@class='mce-content-body ' and @data-id= 'textContent']//p")
//    WebElement inputTextbox;
//
//    @FindBy(xpath = "(//span[text()='CLOSE NOTE'])[3]")
//    WebElement closeNoteButton;
//
//    @FindBy(xpath = "//span[@data-placeholder='Select Assignees']")
//    WebElement taskAssignTo;
//
//    @FindBy(xpath = "//span[text()='--- Select a Value ---']")
//    WebElement taskPriority;
//
//    @FindBy(xpath = "(//p[@class='ParagraphText---richtext_paragraph ParagraphText---default_direction ParagraphText---align_start elements---global_p' and @data-testid='ParagraphText-paragraph'])[2]")
//    WebElement noteStartDate;
//
//    @FindBy(xpath = "(//input[@placeholder='dd/mm/yyyy'])[1]")
//    WebElement startDate;
//
//    @FindBy(xpath = "(//input[@placeholder='dd/mm/yyyy'])[2]")
//    WebElement endDate;
//
//    @FindBy(xpath = "//span[text()='Save']")
//    WebElement saveButtonVisibility;
//
//    @FindBy(xpath = "(//input[@class='TextInput---text TextInput---align_start TextInput---inEditableGridLayout' and @type='text'])[1]")
//    WebElement nameOnAddDecision;
//
//    @FindBy(xpath = "(//input[@class='TextInput---text TextInput---align_start TextInput---inEditableGridLayout' and @type='text'])[3]")
//    WebElement roleOnAddDecision;
//
//    @FindBy(xpath = "//textarea[@role='textbox']")
//    WebElement decisionTextbox;
//
//    @FindBy(xpath = "//span[text()='Edit Key Discussions']")
//    WebElement editKeyDiscussionButton;
//
//    @FindBy(xpath = "//span[text()='Notes' and @class='SizedText---medium SizedText---predefined']")
//    WebElement selectTemplate;
//
//    @FindBy(xpath = "//span[text()='CPM' and @class='SizedText---medium SizedText---predefined']")
//    WebElement selectTemplateCPM;
//
//    @FindBy(xpath = "//input[contains(@placeholder,'Search Note')]")
//    WebElement searchNote_textbox;
//
//    @FindBy(xpath = "(//button[@type='button'])[5]")
//    WebElement getManagerFilter;
//
//    @FindBy(xpath = "//input[@placeholder='Search Topics']")
//    WebElement searchTopicTextBox;
//
//    @FindBy(xpath = "(//label[text()='From Date']/ancestor::div)[last()]//following-sibling::div//input")
//    WebElement outOfOfficeFromDate;
//
//    @FindBy(xpath = "(//label[text()='To Date']/ancestor::div)[last()]//following-sibling::div//input")
//    WebElement outOfOfficeToDate;
//
//    @FindBy(xpath = "(//span[text()='Delete Quick Link']//parent::button[@type='button'])")
//    WebElement deleteButton;
//
//    @FindBy(xpath = "//span[@data-placeholder='Select Users']")
//    WebElement selectUsers;
//
//    @FindBy(xpath = "(//span[@class='SizedText---medium_plus SizedText---predefined']//a[contains(@class,'LinkedItem---standalone_richtext_link elements---global_a')])[1]")
//    WebElement toggleBar;
//
//    @FindBy(xpath = "//span[@data-placeholder='Any']")
//    WebElement calendarDropdown;
//
//    @FindBy(xpath = "//span[text()='Study Level Color Tag']/..")
//    WebElement studyLevelColorTag;
//
//    @FindBys({@FindBy(xpath = "//span[contains(@class,'IconWidget---large')]")})
//    List<WebElement> colorCodes;
//
//    @FindBy(xpath = "//*[name()='svg' and @data-owl-icon-name='check-square']/..")
//    WebElement selectedColorCode;
//
//    @FindBys({@FindBy(xpath = "//*[name()='svg' and @data-owl-icon-name='check-square']/..")})
//    List<WebElement> colorSelectedOrNot;
//
//    @FindBy(xpath = "//span[@class='SizedText---medium SizedText---predefined']/ancestor::div[@class='CardLayout---scrollable_content']/preceding-sibling::div")
//    WebElement getBarColor;
//
//    @FindBy(xpath = "//strong[@class='StrongText---richtext_strong']/a/span")
//    WebElement firstStudy;
//
//    @FindBy(xpath = "//ol[contains(@class,'BreadcrumbLayout---breadcrumbs')]/../..")
//    WebElement headerColor;
//
//    @FindBy(xpath = "(//span[@class='VirtualUserProfileLayout---avatar_initials_text'])[2]")
//    WebElement signOut;
//
//    @FindBy(xpath = "(//div[@class='PickerWidget---picker_value PickerWidget---placeholder_visible'])")
//    WebElement selectTeamMembers;
//
//    @FindBy(xpath = "//*[contains(text(),'EDIT KEY DISCUSSION')]//ancestor::div[contains(@class,'inModal')]//iframe")
//    WebElement iframe1;
//
//    @FindBy(xpath = "//h1[text()='ADD DECISION']//ancestor::div[contains(@class,'inModal')]//iframe")
//    WebElement iframe2;
//
//    public HomePagewithAppian(WebDriver driver, SitesFixture fixture, CommonMethods common) {
//        this.driver = driver;
//        this.fixture = fixture;
//        this.common = common;
//        PageFactory.initElements(driver, this);
//    }
//
//    public String selectColorTag() {
//        Random rand = new Random();
//        studyLevelColorTag.click();
//        common.verifyTextIsPresent("TAG COLOR TO STUDY");
//        do {
//            int item = rand.nextInt(colorCodes.size());
//            colorCodes.get(item).click();
//            common.waitForProgressBar();
//        } while (colorSelectedOrNot.size() <= 0);
//        String code = selectedColorCode.getAttribute("style");
//        return code.split("\\(")[1].split("\\)")[0];
//    }
//
//    public String getColorAfterSelection() {
//        return getBarColor.getAttribute("style").split("\\(")[1].split("\\)")[0];
//    }
//
//    public String verifyColorCodeInFirstStudyHeader() {
//        firstStudy.click();
//        common.waitForProgressBar();
//        return headerColor.getAttribute("style").split("\\(")[1].split("\\)")[0];
//    }
//
//    public void openItemFromDropdown(String option) {
//        noteDropdown.click();
//        common.waitForProgressBar();
//        common.clickOnText(option);
//
//    }
//
//    public void inputNameOnAddDecision(String text) {
//        nameOnAddDecision.sendKeys(text);
//        common.waitForSeconds(1);
//    }
//
//    public void inputRoleOnAddDecision(String text) {
//        roleOnAddDecision.sendKeys(text);
//        common.waitForSeconds(1);
//    }
//
//    public void clickNoteTab() {
//        noteTab.click();
//        common.waitForSeconds(2);
//    }
//
//    public void clickSurveyTab() {
//        surveyTab.click();
//        common.waitForSeconds(2);
//    }
//
//    public void clickStudyConfigurationTab() {
//        studyConfiguration_tab.click();
//    }
//
//    public void clickEditForOtherTeam() {
//        editOtherTeam.click();
//    }
//
//    public void clickDeleteForOtherTeam() {
//        deleteOtherTeam.click();
//    }
//
//    public void openEditKeyDiscussionInNewTab() {
//        Actions act = new Actions(driver);
//        act.keyDown(Keys.CONTROL).click(editKeyDiscussionButton).keyUp(Keys.CONTROL).perform();
//        common.waitForSeconds(3);
//    }
//
//    public void inputDecision(String text) {
//        decisionTextbox.sendKeys(text);
//    }
//
//    public void inputDecisionRationale(String text) {
//        driver.switchTo().frame(iframe2);
//        driver.switchTo().frame("textContent_ifr");//textContent_ifr
//        inputTextbox.sendKeys(text);
//        common.waitForSeconds(1);
//        driver.switchTo().defaultContent();
//    }
//
//    public void inputDecisionRationaleInEditDecision(String text) {
//        WebElement iframe1 = driver.findElement(By.xpath("//h1[text()='EDIT DECISION']//ancestor::div[contains(@class,'inModal')]//iframe"));
//        driver.switchTo().frame(iframe1);
//        driver.switchTo().frame("textContent_ifr");//textContent_ifr
//        inputTextbox.sendKeys(text);
//        common.waitForSeconds(1);
//        driver.switchTo().defaultContent();
//    }
//
//    public void inputKeyDiscussionTextbox(String text) {
//        common.waitForSeconds(3);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
//        wait.until(ExpectedConditions.visibilityOf(saveButtonVisibility));
//        driver.switchTo().frame(iframe1);
//        driver.switchTo().frame("textContent_ifr");//textContent_ifr
//        inputTextbox.sendKeys(text);
//        common.waitForSeconds(1);
//        driver.switchTo().defaultContent();
//    }
//
//    public void verifyKeyDiscussion() {
//        common.waitForSeconds(3);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
//        wait.until(ExpectedConditions.visibilityOf(saveButtonVisibility));
//        driver.switchTo().frame(iframe1);
//        driver.switchTo().frame("textContent_ifr");
//    }
//
//    public void switchToDefault() {
//        common.waitForSeconds(1);
//        driver.switchTo().defaultContent();
//    }
//
//    public void inputKeyDiscussionTextboxInNewTab(String text) {
//        common.waitForSeconds(3);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
//        wait.until(ExpectedConditions.visibilityOf(saveButtonVisibility));
//        driver.switchTo().frame(iframe1);
//        driver.switchTo().frame("textContent_ifr");//textContent_ifr
//        inputTextbox.clear();
//        inputTextbox.sendKeys(text);
//        common.waitForSeconds(2);
//        driver.switchTo().defaultContent();
//    }
//
//    public void clickOnViewAllNotification() {
//        common.waitForSeconds(2);
//        viewAllNotification.click();
//    }
//
//    public void clickOnViewAllStudies() {
//        viewAllStudies.click();
//        common.waitForSeconds(3);
//    }
//
//    public String readNoteStartDate() {
//        String startdate = noteStartDate.getText();
//        return startdate;
//    }
//
//    public void clickOnAddTopicCheckbox() {
//        addTopicCheckbox.click();
//        common.waitForSeconds(5);
//    }
//
//    public void clickOnMultipleTopicCheckboxes(int topics) {
//        for (int i = 0; i < topics; i++) {
//            int j = i + 2;
//            driver.findElement(By.xpath("(//div[@class='CheckboxGroup---choice_group CheckboxGroup---no_label CheckboxGroup---align_start'])[" + j + "]")).click();
//            common.waitForProgressBar();
//        }
////        common.waitForSeconds(5);
//    }
//
//    public void checkDownloadReportIcon() {
//        int refreshCount = 15;
//        List<WebElement> element = driver.findElements(By.xpath("//span[text()='Last exported']/preceding-sibling::span"));
//        outerloop:
//        for (int i = 0; i < refreshCount; i++) {
////            List<WebElement> element = driver.findElements(By.xpath("//a[contains(@href,'https://nvs-') and contains(@href,'appiancloud.com/suite/rest/a/content/latest')]"));
//            if (element.size() > 0) {
//                common.verifyLinkIsPresent("Last exported");
//                break outerloop;
//            } else {
//                driver.navigate().refresh();
//                common.waitForProgressBar();
//                System.out.println("Interation no. " + i);
//                element = driver.findElements(By.xpath("//span[text()='Last exported']/preceding-sibling::span"));
//            }
//        }
//    }
//
//    public void waitForUpdatedStudyText() {
//        int refreshCount = 15;
//        List<WebElement> element = driver.findElements(By.xpath("//*[text()='updated study settings in']"));
//        outerloop:
//        for (int i = 0; i < refreshCount; i++) {
//            if (element.size() > 0) {
//                common.verifyTextIsPresent("updated study settings in");
//                break outerloop;
//            } else {
//                driver.navigate().refresh();
//                common.waitForProgressBar();
//                System.out.println("Interation no. " + i);
//                element = driver.findElements(By.xpath("//*[text()='updated study settings in']"));
//            }
//        }
//    }
//
//    public String getTopicList() {
//        List<WebElement> topics = driver.findElements(By.xpath("//div[@data-testid='CardLayout-cardDiv' and contains(@class,'CardLayout---height_auto CardLayout---width_fit')]//div[@data-testid='SideBySideItem-wrapper' and contains(@class,'bias10x appian-context-last-in-list')]//p/span"));
//        List<String> topicTexts = new ArrayList<>();
//        for (WebElement topic : topics) {
//            topicTexts.add(topic.getText());
//        }
//        Collections.sort(topicTexts);
//        String FirstTopicText = topicTexts.get(0);
//        System.out.println(FirstTopicText);
//        return FirstTopicText;
//    }
//
//    public String getFirstTopicText() {
//        WebElement firstTopic = driver.findElement(By.xpath("(//div[@data-testid='CardLayout-cardDiv' and contains(@class,'CardLayout---height_auto CardLayout---width_fit')]//div[@data-testid='SideBySideItem-wrapper' and contains(@class,'bias10x appian-context-last-in-list')]//p/span)[1]"));
//        String topicText = firstTopic.getText();
//        System.out.println(topicText);
//        return topicText;
//    }
//
//    public void clickOnAddTopic() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.elementToBeClickable(addTopicButton));
//        addTopicButton.click();
//        common.waitForSeconds(5);
//    }
//
//    public void inputSearchTopic(String text) {
//        searchTopic.sendKeys(text);
//        common.waitForSeconds(1);
//    }
//
//    public void inputSearchTopicTextBox(String text) {
//        searchTopicTextBox.sendKeys(text);
//        common.waitForSeconds(1);
//    }
//
//    public void clickOnExpandTopicIcon() {
//        expandTopicIcon.click();
//        common.waitForSeconds(2);
//    }
//
//    public void clickOnCloseNoteButton() {
//        closeNoteButton.click();
//    }
//
//    public void clickOnManageFilters() {
//        common.wait_for_element_to_be_clickable(getManagerFilter, 10);
//        getManagerFilter.click();
//        common.waitForSeconds(1);
//    }
//
//    public void clickOnDeleteButton() {
//        deleteButton.click();
//        common.waitForSeconds(1);
//    }
//
//    public void checkStudiesPresence() {
//        boolean result = false;
//        try {
//            result = studyPresentAndClickable.isDisplayed();
//            if (result) {
//                ReportListeners.logStep("pass", "Studies presence verified and are Clickable");
//            } else {
//                ReportListeners.logStep("pass", "No Studies present");
//            }
//        } catch (Exception e) {
//            ReportListeners.logStep("pass", "Studies presence not verified" + e);
//            throw e;
//        }
//    }
//
//    public void checkRecentDecisionPresence() {
//        boolean result = false;
//        try {
//            result = recentDecisionsPresence.isDisplayed();
//            if (result = true) {
//                ReportListeners.logStep("pass", "Recent Decisions presence verified");
//            } else {
//                ReportListeners.logStep("pass", "Recent Decisions not present");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void clickExport() {
//
//        boolean result = false;
//        try {
//            result = moreAction.isDisplayed();
//            if (result = true) {
//                common.clickOnButton("Export");
//                ReportListeners.logStep("pass", "Clicked on Export");
//            } else {
//                Actions act = new Actions(driver);
//                act.moveToElement(moreAction).click();
//                common.waitForSeconds(1);
//                act.sendKeys(Keys.ARROW_DOWN).perform();
//                common.waitForSeconds(1);
//                act.sendKeys(Keys.ENTER).perform();
//                common.waitForSeconds(1);
//                common.waitForProgressBar();
//                ReportListeners.logStep("pass", "Clicked on More Action and then Export");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void checkMyTaskPresence() {
//        boolean result = false;
//        try {
//            result = myTaskPresence.isDisplayed();
//            if (result = true) {
//                ReportListeners.logStep("pass", "My Tasks presence verified");
//            } else {
//                ReportListeners.logStep("pass", "My Tasks not present");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void clickOnRecentDecision() {
//        recentDecisionsPresence.click();
//    }
//
//    public void selectTaskAssignTo() {
//        Actions act = new Actions(driver);
//        act.moveToElement(taskAssignTo).click();
//        common.waitForSeconds(1);
////        act.sendKeys(Keys.ARROW_DOWN).perform();
////        common.waitForSeconds(1);
//        act.sendKeys(Keys.ENTER).perform();
//        common.waitForSeconds(1);
//    }
//
//    public void selectTaskPriority() {
//        Actions act = new Actions(driver);
//        act.moveToElement(taskPriority).click();
//        common.waitForSeconds(1);
//        act.sendKeys(Keys.ARROW_DOWN).perform();
//        common.waitForSeconds(1);
//        act.sendKeys(Keys.ENTER).perform();
//        common.waitForSeconds(1);
//    }
//
//    public void clickOnMyTask() {
//        myTaskPresence.click();
//    }
//
//    public boolean checkNotificationsPresence() {
//        boolean result = false;
//        try {
//            result = notificationPresent.isDisplayed();
//            if (result) {
//                ReportListeners.logStep("pass", "Notification presence verified");
//            } else {
//                ReportListeners.logStep("fail", "Notification presence not verified");
//            }
//        } catch (Exception e) {
//            ReportListeners.logStep("fail", "Notification presence not verified" + e);
//            throw e;
//        }
//        return result;
//    }
//
//    public void clickOnActiveStudy() {
//        firstActiveStudy.click();
//        common.waitForSeconds(3);
//    }
//
//    public void searchAndVerifyStudy(String study) {
//        common.waitForSeconds(2);
//        common.selectAllFromKeyboard(searchStudy_textbox);
//        searchStudy_textbox.sendKeys(study);
//        common.hitEnterFromKeyboard(searchStudy_textbox);
//        common.verifyTextIsPresent(study);
//        common.waitForSeconds(3);
//    }
//
//    public void expandSection(String str) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//span[text()='" + str + "']//ancestor::div[@role='presentation'])[2]//a[@href='#']"))));
//        ele.click();
//    }
//
//    public void switchToNewTab(int tab) {
//        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(tab));
//    }
//
//    public void closeCurrentTab() {
//        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//        driver.close();
//        driver.switchTo().window(tabs.get(0));
//    }
//
//    public void navigateToAttachmentsTabInNotes() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(attachments_Tab));
//        ele.click();
//    }
//
//    public void clickOnSelectTemplate() {
//        common.waitForSeconds(3);
//        selectTemplate.click();
//        common.waitForSeconds(3);
//    }
//
//    public void clickOnSelectTemplateCPM() {
//        common.waitForSeconds(3);
//        selectTemplateCPM.click();
//        common.waitForSeconds(3);
//    }
//
//    public String getStartDate() throws ParseException {
//        try {
//            common.waitForSeconds(3);
//            notesPresent = notesPresence.isDisplayed();
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }
//        if (notesPresent) {
//            System.out.println("Notes presence is " + notesPresent);
//            List<WebElement> dateElements = driver.findElements(By.xpath("//span[text()='END DATE']//parent::div[1]//following-sibling::div//p"));
//            List<Date> dates = new ArrayList<>();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//            for (WebElement element : dateElements) {
//                String dateString = element.getText();
//                try {
//                    Date date = dateFormat.parse(dateString);
//                    dates.add(date);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//            Collections.sort(dates, Collections.reverseOrder());
//
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            startDateValue = sdf.format(dates.get(0));
//            System.out.println("Date returned is " + startDateValue);
//            return startDateValue;
//        } else {
//            System.out.println("Notes presence is " + notesPresent);
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String todayDate = sdf.format(date);
//            System.out.println("Date returned is " + todayDate);
//
//            return todayDate;
//        }
//    }
//
//    public String getStartDateCPM() throws ParseException {
//        try {
//            common.waitForSeconds(3);
//            notesPresentCPM = notesPresenceCPM.isDisplayed();
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }
//        if (notesPresentCPM) {
//            System.out.println("Notes presence is " + notesPresentCPM);
//            List<WebElement> dateElements = driver.findElements(By.xpath("//span[text()='END DATE']//parent::div[1]//following-sibling::div//p"));
//            List<Date> dates = new ArrayList<>();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//            for (WebElement element : dateElements) {
//                String dateString = element.getText();
//                try {
//                    Date date = dateFormat.parse(dateString);
//                    dates.add(date);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//            Collections.sort(dates, Collections.reverseOrder());
//
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            startDateValue = sdf.format(dates.get(0));
//            System.out.println("Date returned is " + startDateValue);
//            return startDateValue;
//        } else {
//            System.out.println("Notes presence is " + notesPresentCPM);
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String todayDate = sdf.format(date);
//            System.out.println("Date returned is " + todayDate);
//
//            return todayDate;
//        }
//    }
//
//    public void uploadAttachmentToNotes(String path) {
//        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        //WebElement ele1 = wait.until(ExpectedConditions.visibilityOf(upload_Button));
//        common.waitForSeconds(2);
//        upload_Button.sendKeys(path);
//    }
//
//    public String verifyFutureDate(String futureDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate d = LocalDate.parse(futureDate, formatter);
//        LocalDate d1 = d.plusDays(9);
//        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
//        String newDate = d1.format(outFormatter);
//        System.out.println("New note to be created on " + newDate);
//        return newDate;
//    }
//
//    public void selectStartDate(String startDateV) {
//        //      try{
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        try {
//            LocalDate d = LocalDate.parse(startDateV, formatter);
//            LocalDate d1 = d.plusDays(1);
//            String newDate = d1.format(formatter);
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String todayDate = sdf.format(date);
//            LocalDate d2 = LocalDate.parse(todayDate, formatter);
//            String currentDate = d2.format(formatter);
//
//            //       if (notesPresent) {
//            if (d1.isBefore(d2)) {
//                System.out.println("Start Date is " + currentDate);
//                startDate.sendKeys(currentDate);
//                common.waitForSeconds(1);
//                endDate.sendKeys(currentDate);
//            } else {
//                System.out.println("New Date is " + newDate);
//                startDate.sendKeys(newDate);
//                common.waitForSeconds(1);
//                endDate.sendKeys(newDate);
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
////           finally {
////               if(notesPresent = false){
////                   System.out.println("Today's Date is " + todayDate);
////                   startDate.sendKeys(todayDate);
////                   common.waitForSeconds(1);
////                   endDate.sendKeys(todayDate);
////               }
//    }
//
//
////        catch(DateTimeParseException e){
////            e.printStackTrace();
////        }
//    //   }
//
//    public void selectEndDate() {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        String formattedDate = sdf.format(date);
//        endDate.sendKeys(formattedDate);
//        System.out.println("End Date is " + formattedDate);
//        common.waitForSeconds(3);
//    }
//
//    public void changeEndDate(String currentEndDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate d = LocalDate.parse(currentEndDate, formatter);
//        LocalDate d1 = d.plusDays(1);
//        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String newDate = d1.format(outFormatter);
//        endDate.clear();
//        common.waitForSeconds(1);
//        endDate.sendKeys(newDate);
//        common.waitForSeconds(2);
//    }
//
//    public void inputDecisionMadeOnDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, 1); //minus number would decrement the days
//        String date2 = sdf.format(cal.getTime());
//        decisionMadeDate.sendKeys(date2);
//        System.out.println("Decision made on Date is " + date2);
//        common.waitForSeconds(1);
//    }
//
//    public void inputPastDecisionMadeOnDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -100); //minus number would decrement the days
//        String date = sdf.format(cal.getTime());
//        decisionMadeDate.sendKeys(date);
//        System.out.println("Decision made on Date is " + date);
//        common.waitForSeconds(1);
//    }
//
//    public void inputFutureDecisionMadeOnDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, +2); //minus number would decrement the days
//        String date = sdf.format(cal.getTime());
//        decisionMadeDate.sendKeys(Keys.CONTROL + "a");
//        decisionMadeDate.sendKeys(Keys.BACK_SPACE);
//        decisionMadeDate.sendKeys(date);
//        System.out.println("Decision made on Date is " + date);
//        common.waitForSeconds(1);
//    }
//
//    public void inputPastDecisionMadeOnDateInEdit() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -100); //minus number would decrement the days
//        String date = sdf.format(cal.getTime());
//        decisionMadeDate.sendKeys(Keys.CONTROL + "a");
//        decisionMadeDate.sendKeys(Keys.BACK_SPACE);
//        decisionMadeDate.sendKeys(date);
//        System.out.println("Edited Decision made on Date is " + date);
//        common.waitForSeconds(1);
//    }
//
//    public void inputTaskDueDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, 1); //minus number would decrement the days
//        String date = sdf.format(cal.getTime());
//        taskDueDate.sendKeys(date);
//        System.out.println("Task Due Date is " + date);
//        common.waitForSeconds(1);
//    }
//
//    public void inputTaskDueDateChanged() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate today = LocalDate.now();
//        LocalDate futureDate = today.plusDays(2);
//        String formattedDate = futureDate.format(formatter);
//        taskDueDate.sendKeys(Keys.CONTROL + "a");
//        taskDueDate.sendKeys(Keys.BACK_SPACE);
//        taskDueDate.sendKeys(formattedDate);
//        System.out.println(" Task Changed Date is " + formattedDate);
//        common.waitForSeconds(1);
//    }
//
//    public void clickOnCrossIconForFileUpload() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(cross_Icon));
//        element.click();
//    }
//
//    public void waitTillAddAttachmentIsEnabled() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addAttachment_button));
//    }
//
//    public void deleteAttachment(String fileName) {
//        common.waitForSeconds(3);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("((//p[text()='" + fileName + "']//ancestor::div)[last()-4]//p)[last()]")));
//        deleteIcon.click();
//    }
//
//    public void clickOnAddAttachmentButton() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement addAttachmentButton = wait.until(ExpectedConditions.elementToBeClickable(addAttachment_button));
//        addAttachmentButton.click();
//        common.waitForSeconds(9);
//    }
//
//    public void clearSearchResult() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(clearSearch_Icon));
//        ele.click();
//
//    }
//
//    public void clearFilterValue(String fieldName) {
//        switch (fieldName) {
//            case "Study Lead":
//                driver.findElement(By.xpath("//a[@aria-label='Clear value for Study Lead']")).click();
//                break;
//            case "Status":
//                driver.findElement(By.xpath("//a[@aria-label='Clear value for Status']")).click();
//                break;
//            case "Favourtie":
//                driver.findElement(By.xpath("//a[@aria-label='Clear value for Favourite']")).click();
//                break;
//            case "Project":
//                driver.findElement(By.xpath("//a[@aria-label='Clear value for Project']")).click();
//                break;
//            case "DU":
//                driver.findElement(By.xpath("//a[@aria-label='Clear value for DU']")).click();
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void applyFilterOnStudyGrid(String dropdown, String value) {
//        WebElement ele = driver.findElement(By.xpath("//div[@role='combobox']//span[text()='" + dropdown + "']"));
//        ele.click();
//        if (dropdown.equals("Study Status") || dropdown.equals("Favourite") || dropdown.equals("DU")) {
//            driver.findElement(By.xpath("//li[@role='option']//span[text()='" + value + "']")).click();
//        } else {
//            driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(value);
//            driver.findElement(By.xpath("//li[@role='option']//span[text()='" + value + "']")).click();
//        }
//    }
//
//    public void clearAllFilter() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement ele1 = wait.until(ExpectedConditions.visibilityOf(manageFilter_tab));
//        ele1.click();
//        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement ele2 = wait2.until(ExpectedConditions.visibilityOf(clearFilter_Icon));
//        ele2.click();
//        common.waitForSeconds(2);
//    }
//
//    public void markStudyAsFavourite(String study) {
//        WebElement favIcon = driver.findElement(By.xpath("(//a[text()='" + study + "']//ancestor::tr//td)[last()]//a"));
//        String iconStatus = driver.findElement(By.xpath("(//a[text()='" + study + "']//ancestor::tr//td)[last()]//a//span[2]")).getText();
//        if (iconStatus.equalsIgnoreCase("Mark Favourite Study")) {
//            favIcon.click();
//            common.waitForProgressBar();
//            common.verifyTextIsPresent("MARK STUDY FAVOURITE");
//            common.clickOnText("CONFIRM");
//            common.waitForProgressBar();
//            common.waitForSeconds(4);
//        }
//    }
//
//    public void markStudyAsUnfavourite(String study) {
//        WebElement favIcon = driver.findElement(By.xpath("(//a[text()='" + study + "']//ancestor::tr//td)[last()]//a"));
//        String iconStatus = driver.findElement(By.xpath("(//a[text()='" + study + "']//ancestor::tr//td)[last()]//a//span[2]")).getText();
//        if (iconStatus.equalsIgnoreCase("Mark Study Unfavourite")) {
//            favIcon.click();
//            common.waitForProgressBar();
//            common.verifyTextIsPresent("MARK STUDY UNFAVOURITE");
//            common.clickOnText("CONFIRM");
//            common.waitForProgressBar();
//            common.waitForSeconds(4);
//        }
//    }
//
//    public void navigateToHome() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement ele = wait.until(ExpectedConditions.visibilityOf(home_Tab));
//        ele.click();
//        common.waitForSeconds(5);
//    }
//
//    public void selectFirstStudyFromStudyDropdown() {
//        try {
//            Actions act = new Actions(driver);
//            act.moveToElement(studyDropDown).click().perform();
//            common.waitForProgressBar();
//            common.waitForSeconds(10);
//            act.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
//            common.waitForProgressBar();
//            System.out.println("selection done");
//        } catch (Exception e) {
//            System.out.println("failure" + e);
//        }
////        studyDropDown.click();
////        boolean condition = true;
////        while(condition){
////            List<WebElement> studySelection = driver.findElements(By.xpath("//ul/li/div"));
////            if(studySelection.size()==0){
////                common.waitForSeconds(5);
////            }else {
////                condition = false;
////                break;
////            }
////        }
////        List<WebElement> studyList = driver.findElements(By.xpath("//ul/li/div"));
////        for (int i = 0; i <= studyList.size();i++) {
////            studyList.get(3).click();
////            break;
////        }
//    }
//
//    public void verifyCTMSUser(String username) {
//        Boolean editIcon = false;
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            editIcon = wait.until((ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[text()='" + username + "']//ancestor::tr//td[7]//span[text()='Edit Member']"))));
//            if (username.equalsIgnoreCase("Trisha Jain")) {
//                try {
//                    //WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
//                    common.waitForSeconds(2);
//                    String ctmsStatus = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[text()='" + username + "']//ancestor::tr//td[5]//p[text()]")))).getText();
//                    if (ctmsStatus.equalsIgnoreCase("Yes")) {
//                        ReportListeners.logStep("pass", "Trisha Jain is a CTMS user");
//                    } else {
//                        ReportListeners.logStep("fail", "Trisha Jain is not a CTMS user");
//                    }
//                } catch (Exception e) {
//                    ReportListeners.logStep("fail", "CTMS user field is not present due to" + e);
//                    throw e;
//
//                }
//            } else {
//                try {
//                    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                    String ctmsStatus = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[text()='" + username + "']//ancestor::tr//td[5]//p[text()]")))).getText();
//                    if (ctmsStatus.equalsIgnoreCase("No")) {
//                        ReportListeners.logStep("pass", "Mohit Dadhich is not CTMS user");
//                    } else {
//                        ReportListeners.logStep("fail", "Mohit Dadhich is a CTMS user");
//                    }
//                } catch (Exception e) {
//                    ReportListeners.logStep("fail", "CTMS user field is not present due to" + e);
//                    throw e;
//
//                }
//            }
//        } catch (TimeoutException e) {
//            //if(username=="Trisha Jain" && editIcon){
//            if (username == "Trisha Jain") {
//                if (editIcon) {
//                    ReportListeners.logStep("pass", "Edit icon is not visible for CTMS user");
//                } else {
//                    ReportListeners.logStep("fail", "Edit icon is visible for CTMS user");
//                }
//            }
//            if (username == "Mohit Dadhich") {
//                if (!editIcon) {
//                    ReportListeners.logStep("pass", "Edit icon is visible for non CTMS user");
//                } else {
//                    ReportListeners.logStep("fail", "Edit icon is not visible for non CTMS user");
//                }
//            }
//        }
//    }
//
//    public void removeTeamMember(String username) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement crossIcon = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//p[text()='" + username + "']//ancestor::tr//td[8]//a"))));
//        crossIcon.click();
//    }
//
//    public void markAsTeamLead(String username) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ContentLayout---content_layout']//table//p[text()='" + username + "']//ancestor::tr//td[7]//label")));
//        Boolean status = checkbox.isSelected();
//        if (!status) {
//            checkbox.click();
//            common.waitForSeconds(2);
//        }
//    }
//
//    public void unmarkFromTeamLead(String username) {
//        //common.waitForSeconds(2);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ContentLayout---content_layout']//table//p[text()='" + username + "']//ancestor::tr//td[6]//input")));
//        WebElement checkbox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ContentLayout---content_layout']//table//p[text()='" + username + "']//ancestor::tr//td[6]//label")));
//        Boolean status = checkbox.isSelected();
//        if (status) {
//            checkbox2.click();
//        }
//    }
//
//    public void removeTeam(String teamName) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'" + teamName + "')]//ancestor::h3/following-sibling::div//span[text()='REMOVE']")));
//        removeButton.click();
//    }
//
//    public String getStudyCreatedName() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement study = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//strong[contains(text(),'Study:')]"))));
//        return study.getText().substring(7);
//    }
//
//    public void selectFirstTopicFromTopicLibrary() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement topic = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table//span[text()='Select row 2']//ancestor::td/div"))));
//        topic.click();
//    }
//
//    public String getFirstTopicFromTopicLibrary() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement topic = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table//span[text()='Select row 2']//ancestor::tr//td[2]//p"))));
//        return topic.getText();
//    }
//
//    public void navigateToNotifications() {
//        common.waitForSeconds(2);
//        notificationTab.click();
//    }
//
//    public void clickOnToggleBar() {
//        toggleBar.click();
//    }
//
//    public void searchAndVerifyNote(String note) {
//        common.waitForSeconds(2);
//        common.selectAllFromKeyboard(searchNote_textbox);
//        searchNote_textbox.sendKeys(note);
//        common.hitEnterFromKeyboard(searchNote_textbox);
//        common.verifyTextIsPresent(note);
//        common.waitForSeconds(3);
//    }
//
//    public String selectOutOfOfficeFromDate() {
//        String fromDate = common.getSystemDate();
//        outOfOfficeFromDate.sendKeys(Keys.CONTROL + "a");
//        outOfOfficeFromDate.sendKeys(Keys.BACK_SPACE);
//        outOfOfficeFromDate.sendKeys(fromDate);
//        System.out.println("From Date is " + fromDate);
//        return fromDate;
//    }
//
//    public String selectOutOfOfficeToDate() {
//        String toDate = common.getSystemDate();
//        outOfOfficeToDate.sendKeys(Keys.CONTROL + "a");
//        outOfOfficeToDate.sendKeys(Keys.BACK_SPACE);
//        outOfOfficeToDate.sendKeys(toDate);
//        common.clickOnText("SCHEDULE OUT OF OFFICE");
//        System.out.println("To Date is " + toDate);
//        return toDate;
//    }
//
//    public void selectFirstTeamFromSelectUsersDropdown() {
//        Actions act = new Actions(driver);
//        act.moveToElement(selectUsers).click();
//        common.waitForSeconds(1);
//        act.sendKeys(Keys.ARROW_DOWN).perform();
//        common.waitForSeconds(1);
//        act.sendKeys(Keys.ENTER).perform();
//        common.waitForSeconds(1);
//    }
//
//    public void verifyOutOfOfficeIcon() {
//        LocalDate d = LocalDate.now();
//        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("d");
//        String outOfOfficeDate = d.format(outFormatter);
//        String xpath = "(//strong[text()=" + outOfOfficeDate + "]//ancestor::div)[last()-6]//span[contains(@class,'IconWidget')]//*[name()='svg' and @data-owl-icon-name='arrow-circle-o-right']";
//        WebElement outOfOfficeIcon = driver.findElement(By.xpath(xpath));
//        outOfOfficeIcon.click();
//    }
//
//    public String verifyOutOfOfficeDate() {
//        LocalDate d = LocalDate.now();
//        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//        String outOfOfficeDate = d.format(outFormatter);
//        return outOfOfficeDate;
//    }
//
//    public void clickOnCalendarDropdown() {
//        calendarDropdown.click();
//    }
//
//    public void clickSignOutFromDropdown() {
//        signOut.click();
//        common.clickOnText("Sign Out");
//        common.waitForProgressBar();
//    }
//
//    public void selectTeamMembersFromDropdown() {
//        selectTeamMembers.click();
//        common.waitForSeconds(5);
//        selectTeamMembers.sendKeys(Keys.TAB);
//        selectTeamMembers.sendKeys("Trisha Jain");
//        common.waitForProgressBar();
//        common.waitForSeconds(2);
//    }
//
//    public void checkForButton() {
//        int refreshCount = 15;
//        List<WebElement> element = driver.findElements(By.xpath("//span[text()='Add Topic']"));
//        outerloop:
//        for (int i = 0; i < refreshCount; i++) {
//            if (element.size() > 0) {
//                common.verifyButtonIsPresent("Add Topic");
//                break outerloop;
//            } else {
//                driver.navigate().refresh();
//                common.waitForProgressBar();
//                System.out.println("Interation no. " + i);
//                element = driver.findElements(By.xpath("//span[text()='Add Topic']"));
//            }
//        }
//    }
//}
//
//
//
