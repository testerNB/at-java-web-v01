package work.part01;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Day1 {
    @Test
    void test1() {
        open("https://slqa.ru/cases/SimpleForm/");
        $("body").shouldHave(text("Томас Фуллер"));
    }
}
