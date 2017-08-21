import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
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
        System.setProperty("selenide.browser", "Chrome");
    }

    @After
    public void teardown() {
        close();
    }

    @Test
    public void Googleにアクセスしタイトルを調べる() {
        open(googleUrl);
        assertThat(title(), containsString("Google"));
    }

    @Test
    public void Googleで検索を行う() throws InterruptedException {
        open(googleUrl);
        $("[name='q'").setValue("Selenium").sendKeys(Keys.RETURN);
        assertThat(title(), containsString("Selenium - Google"));
    }

    @Test
    public void 常に失敗する() {
        open(googleUrl);
        assertThat(title(), containsString("Gaagle"));
    }

    @Test
    public void スクリーンショットを取る() {
        open(googleUrl);

        screenshot("screenshot");
        File png = new File("build/reports/tests/screenshot.png");
        assertThat(png.exists(), equalTo(true));
    }
}
