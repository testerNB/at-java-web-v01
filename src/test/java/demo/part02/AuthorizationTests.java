package demo.part02;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTests {

    @Test
    public void test01LoginSuccess() {
        open("https://slqamsk.github.io/cases/slflights/v01/");

        SelenideElement username = $(By.id("username"));
        SelenideElement password = $(By.id("password"));
        SelenideElement loginButton = $(By.id("loginButton"));

        username.setValue("standard_user");
        password.setValue("stand_pass1");
        loginButton.click();

        $(By.xpath("//*[contains(text(), 'Поиск авиабилетов') or contains(text(), 'Welcome')]"))
                .shouldBe(visible);
    }

    @Test
    public void test02LoginWithWrongPassword() {
        open("https://slqamsk.github.io/cases/slflights/v01/");

        SelenideElement username = $(By.id("username"));
        SelenideElement password = $(By.id("password"));
        SelenideElement loginButton = $(By.id("loginButton"));

        username.setValue("standard_user");
        password.setValue("stand");
        loginButton.click();

        $(By.xpath("//*[contains(text(), 'Неверное имя пользователя или пароль') or contains(text(), 'Invalid')]"))
                .shouldBe(visible);
    }
}