package core;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.apache.commons.lang3.SystemUtils;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.close;

public class BaseTest implements IHookable {
    public String chromeDriverPath() {
        String path;
        if (SystemUtils.IS_OS_WINDOWS) {
            path = "../driver/win32/chromedriver.exe";
        } else if (SystemUtils.IS_OS_MAC) {
            path = "../driver/mac/chromedriver";
        } else {
            path = "../driver/linux/chromedriver";
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

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {

        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                screenshot();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(type = "image/png")
    private byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }
}
