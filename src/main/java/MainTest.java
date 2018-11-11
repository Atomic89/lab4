import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.support.ui.*;

public class MainTest {
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        String test = System.getProperty("webdriver.chrome.driver");
        WebDriver driver ;
        System.out.println(test);
        try{
           driver = new ChromeDriver();
        } catch (Exception ex){
            driver = new InternetExplorerDriver();
        }

       // WebDriver driver = new InternetExplorerDriver();

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Ch");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("ch");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());

        //Close the browser
        driver.quit();
    }
}
