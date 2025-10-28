package Learning;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;


public class Example {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();

            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page page = context.newPage();
            page.navigate("https://www.amazon.in/");

            page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).click();
            page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).fill("iPhone");
            page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Search Amazon.in")).press("Enter");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go").setExact(true)).click();
            Page page1 = page.waitForPopup(() -> {
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Apple iPhone 13 (128GB) - Midnight")).click();
            });
            page1.navigate("https://www.amazon.in/Apple-iPhone-13-128GB-Midnight/dp/B09G9HD6PD/ref=sr_1_3?crid=1KARC7LT83HM4&dib=eyJ2IjoiMSJ9.8h9A_YSPiLCsRbGj7EQ9tqHsqu1dvOSrBqGMWf6Z2sUL1me6rw8OXNrOi1LBmMiUThhbCv6Pg1X9U1GIZq1NlpIq0rr2MkrjMGS77y5HlDMbFRyZkMIaPGF9Cx5H-dpNnawSM03QnqsTDLjoE7CV2OxgwTcIKyjjwomaTl9I-Uj1IagXMf7k75Os11XYdiZR5Z9lx9vqGcsQEPBblPkUd1zWFldfjIb4iadiep5dmXE.CfPQHjnKMbvHnW7ib0crC0r8MGErRLw6w7AWWeY7-Nk&dib_tag=se&keywords=iPhone&qid=1757696162&sprefix=iphone%2Caps%2C411&sr=8-3&th=1");
            page1.close();

            //page.pause();
            Page page2 = page.waitForPopup(() -> {
                page.locator(".s-widget-container.s-spacing-small.s-widget-container-height-small.celwidget.slot\\=MAIN.template\\=SEARCH_RESULTS.widgetId\\=search-results_5 > span > .puis-card-container > div > div > .puisg-col.puisg-col-4-of-4.puisg-col-4-of-8.puisg-col-4-of-12.puisg-col-4-of-16.puisg-col-4-of-20.puisg-col-4-of-24.puis-list-col-left > .puisg-col-inner > .s-product-image-container > div > .rush-component > .a-link-normal").click();
            });
            page2.navigate("https://www.amazon.in/Apple-iPhone-15-128-GB/dp/B0CHX7NG26/ref=sr_1_5?crid=1KARC7LT83HM4&dib=eyJ2IjoiMSJ9.8h9A_YSPiLCsRbGj7EQ9tqHsqu1dvOSrBqGMWf6Z2sUL1me6rw8OXNrOi1LBmMiUThhbCv6Pg1X9U1GIZq1NlpIq0rr2MkrjMGS77y5HlDMbFRyZkMIaPGF9Cx5H-dpNnawSM03QnqsTDLjoE7CV2OxgwTcIKyjjwomaTl9I-Uj1IagXMf7k75Os11XYdiZR5Z9lx9vqGcsQEPBblPkUd1zWFldfjIb4iadiep5dmXE.CfPQHjnKMbvHnW7ib0crC0r8MGErRLw6w7AWWeY7-Nk&dib_tag=se&keywords=iPhone&qid=1757696162&sprefix=iphone%2Caps%2C411&sr=8-5&th=1");
            page2.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart")).click();

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
        }
    }
}