package work.part07;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import work.part07.pages.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.File;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsTests {

    private static final String BASE_URL = "https://slqamsk.github.io/cases/slflights/v01/";
    private static final String STANDARD_USER = "standard_user";
    private static final String STANDARD_PASSWORD = "stand_pass1";
    private static final String TEST_USER_FIO = "Иванов Иван Иванович";

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = "Chrome";
    }

    @BeforeEach
    void setUp() {
        open(BASE_URL);
        getWebDriver().manage().window().maximize();
        sleep(10_000);
    }


    @Test
    @DisplayName("test1 - Успешная регистрация с валидацией ФИО")
    void test1() {

        String testFio = "Иванов Иван Иванович";

        // Валидация ФИО с помощью FioValidator
        boolean isFioValid = FioValidator.containsOnlyRussianLetters(testFio);

        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.login(STANDARD_USER, STANDARD_PASSWORD);
        loginPage.isLoginSuccessful(TEST_USER_FIO);

        // Поиск рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Выбор рейса
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Данные рейса
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");

        // Заполнение формы регистрации
        registrationPage.registration(
                testFio,
                "1234 567890",
                "ivanov@example.com",
                "+7 (123) 456-7890");

        // Если ФИО валидно, вызов метода проверки успешной регистрации
        if (isFioValid) {
            registrationPage.successRegistration();
        }
    }

    @Test
    @DisplayName("test2 - ФИО не заполнено")
    void test2() {

        String testFio = "";

        // Валидация ФИО с помощью FioValidator
        boolean isFioValid = FioValidator.containsOnlyRussianLetters(testFio);

        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.login(STANDARD_USER, STANDARD_PASSWORD);
        loginPage.isLoginSuccessful(TEST_USER_FIO);

        // Поиск рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Выбор рейса
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Данные рейса
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");

        // Заполнение формы регистрации
        registrationPage.registration(
                testFio,
                "1234 567890",
                "ivanov@example.com",
                "+7 (123) 456-7890");

        // Если ФИО не заполнено, появилось сообщение "Пожалуйста, заполните все поля"
        if (isFioValid) {
            registrationPage.verifyErrorFillAllFields();
        }
    }

    @Test
    @DisplayName("test3 - ФИО с цифрами")
    void test3() {

        String testFio = "Иванов 123";

        // Валидация ФИО с помощью FioValidator
        boolean isFioValid = FioValidator.containsOnlyRussianLetters(testFio);

        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.login(STANDARD_USER, STANDARD_PASSWORD);
        loginPage.isLoginSuccessful(TEST_USER_FIO);

        // Поиск рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Выбор рейса
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Данные рейса
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");

        // Заполнение формы регистрации
        registrationPage.registration(
                testFio,
                "1234 567890",
                "ivanov@example.com",
                "+7 (123) 456-7890");

        // Если ФИО содержит цифры, появляется сообщение ""
        if (isFioValid) {
            registrationPage.verifyInvalidFioWithNumbers();
        }
    }

    @Test
    @DisplayName("test4 - ФИО со спецсимволами")
    void test4() {

        String testFio = "Иванов %:?*";

        // Валидация ФИО с помощью FioValidator
        boolean isFioValid = FioValidator.containsOnlyRussianLetters(testFio);

        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.login(STANDARD_USER, STANDARD_PASSWORD);
        loginPage.isLoginSuccessful(TEST_USER_FIO);

        // Поиск рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Выбор рейса
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Данные рейса
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");

        // Заполнение формы регистрации
        registrationPage.registration(
                testFio,
                "1234 567890",
                "ivanov@example.com",
                "+7 (123) 456-7890");

        // Если ФИО содержит цифры, появляется сообщение ""
        if (isFioValid) {
            registrationPage.verifyInvalidFioWithNumbers();
        }
    }

    @Test
    @DisplayName("test5 - Отправка формы с пустыми полями")
    void test5() {
        String emptyFio = "";
        String emptyPassport = "";
        String emptyEmail = "";
        String emptyPhone = "";

        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.login(STANDARD_USER, STANDARD_PASSWORD);
        loginPage.isLoginSuccessful(TEST_USER_FIO);

        // Поиск рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Выбор рейса
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Данные рейса
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");

        // Заполнение формы регистрации с пустыми полями
        registrationPage.registration(emptyFio, emptyPassport, emptyEmail, emptyPhone);

        // Сообщение об ошибке
        registrationPage.verifyErrorFillAllFields();

    }

    @Test
    @DisplayName("test6 - Проверка кнопки Вернуться к найденным рейсам")
    void test6() {
        String emptyFio = "";
        String emptyPassport = "";
        String emptyEmail = "";
        String emptyPhone = "";

        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.login(STANDARD_USER, STANDARD_PASSWORD);
        loginPage.isLoginSuccessful(TEST_USER_FIO);


        // Поиск рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Выбор рейса
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Данные рейса
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");

        // Заполнение формы регистрации с пустыми полями
        registrationPage.registration(emptyFio, emptyPassport, emptyEmail, emptyPhone);

        // Сообщение об ошибке
        registrationPage.verifyErrorFillAllFields();


        // Нажать кнопку "Вернуться к найденным рейсам"
        registrationPage.clickReturnToFlightsButton();

        // Проверить, что открылась страница "Найденные рейсы"
        flightsList.verifyFlightsListPageOpened();


        }

    }



