import com.sun.jna.platform.win32.*;
import org.openqa.selenium.*;
import org.openqa.selenium.browserlaunchers.locators.SafariLocator;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.support.ui.*;

public class MainTest {
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = null;
        String productName = Advapi32Util.registryGetStringValue(
                WinReg.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\Shell\\Associations\\UrlAssociations\\http\\UserChoice", "ProgId");
        try{
            if(productName.equals("ChromeHTML")){
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                driver = new ChromeDriver(); 
            } else if(productName.equals("FirefoxURL")){
                System.setProperty("webdriver.firefox.driver", "firefoxdriver.exe");
                driver = new FirefoxDriver();
            } else if(productName.equals("IE.HTTP")){
                System.setProperty("webdriver.ie.driver", "iedriver.exe");
                driver = new InternetExplorerDriver();
            }
        } catch (Exception ex){
            driver = new InternetExplorerDriver();
        }

        assert driver != null;
        driver.get("http://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Ch");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("ch");
            }
        });
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }
}
