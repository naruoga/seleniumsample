import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void Googleにアクセスしタイトルを調べる() {
        driver.get(googleUrl);
        assertThat(driver.getTitle(), containsString("Google"));
    }

    @Test
    public void Googleで検索を行う() throws InterruptedException {
        driver.get(googleUrl);
        driver.findElement(By.name("q")).sendKeys("Selenium" + Keys.RETURN);
        assertThat(driver.getTitle(), containsString("Selenium - Google"));
    }

    @Ignore
    @Test
    public void 常に失敗する() {
        driver.get(googleUrl);
        assertThat(driver.getTitle(), containsString("Gaagle"));
    }
}
