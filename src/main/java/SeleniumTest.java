import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.google.com");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Tout accepter')]")));
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='QS5gu sy4vM']")));
            acceptCookiesButton.click();

            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
            searchBox.sendKeys("automatisation des tests logiciels");
            searchBox.submit();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));

            List<WebElement> searchResults = driver.findElements(By.cssSelector("div#search h3"));
            boolean termFound = searchResults.stream()
                    .anyMatch(element -> element.getText().toLowerCase().contains("automatisation des tests logiciels"));

            if (termFound) {
                System.out.println("Le terme de recherche 'automatisation des tests logiciels' a été trouvé dans les résultats.");
            } else {
                System.out.println("Le terme de recherche 'automatisation des tests logiciels' n'a pas été trouvé dans les résultats.");
            }
        } finally {
            driver.quit();
        }
    }
}
