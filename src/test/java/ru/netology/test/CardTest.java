package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataBase;
import ru.netology.data.Helper;
import ru.netology.data.PaymentCard;
import ru.netology.data.PaymentPage;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    @BeforeEach
    void setUpPage() {
        PaymentPage paymentPage = new PaymentPage();
    }
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
        DataBase.clear();
    }

    long numberFromPayment() {
        DataBase database= new DataBase();
        return database.getNumberPaymentCard();
    }

    String statusAfterServer() {
        DataBase database= new DataBase();
        return database.getStatusPaymentCard();
    }

    @Test
    @DisplayName("Покупка тура по дебетовой активной карте")
    public void shouldSuccessfulPaymentApprovedCard() {
        PaymentPage.purchaseByCard();
        PaymentCard.approvedNumberCard();
        PaymentPage.messageSuccess();
    }

    @Test
    @DisplayName("Покупка тура по дебетовой заблокированной карте")
    public void shouldUnsuccessfulPaymentDeclinedCard() {
        PaymentPage.purchaseByCard();
        PaymentCard.declinedNumberCard();
        PaymentPage.messageError();
    }

    @Test
    @DisplayName("Покупка тура по дебетовой несуществующей карте")
    public void shouldErrorRandomNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.randomNumber();
        PaymentPage.messageError();
    }

    @Test
    @DisplayName("Покупка тура по дебетовой карте 0000 0000 0000 0000")
    public void shouldErrorZeroNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.zeroNumber();
        PaymentPage.messageError();
    }

    // Ввод невалидных данных в поле Номер карты

    @Test
    @DisplayName("Оплата картой с номером из одной цифры")
    public void shouldErrorSingleNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.randomSingleNumber();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оплата тура с 15-значным номером карты")
    public void shouldErrorFifteenNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.randomFifteenNumber();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оплата тура с 17-значным номером карты")
    public void shouldErrorSeventeenNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.randomSeventeenNumber();
        PaymentPage.messageError();
    }

    @Test
    @DisplayName("Оплата картой с буквами на кириллице в номере")
    public void shouldErrorCyrillicLettersNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.cyrillicLettersNumber();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Оплата картой с буквами на латинице в номере")
    public void shouldErrorEnglishLettersNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.englishLettersNumber();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Оплата картой со спецсимволами в номере")
    public void shouldErrorSymbolsNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.symbolsNumber();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Оплата картой с пустым номером")
    public void shouldErrorEmptyNumber() {
        PaymentPage.purchaseByCard();
        PaymentCard.emptyNumber();
        PaymentPage.messageRequiredField();
    }

    /* Ввод невалидных данных в поле Месяц
     */
    @Test
    @DisplayName("Ввести два ноля в поле Месяц")
    public void shouldErrorZeroMonth() {
        PaymentPage.purchaseByCard();
        PaymentCard.zeroMonth();
        PaymentPage.messageInvalidDate();
    }

    @Test
    @DisplayName("Ввести 13 в поле Месяц")
    public void shouldErrorIfNotExistedMonth13() {
        PaymentPage.purchaseByCard();
        PaymentCard.thirteenthMonth();
        PaymentPage.messageInvalidDate();
    }

    @Test
    @DisplayName("Ввести в поле Месяц буквы на латинице")
    public void shouldErrorIfInvalidMonthFormat() {
        PaymentPage.purchaseByCard();
        PaymentCard.englishLettersNumberMonth();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести в поле Месяц одну цифру")
    public void shouldErrorIfInvalidNumberMonth() {
        PaymentPage.purchaseByCard();
        PaymentCard.singleNumberMonth();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оставить поле Месяц пустым")
    public void shouldErrorEmptyMonth() {
        PaymentPage.purchaseByCard();
        PaymentCard.emptyMonth();
        PaymentPage.messageRequiredField();
    }

    // 7. Ввод невалидных данных в поле Год

    @Test
    @DisplayName("Оставить поле Год пустым")
    public void shouldErrorEmptyYear() {
        PaymentPage.purchaseByCard();
        PaymentCard.emptyYear();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввести год из одной цифры")
    public void shouldErrorRandomSingleYear() {
        PaymentPage.purchaseByCard();
        PaymentCard.randomSingleYear();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Год старше текущего на шесть лет")
    public void shouldErrorIfYearMoreThan6() {
        PaymentPage.purchaseByCard();
        PaymentCard.yearMoreThanSix();
        PaymentPage.messageValidity();
    }

    @Test
    @DisplayName("Нулевой год")
    public void shouldErrorZeroYear() {
        PaymentPage.purchaseByCard();
        PaymentCard.zeroYear();
        PaymentPage.messageValidity();
    }

    @Test
    @DisplayName("Год из букв")
    public void shouldErrorLetterYear() {
        PaymentPage.purchaseByCard();
        PaymentCard.lettersYear();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Год из специальных символов")
    public void shouldErrorSymbolsYear() {
        PaymentPage.purchaseByCard();
        PaymentCard.symbolYear();
        PaymentPage.messageInvalidFormat();
    }

    // Ввод невалидных данных в поле Владелец
    @Test
    @DisplayName("Оставить поле Владелец пустым")
    public void shouldErrorIfEmptyOwnerField() {
        PaymentPage.purchaseByCard();
        PaymentCard.emptyOwner();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Фамилия и имя на кириллице")
    public void shouldErrorIfCyrillicLetters() {
        PaymentPage.purchaseByCard();
        PaymentCard.cyrillicLettersOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Владелец")
    public void shouldErrorIfSymbolsOwner() {
        PaymentPage.purchaseByCard();
        PaymentCard.symbolsOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод чисел в поле Владелец")
    public void shouldErrorNumberOwner() {
        PaymentPage.purchaseByCard();
        PaymentCard.numberOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 1000 символов в поле Владелец")
    public void shouldErrorIfOwnerOverLimit() {
        PaymentPage.purchaseByCard();
        PaymentCard.overLimitLettersOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод одного имени в поле Владелец")
    public void shouldErrorSingleWordOwner() {
        PaymentPage.purchaseByCard();
        PaymentCard.singleWordOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод трех имён в поле Владелец")
    public void shouldErrorThreeWordOwner() {
        PaymentPage.purchaseByCard();
        PaymentCard.threeWordOwner();
        PaymentPage.messageInvalidFormat();
    }

    // Ввод невалидных данных в поле CVV
    @Test
    @DisplayName("Оставить поле CVC пустым")
    public void shouldErrorIfEmptyCVCField() {
        PaymentPage.purchaseByCard();
        PaymentCard.emptyCVC();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввод в поле CVC одной цифры")
    public void shouldErrorOneSymbolCVC() {
        PaymentPage.purchaseByCard();
        PaymentCard.oneSymbolCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC двух цифр")
    public void shouldErrorTwoSymbolCVC() {
        PaymentPage.purchaseByCard();
        PaymentCard.twoSymbolCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC из трёх нулей")
    public void shouldErrorZeroCVC() {
        PaymentPage.purchaseByCard();
        PaymentCard.zeroCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC букв")
    public void shouldErrorLetterCVC() {
        PaymentPage.purchaseByCard();
        PaymentCard.lettersCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC специальных символов")
    public void shouldErrorSymbolsCVC() {
        PaymentPage.purchaseByCard();
        PaymentCard.symbolsCVC();
        PaymentPage.messageInvalidFormat();
    }
}