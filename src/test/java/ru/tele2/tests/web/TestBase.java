package ru.tele2.tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import ru.tele2.helpers.Attach;
import ru.tele2.pages.LoginPage;
import ru.tele2.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.tele2.pages.TariffPage;

public class TestBase {
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    TariffPage tariffPage = new TariffPage();

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl =  System.getProperty("base_url", "https://tele2.ru/");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000;
        Configuration.remote =  System.getProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud") +"/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
