package test;

import core.BaseTest;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

@Features("Selenide + TestNG + Allureのテスト")
public class ExampleTest extends BaseTest {
    private final String googleUrl = "http://www.google.com";

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


    @Stories("スクリーンショットをとる")
    @Test
    public void takeScreenshot() {
        open(googleUrl);

        screenshot("screenshot");
        File png = new File("build/reports/tests/screenshot.png");
        assert png.exists();
    }
}

