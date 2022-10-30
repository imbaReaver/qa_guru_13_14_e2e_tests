package guru.qa.tests;

import guru.qa.helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class MainPageTests extends TestBase {
    @Test
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://e-legion.ru'", () ->
                open("https://e-legion.ru"));

        step("Page title should have text 'e-legion — разработка мобильных приложений на заказ. Создание приложений для iPhone, iPad, Android и Windows'", () -> {
            String expectedTitle = "e-legion — разработка мобильных приложений на заказ. Создание приложений для iPhone, iPad, Android и Windows";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://e-legion.ru'", () ->
                open("https://e-legion.ru"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @DisplayName("Project modal form should appear on main page")
    void projectModalFormTest() {
        step("Open url 'https://e-legion.ru'", () ->
                open("https://e-legion.ru"));

        step("Click on top '+' button", () ->
                $(".top__buttons_plus .pulse-button").click()
        );

        step("Project modal form should be visible", () ->
                $(".request .container").shouldBe(visible)
        );
    }

    @Test
    @DisplayName("Phone number in footer should be +7 812 200 95 09")
    void phoneNumberTest() {
        step("Open url 'https://e-legion.ru'", () ->
                open("https://e-legion.ru"));

        step("Phone number in footer should be +7 812 200 95 09", () ->
                $(".footer__container .footer__contacts").shouldHave(text("+7 812 200 95 09"))
        );
    }

    @Test
    @DisplayName("Accordion in career page should have text")
    void careerAccordionTest() {
        step("Open url 'https://e-legion.ru'", () ->
                open("https://e-legion.ru"));

        step("Click to career button", () ->
                $(".header__mmenu").$("[href='/about/career/']").click()
        );
        step("Click to accordion", () ->
                $(".not-all-the-same__accordion").$(byText("Эффективность")).click()
        );
        step("Accordion should have text", () ->
                $(".accordion__item.active .accordion__item_body")
                        .shouldHave(text("Непрерывно анализируем и оптимизируем процессы"))
        );
    }

    @Test
    @DisplayName("Old site version should be exist")
    void oldSiteVersionTest() {
        step("Open url 'https://e-legion.ru'", () ->
                open("https://e-legion.ru"));

        step("Click to old site version button", () ->
                $(".header__mmenu_lang").click()
        );
        step("Url should be https://old.e-legion.ru/", () ->
                webdriver().shouldHave(url("https://old.e-legion.ru/"))
        );
    }

}