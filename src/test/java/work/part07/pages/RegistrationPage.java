package work.part07.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class RegistrationPage {
    SelenideElement
            flightInfo = $("#flightRegistrationInfo"),
            buttonFinishRegistration = $x("//button[contains(.,'Завершить регистрацию')]"),
            fio = $("#passengerName"),
            passport = $("#passportNumber"),
            email = $("#email"),
            phone = $("#phone"),
            message = $("#registrationMessage"),
            returnToFlightsButton = $x("//button[contains(text(), 'Вернуться к найденным рейсам')]");



    @Step("Проверка видимости сообщения об ошибке")
    private boolean isErrorMessageVisible() {
        try {
            message.shouldBe(visible);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Успешная регистрация")
    public void successRegistration() {
        buttonFinishRegistration.click();
        Alert alert = switchTo().alert();
        assertTrue(alert.getText().contains("Бронирование завершено"));
        alert.accept();
        this.message.shouldHave(text("Регистрация успешно завершена!"));
    }

    @Step("Заполнение полей и регистрация")
    public void registration(String fio, String passport, String email, String phone) {
        this.fio.setValue(fio);
        this.passport.setValue(passport);
        this.email.setValue(email);
        this.phone.setValue(phone);
        buttonFinishRegistration.click();
    }

    @Step("Проверка, что данные рейса корректные")
    public void isFlightDataCorrect(String cityFrom, String cityTo) {
        flightInfo
                .shouldBe(visible)
                .shouldHave(text(cityFrom + " → " + cityTo));
    }

    @Step("Ошибка 'Заполните все поля'")
    public void verifyErrorFillAllFields() {
        message.shouldBe(visible);
        message.shouldHave(text("Пожалуйста, заполните все поля."));
    }

    @Step("Ошибка 'ФИО должно содержать только русские буквы, пробелы и дефис")
    public void verifyInvalidFioWithNumbers() {
        sleep(1000);
        message.shouldBe(visible);
        message.shouldHave(text("ФИО должно содержать только русские буквы, пробелы и дефис."));
    }

    @Step("Нажать кнопку 'Вернуться к найденным рейсам'")
    public void clickReturnToFlightsButton() {

        returnToFlightsButton
                .shouldBe(visible)  // кнопка видима
                .shouldBe(enabled)   // кнопка активна
                .click();

        // переход на другую страницу
        returnToFlightsButton.should(disappear);

        sleep(1000);
    }

}




