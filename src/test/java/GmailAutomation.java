import com.microsoft.playwright.*;

public class GmailAutomation {
    String userName = "your-email@gmail.com";
    String password = "your-password";
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Navigate to Gmail login page
            page.navigate("https://accounts.google.com/signin/v2/identifier");

            // Enter the email and proceed
            page.fill("input[type='email']", "your-email@gmail.com");
            page.click("#identifierNext");

            // Wait for password field and enter the password
            page.waitForSelector("input[type='password']");
            page.fill("input[type='password']", "your-password");
            page.click("#passwordNext");

            // Wait for Gmail to load
            page.waitForURL("https://mail.google.com/mail/u/0/#inbox", new Page.WaitForURLOptions().setTimeout(10000));

            // Compose a new email
            page.click("div[role='button'][gh='cm']"); // Click on 'Compose' button
            page.waitForSelector("textarea[name='to']");

            // Fill in email details
            page.fill("textarea[name='to']", "recipient-email@example.com");  // Recipient
            page.fill("input[name='subjectbox']", "Test Email from Playwright");  // Subject
            page.fill("div[aria-label='Message Body']", "Hello, this is a test email generated by Playwright with Java.");  // Body

            // Send the email
            page.click("div[role='button'][data-tooltip^='Send']");  // Click the 'Send' button

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}