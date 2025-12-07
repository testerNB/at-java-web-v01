package demo.part02;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class SearchDemoTest {
    @Test
    public void testElementSearchMethods() {
        open("https://slqamsk.github.io/demo/search-demo/");

        $(By.id("user-password")).shouldBe(visible);
        $(By.name("user_email")).shouldBe(visible);
        $(By.className("btn")).shouldBe(visible);
        $(By.tagName("h3")).shouldBe(visible);
        $(By.linkText("Регистрация нового пользователя в системе")).shouldBe(visible);
        $(By.partialLinkText("Контакты")).shouldBe(visible);
    }
}