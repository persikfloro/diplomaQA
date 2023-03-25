package ru.netology.test.unit;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.SQL;
import ru.netology.page.Main;
import ru.netology.page.PaymentCard;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    private final SelenideElement errorSpecifiedPeriodCard = $(Selectors.withText("Неверно указан срок действия карты"));
    private SelenideElement errorPeriodCard = $(Selectors.withText("Истёк срок действия карты"));
    private SelenideElement errorEmptyFieldOwner = $(Selectors.withText("Поле обязательно для заполнения"));
    private SelenideElement errorEmptyFieldNumber = $(Selectors.withText("Поле обязательно для заполнения"));
    private SelenideElement errorEmptyFieldMonth = $(Selectors.withText("Поле обязательно для заполнения"));
    private SelenideElement errorEmptyFieldYear = $(Selectors.withText("Поле обязательно для заполнения"));
    private SelenideElement errorEmptyFieldCVC = $(Selectors.withText("Поле обязательно для заполнения"));
    private SelenideElement errorFormat = $(Selectors.withText("Неверный формат"));
    private SelenideElement sendingRequest = $(Selectors.withText("Отправляем запрос в Банк..."));
    private SelenideElement messageSuccess = $(Selectors.withText("Успешно"));
    private SelenideElement messageApprove = $(Selectors.withText("Операция одобрена Банком."));
    private SelenideElement messageError = $(Selectors.withText("Ошибка"));
    private SelenideElement messageDecline = $(Selectors.withText("Ошибка! Банк отказал в проведении операции."));

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openSetup() {
        open("http://localhost:8080");
    }

    @AfterEach
    void clear() {
        SQL.clear();
    }

    long numberFromPayment() {
        SQL sql = new SQL();
        return sql.getNumberPaymentCard();
    }

    String statusAfterServer() {
        SQL sql = new SQL();
        return sql.getStatusPaymentCard();
    }

    public void positiveMessage() {
        messageSuccess.shouldBe(Condition.visible, Duration.ofSeconds(15));
        messageApprove.shouldBe(Condition.visible);
    }

    public void notPositiveMessage() {
        messageSuccess.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        messageApprove.shouldNotBe(Condition.visible);
    }

    public void denialMessage() {
        messageError.shouldBe(Condition.visible, Duration.ofSeconds(15));
        messageDecline.shouldBe(Condition.visible);
    }

    public void notDenialMessage() {
        messageError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        messageDecline.shouldNotBe(Condition.visible);
    }

    public void checkNumberPayment(long initialNumberPayment, int x) {
        long finalNumberPayment = numberFromPayment();
        assertEquals(initialNumberPayment + x, finalNumberPayment);
    }

    PaymentCard choicePaymentCard() {
        Main page = new Main();
        return page.clickButtonPay();
    }

    @Test
    @DisplayName("1. Покупка тура по дебетовой активной карте")
    public void shouldSuccessfulPaymentApprovedCard() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.approvedNumberCard();
        sendingRequest.shouldBe();
        positiveMessage();
        String statusAfterServer = statusAfterServer();
        checkNumberPayment(initialNumberPayment, 1);
        assertEquals("APPROVED", statusAfterServer);
    }

    @Test
    @DisplayName("2. Покупка тура по дебетовой заблокированной карте")
    public void shouldUnsuccessfulPaymentDeclinedCard() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.declinedNumberCard();
        String statusAfterServer = statusAfterServer();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 1);
        assertEquals("DECLINED", statusAfterServer);
    }

    @Test
    @DisplayName("3. Покупка тура по дебетовой несуществующей карте")
    public void shouldErrorRandomNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.randomNumber();
        notPositiveMessage();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("4. Покупка тура по дебетовой карте 0000 0000 0000 0000")
    public void shouldErrorZeroNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.zeroNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    // 5. Ввод невалидных данных в поле Номер карты

    @Test
    @DisplayName("Оплата картой с номером из одной цифры")
    public void shouldErrorSingleNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.randomSingleNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
    @Test
    @DisplayName("Оплата тура с 15-значным номером карты")
    public void shouldErrorFifteenNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.randomFifteenNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Оплата тура с 17-значным номером карты")
    public void shouldErrorSeventeenNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.randomFifteenNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Оплата картой с буквами на кириллице в номере")
    public void shouldErrorCyrillicLettersNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.cyrillicLettersNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
    @Test
    @DisplayName("Оплата картой с буквами на латинице в номере")
    public void shouldErrorEnglishLettersNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.englishLettersNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Оплата картой со спецсимволами в номере")
    public void shouldErrorSymbolsNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.symbolsNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
    @Test
    @DisplayName("Оплата картой с пустым номером")
    public void shouldErrorEmptyNumber() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.emptyNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldNumber.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    /* 6. Ввод невалидных данных в поле Месяц
    Ввод в поле двух нолей
    Ввод в поле двузначного числа
    Ввод в поле букв на латинице
    Ввод в поле 1 цифры
    Оставить поле пустым
     */
    @Test
    @DisplayName("Ввести два ноля в поле Месяц")
    public void shouldErrorZeroMonth() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.zeroMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorSpecifiedPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввести 13 в поле Месяц")
    public void shouldErrorIfNotExistedMonth13() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.thirteenthMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorSpecifiedPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввести в поле Месяц буквы на латинице")
    public void shouldErrorIfInvalidMonthFormat() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.englishLettersNumberMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
    @Test
    @DisplayName("Ввести в поле Месяц одну цифру")
    public void shouldErrorIfInvalidNumberMonth() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.singleNumberMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
    @Test
    @DisplayName("Оставить поле Месяц пустым")
    public void shouldErrorEmptyMonth() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.emptyMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldMonth.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    // 7. Ввод невалидных данных в поле Год

    @Test
    @DisplayName("Оставить поле Год пустым")
    public void shouldErrorEmptyYear() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.emptyYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldYear.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввести год из одной цифры")
    public void shouldErrorRandomSingleYear() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.randomSingleYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Год старше текущего на шесть лет")
    public void shouldErrorIfYearMoreThan6() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.yearMoreThanSix();
        sendingRequest.shouldNotBe(Condition.visible);
        errorSpecifiedPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Нулевой год")
    public void shouldErrorZeroYear() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.zeroYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Год из букв")
    public void shouldErrorLetterYear() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.lettersYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Год из специальных символов")
    public void shouldErrorSymbolsYear() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.symbolYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    // Ввод невалидных данных в поле Владелец
    @Test
    @DisplayName("Оставить поле Владелец пустым")
    public void shouldErrorIfEmptyOwnerField() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.emptyOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldOwner.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Фамилия и имя на кириллице")
    public void shouldErrorIfCyrillicLetters() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.cyrillicLettersOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Владелец")
    public void shouldErrorIfSymbolsOwner() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.symbolsOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод чисел в поле Владелец")
    public void shouldErrorNumberOwner() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.numberOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Quantity symbols owner")
    public void shouldErrorIfOwnerOverLimit() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.overLimitLettersOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод одного имени в поле Владелец")
    public void shouldErrorSingleWordOwner() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.singleWordOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод трех имён в поле Владелец")
    public void shouldErrorThreeWordOwner() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.threeWordOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    // Ввод невалидных данных в поле CVV
    @Test
    @DisplayName("Оставить поле CVC пустым")
    public void shouldErrorIfEmptyCVCField() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.emptyCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldCVC.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод в поле CVC одной цифры")
    public void shouldErrorOneSymbolCVC() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.oneSymbolCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод в поле CVC двух цифр")
    public void shouldErrorTwoSymbolCVC() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.twoSymbolCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод в поле CVC из нулей")
    public void shouldErrorZeroCVC() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.zeroCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод в поле CVC букв")
    public void shouldErrorLetterCVC() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.lettersCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Ввод в поле CVC специальных символов")
    public void shouldErrorSymbolsCVC() {
        long initialNumberPayment = numberFromPayment();
        var card = choicePaymentCard();
        card.symbolsCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
}
