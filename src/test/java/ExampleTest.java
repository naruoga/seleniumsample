import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Features("Selenide + TestNG + Allureのテスト")
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

    @Stories("Googleにアクセスしタイトルを調べる")
    @Test
    public void testTitle() {
        open(googleUrl);
        assert title().contains("Google");
    }

    @Stories("Googleで検索を行う")
    @Test
    public void searchOnGoogle() throws InterruptedException {
        open(googleUrl);
        $("[name='q'").setValue("Selenium").sendKeys(Keys.RETURN);
        assert title().contains("Selenium - Google");
    }

    @Stories("常に失敗する")
    @Test(enabled = false)
    public void alwaysFail() {
        open(googleUrl);
        $("a[href='//www.google.co.jp/intl/ja/about.html?fg=1']").shouldBe(text("Geegleについて"));
    }

    @Stories("スクリーンショットをとる")
    @Test
    public void takeScreenshot() {
        open(googleUrl);

        screenshot("screenshot");
        File png = new File("build/reports/tests/screenshot.png");
        assert png.exists();
    }
}
