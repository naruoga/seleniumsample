import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ExampleTest {
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
        System.setProperty("selenide.browser", "Chrome");
    }

    @AfterTest
    public void teardown() {
        close();
    }

    @Test
    public void Googleにアクセスしタイトルを調べる() {
        open(googleUrl);
        assert title().contains("Google");
    }

    @Test
    public void Googleで検索を行う() throws InterruptedException {
        open(googleUrl);
        $("[name='q'").setValue("Selenium").sendKeys(Keys.RETURN);
        assert title().contains("Selenium - Google");
    }

    @Test(enabled = false)
    public void 常に失敗する() {
        open(googleUrl);
        $("a[href='//www.google.co.jp/intl/ja/about.html?fg=1']").shouldBe(text("Geegleについて"));
    }

    @Test
    public void スクリーンショットを取る() {
        open(googleUrl);

        screenshot("screenshot");
        File png = new File("build/reports/tests/screenshot.png");
        assert png.exists();
    }
}
