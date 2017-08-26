import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ExampleTest {
    private WebDriver driver;
    private final String googleUrl = "http://www.google.com";

    public String chromeDriverPath() {
        String path;
        if (SystemUtils.IS_OS_WINDOWS) {
            path = "driver/win32/chromedriver.exe";
        } else if (SystemUtils.IS_OS_MAC) {
            path = "driver/mac/chromedriver";
        } else {
            path = "driver/linux/chromedriver";
        }
        return (new File(path)).getAbsolutePath();
    }

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        driver = new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void Googleにアクセスしタイトルを調べる() {
        driver.get(googleUrl);
        assert title()
        assertThat(driver.getTitle(), containsString("Google"));
    }

    @Test
    public void Googleで検索を行う() throws InterruptedException {
        driver.get(googleUrl);
        driver.findElement(By.name("q")).sendKeys("Selenium" + Keys.RETURN);
        assertThat(driver.getTitle(), containsString("Selenium - Google"));
    }

    @Test(enabled = false)
    public void 常に失敗する() {
        driver.get(googleUrl);
        assertThat(driver.getTitle(), containsString("Gaagle"));
    }
}
