package test;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import core.BaseTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Features("Selenide + TestNG + Allureの常に失敗するテスト")
public class AlwaysFailTest extends BaseTest {
    private final String googleUrl = "http://www.google.com";

    @Stories("常に失敗する")
    @Test
    public void alwaysFail() {
        open(googleUrl);
        $("a[href='//www.google.co.jp/intl/ja/about.html?fg=1']").shouldBe(text("Geegleについて"));
    }
}
