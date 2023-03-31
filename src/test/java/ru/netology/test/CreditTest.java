package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataBase;
import ru.netology.data.PaymentCard;
import ru.netology.data.PaymentPage;
import static com.codeborne.selenide.Selenide.open;

public class CreditTest {
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
    @DisplayName("Покупка тура в кредит по данным активной карты")
    public void shouldSuccessfulPaymentApprovedCardInCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.validNumber();
        PaymentPage.messageSuccess();
    }

    @Test
    @DisplayName("Покупка тура в кредит по дебетовой заблокированной карте")
    public void shouldUnsuccessfulPaymentDeclinedCardInCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.declinedNumberCard();
        PaymentPage.messageError();
    }

    @Test
    @DisplayName("Покупка тура в кредит по данным несуществующей карты")
    public void shouldUnsuccessfulPaymentRandomCardInCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.randomNumber();
        PaymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод 1 цифры в поле Номер карты при покупке тура в кредит")
    public void shouldErrorSingleNumberCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.randomSingleNumber();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 15 цифр в поле Номер карты при покупке тура в кредит")
    public void shouldErrorFifteenNumberCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.randomFifteenNumber();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 17 цифр в поле Номер карты при покупке тура в кредит")
    public void shouldErrorSeventeenNumberCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.randomSeventeenNumber();
        PaymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод букв на кириллице в поле Номер карты при покупке тура в кредит")
    public void shouldErrorCyrillicLettersNumberCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.cyrillicLettersNumber();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод букв на латинице в поле Номер карты при покупке тура в кредит")
    public void shouldErrorEnglishLettersNumberCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.englishLettersNumber();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Номер карты при покупке тура в кредит")
    public void shouldErrorSymbolsNumberCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.symbolsNumber();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Номер карты при покупке тура в кредит")
    public void shouldErrorEmptyNumberCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.emptyNumber();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввод двух нулей в поле Месяц  при покупке тура в кредит")
    public void shouldErrorZeroMonthCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.zeroMonth();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 13 в поле Месяц при покупке тура в кредит")
    public void shouldErrorIfNotExistedMonth13Credit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.thirteenthMonth();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод букв на латинице в поле Месяц при покупке тура в кредит")
    public void shouldErrorIfInvalidMonthFormatCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.englishLettersNumberMonth();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 1 цифры в поле Месяц при покупке тура в кредит")
    public void shouldErrorIfInvalidNumberMonthCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.singleNumberMonth();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Месяц при покупке тура в кредит")
    public void shouldErrorEmptyMonthCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.emptyMonth();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввод двух нулей в поле Год при покупке тура в кредит")
    public void shouldErrorZeroYearCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.zeroYear();
        PaymentPage.messageValidity();
    }

    @Test
    @DisplayName("Ввод истёкшего года в поле Год при покупке тура в кредит")
    public void shouldErrorIfYearMoreThan6Credit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.pastYear();
        PaymentPage.messageValidity();
    }

    @Test
    @DisplayName("Ввод букв в поле Год при покупке тура в кредит")
    public void shouldErrorLetterYearCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.lettersYear();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 1 цифры в поле Год при покупке тура в кредит")
    public void shouldErrorRandomSingleYearCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.randomSingleYear();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Год при покупке тура в кредит")
    public void shouldErrorEmptyYearCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.emptyYear();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Год при покупке тура в кредит")
    public void shouldErrorSymbolsYearCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.symbolYear();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 1 слова на латинице в поле Владелец при покупке тура в кредит")
    public void shouldErrorSingleWordOwnerCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.singleWordOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 3 слов на латинице в поле Владелец при покупке тура в кредит")
    public void shouldErrorThreeWordOwnerCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.threeWordOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод фамилии и имени на кириллице в поле Владелец при покупке тура в кредит")
    public void shouldErrorIfCyrillicLettersCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.cyrillicLettersOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод цифр в поле Владелец при покупке тура в кредит")
    public void shouldErrorNumberOwnerCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.numberOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Владелец при покупке тура в кредит")
    public void shouldErrorIfSymbolsOwnerCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.symbolsOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Владелец при покупке тура в кредит")
    public void shouldErrorIfEmptyOwnerFieldCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.emptyOwner();
        PaymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввод 1000 символов в поле Владелец при покупке тура в кредит")
    public void shouldErrorIfOwnerOverLimitCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.overLimitLettersOwner();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести 1 цифру в поле CVC при покупке тура в кредит")
    public void shouldErrorOneSymbolCVCCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.validNumber();
        PaymentPage.messageSuccess();
    }

    @Test
    @DisplayName("Ввести 2 цифры в поле CVC при покупке тура в кредит")
    public void shouldErrorTwoSymbolCVCCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.twoSymbolCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести 3 нуля в поле CVC при покупке тура в кредит")
    public void shouldErrorZeroCVCCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.zeroCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести буквы в поле CVC при покупке тура в кредит")
    public void shouldErrorLetterCVCCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.lettersCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести специальные символы в поле CVC при покупке тура в кредит")
    public void shouldErrorSymbolsCVCCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.symbolsCVC();
        PaymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле CVC при покупке тура в кредит")
    public void shouldErrorIfEmptyCVCFieldCredit() {
        PaymentPage.buyingOnCredit();
        PaymentCard.emptyCVC();
        PaymentPage.messageRequiredField();
    }
}
