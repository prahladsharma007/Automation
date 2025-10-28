package Learning;

import com.microsoft.playwright.*;

public class PlayWrightLearning {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

//        page.navigate("https://qa.eventexperio.com/");
//
//        page.click("button[class='primary-btn text-[15px]']");
//        Locator textboxLocator = page.locator("#phn-input");
//        textboxLocator.fill("9999999999");
//        page.click("button[type='submit']");
//
//        //page.screenshot();
//
//
//        String title = page.title();
//        System.out.println(title);
//
//        String url = page.url();
//        System.out.println(url);
//
//        page.waitForTimeout(3000);

        page.navigate("https://www.orangehrm.com/");
        //page.locator("text=Contact Sales").nth(2).click();
        page.locator("h5:has-text('Contact Us')").click();
        Locator sales = page.locator("li button:has-text('Contact Sales')");
        for (int i=0; i<sales.count();i++){
            String countryName = sales.nth(i).textContent();
            System.out.println(countryName);
        }



//        page.locator("//input[@value='Start Your 30 Day Free Trial']").first().click();
//        Locator all_Country = page.locator("#Form_getForm_Country option");
//        System.out.println(all_Country.count());
//        //all_Country.click();
//
//        for (int i=0; i<all_Country.count();i++){
//            String countryName = all_Country.nth(i).textContent();
//            System.out.println(countryName);
//        }
        page.waitForTimeout(3000);

        browser.close();
        page.close();
        playwright.close();

    }
}
