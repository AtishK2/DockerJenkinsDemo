import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SampleTest {
    private String testUrl = "http://demo.guru99.com/test/newtours/index.php";
    private String expectedLoginSuccessfulText = "Login Successfully";

    private String landingPageTitle = "Welcome: Mercury Tours";

    private String loggedInPageTitle = "Login: Mercury Tours";
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(testUrl);
        driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void loginTest() {
        WebElement txtUsername = driver.findElement(By.cssSelector("input[name='userName']"));
        txtUsername.sendKeys("mercury");

        WebElement txtPassword = driver.findElement(By.cssSelector("input[name='password']"));
        txtPassword.sendKeys("mercury");
        WebElement btnSubmit = driver.findElement(By.cssSelector("input[name='submit']"));
        btnSubmit.click();
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase(loggedInPageTitle), "Logged in page title is not matching");
        String actualLoginSuccessfulText = driver.findElement(By.cssSelector("table h3")).getText();
        Assert.assertEquals(actualLoginSuccessfulText, expectedLoginSuccessfulText, "Login message is not matching");

    }

    @Test
    public void signOutTest() {
        WebElement txtUsername = driver.findElement(By.cssSelector("input[name='userName']"));
        txtUsername.sendKeys("mercury");

        WebElement txtPassword = driver.findElement(By.cssSelector("input[name='password']"));
        txtPassword.sendKeys("mercury");
        WebElement btnSubmit = driver.findElement(By.cssSelector("input[name='submit']"));
        btnSubmit.click();
        driver.findElement(By.xpath("//a[contains(text(),'SIGN-OFF')]")).click();
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase(landingPageTitle), "Landing page title is not matching");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
