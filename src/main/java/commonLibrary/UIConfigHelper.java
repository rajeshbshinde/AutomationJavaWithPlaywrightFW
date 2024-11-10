package commonLibrary;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;

public class UIConfigHelper {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    /**
     * Launches the specified browser type (Chromium, Firefox, WebKit).
     * Written by: Rajesh Shinde
     */
    public void launchBrowser(String browserType, boolean headless) {
        playwright = Playwright.create();
        switch (browserType.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        context = browser.newContext();
        page = context.newPage();
    }

    /**
     * Navigates to a specified URL.
     * Written by: Rajesh Shinde
     */
    public void navigateToURL(String url) {
        page.navigate(url);
    }

    /**
     * Clicks on an element using a selector.
     * Written by: Rajesh Shinde
     */
    public void click(String selector) {
        page.locator(selector).click();
    }

    /**
     * Enters text into an input field specified by a selector.
     * Written by: Rajesh Shinde
     */
    public void enterText(String selector, String text) {
        page.locator(selector).fill(text);
    }

    /**
     * Retrieves text from an element specified by a selector.
     * Written by: Rajesh Shinde
     */
    public String getText(String selector) {
        return page.locator(selector).innerText();
    }

    /**
     * Verifies if an element specified by a selector is visible.
     * Written by: Rajesh Shinde
     */
    public boolean isElementVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    /**
     * Takes a screenshot of the current page and saves it with the given filename.
     * Written by: Rajesh Shinde
     */
    public void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(fileName)).setFullPage(true));
    }

    /**
     * Selects an option from a dropdown by visible text.
     * Written by: Rajesh Shinde
     */
    public void selectOptionByText(String selector, String visibleText) {
        page.locator(selector).selectOption(new SelectOption().setLabel(visibleText));
    }

    /**
     * Selects an option from a dropdown by value.
     * Written by: Rajesh Shinde
     */
    public void selectOptionByValue(String selector, String value) {
        page.locator(selector).selectOption(new SelectOption().setValue(value));
    }

    /**
     * Waits for an element to be visible on the page.
     * Written by: Rajesh Shinde
     */
    public void waitForElementVisible(String selector, int timeoutMillis) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setTimeout(timeoutMillis));
    }

    /**
     * Closes the browser and ends the Playwright session.
     * Written by: Rajesh Shinde
     */
    public void closeBrowser() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    /**
     * Executes a custom JavaScript script on the page.
     * Written by: Rajesh Shinde
     */
    public Object executeJavaScript(String script) {
        return page.evaluate(script);
    }

    /**
     * Retrieves the page title of the current page.
     * Written by: Rajesh Shinde
     */
    public String getPageTitle() {
        return page.title();
    }

    /**
     * Scrolls to an element on the page.
     * Written by: Rajesh Shinde
     */
    public void scrollToElement(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Handles file upload by setting the file input to the specified file path.
     * Written by: Rajesh Shinde
     */
    public void uploadFile(String selector, String filePath) {
        page.locator(selector).setInputFiles(Paths.get(filePath));
    }

    /**
     * Drags an element from one location to another.
     * Written by: Rajesh Shinde
     */
    public void dragAndDrop(String sourceSelector, String targetSelector) {
        page.locator(sourceSelector).dragTo(page.locator(targetSelector));
    }

    /**
     * Retrieves the current URL of the page.
     * Written by: Rajesh Shinde
     */
    public String getCurrentURL() {
        return page.url();
    }
}

