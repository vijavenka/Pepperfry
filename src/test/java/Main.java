import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.pepperfry.com/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,60);

        driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#webklipper-publisher-widget-container-notification-close-div.close"))).isDisplayed())
            driver.findElement(By.cssSelector("div#webklipper-publisher-widget-container-notification-close-div.close")).click();
        driver.switchTo().defaultContent();

        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#reg_login_box #regPopUp a.popup-close"))).isDisplayed())
            driver.findElement(By.cssSelector("#reg_login_box #regPopUp a.popup-close")).click();

        driver.findElement(By.id("search")).sendKeys("TV Stand");
        driver.findElement(By.cssSelector("input#searchButton")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("span.clipCard__price-offer"));
        Set<Integer> results = new TreeSet<Integer>().descendingSet();
        for (WebElement ele:elements
             ) {
            String[] resultStr = ele.getText().split(" ");
            results.add(Integer.valueOf(resultStr[1].replaceAll(",","")));
        }
        results.stream().forEach(System.out::println);

        driver.quit();
    }
}

