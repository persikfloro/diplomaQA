package ru.netology.test;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.Helper;
import ru.netology.data.SQL;
import ru.netology.data.Status;
import ru.netology.pages.PaymentPage;
import java.sql.SQLException;

public class CreditTest {
    private PaymentPage paymentPage;
    @BeforeEach
    void setUpPage() {
        paymentPage = new PaymentPage();
    }
    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }
    @AfterEach
    void clear() throws SQLException {
        SQL.clear();
    }
    @Test
    @DisplayName("Покупка тура в кредит по данным активной карты")
    public void shouldSuccessfulPaymentApprovedCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageSuccess();
    }
    @Test
    @DisplayName("Покупка тура в кредит по дебетовой заблокированной карте")
    public void shouldUnsuccessfulPaymentDeclinedCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Покупка тура в кредит по данным несуществующей карты")
    public void shouldUnsuccessfulPaymentRandomCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод 1 цифры в поле Номер карты при покупке тура в кредит")
    public void shouldErrorSingleNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomDigit(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 15 цифр в поле Номер карты при покупке тура в кредит")
    public void shouldErrorFifteenNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomFifteenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 17 цифр в поле Номер карты при покупке тура в кредит")
    public void shouldErrorSeventeenNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomSeventeenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод букв на кириллице в поле Номер карты при покупке тура в кредит")
    public void shouldErrorCyrillicLettersNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getCyrillicLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод букв на латинице в поле Номер карты при покупке тура в кредит")
    public void shouldErrorEnglishLettersNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getEnglishLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Номер карты при покупке тура в кредит")
    public void shouldErrorSymbolsNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getSymbolsNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Номер карты при покупке тура в кредит")
    public void shouldErrorEmptyNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getEmpty(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод двух нулей в поле Месяц при покупке тура в кредит")
    public void shouldErrorZeroMonthCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getTwoZero(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 13 в поле Месяц при покупке тура в кредит")
    public void shouldErrorIfNotExistedMonth13Credit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getThirteenMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidDate();
    }

    @Test
    @DisplayName("Ввод букв на латинице в поле Месяц при покупке тура в кредит")
    public void shouldErrorIfInvalidMonthFormatCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getTwoLetters(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 1 цифры в поле Месяц при покупке тура в кредит")
    public void shouldErrorIfInvalidNumberMonthCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getRandomDigit(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Месяц при покупке тура в кредит")
    public void shouldErrorEmptyMonthCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод двух нулей в поле Год при покупке тура в кредит")
    public void shouldErrorZeroYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getTwoZero(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageValidity();
    }

    @Test
    @DisplayName("Ввод истёкшего года в поле Год при покупке тура в кредит")
    public void shouldErrorIfYearMoreThan6Credit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getPastYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageValidity();
    }

    @Test
    @DisplayName("Ввод букв в поле Год при покупке тура в кредит")
    public void shouldErrorLetterYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getTwoLetters(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 1 цифры в поле Год при покупке тура в кредит")
    public void shouldErrorRandomSingleYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getRandomDigit(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле Год при покупке тура в кредит")
    public void shouldErrorEmptyYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getEmpty(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Год при покупке тура в кредит")
    public void shouldErrorSymbolsYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getSymbolYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 1 слова на латинице в поле Владелец при покупке тура в кредит")
    public void shouldErrorSingleWordOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSingleWordOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод 3 слов на латинице в поле Владелец при покупке тура в кредит")
    public void shouldErrorThreeWordOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getThreeWordOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод фамилии и имени на кириллице в поле Владелец при покупке тура в кредит")
    public void shouldErrorIfCyrillicLettersCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getCyrillicOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод цифр в поле Владелец при покупке тура в кредит")
    public void shouldErrorNumberOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getNumbersOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Владелец при покупке тура в кредит")
    public void shouldErrorIfSymbolsOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSymbolOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Пустое поле Владелец при покупке тура в кредит")
    public void shouldErrorIfEmptyOwnerFieldCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEmpty(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввод 1000 символов в поле Владелец при покупке тура в кредит")
    public void shouldErrorIfOwnerOverLimitCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOverLimitLettersOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести 1 цифру в поле CVC при покупке тура в кредит")
    public void shouldErrorOneSymbolCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getRandomDigit());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести 2 цифры в поле CVC при покупке тура в кредит")
    public void shouldErrorTwoSymbolCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getTwoSymbolsCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести 3 нуля в поле CVC при покупке тура в кредит")
    public void shouldErrorZeroCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getZeroCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести буквы в поле CVC при покупке тура в кредит")
    public void shouldErrorLetterCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getLettersCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести специальные символы в поле CVC при покупке тура в кредит")
    public void shouldErrorSymbolsCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getSymbolCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Пустое поле CVC при покупке тура в кредит")
    public void shouldErrorIfEmptyCVCFieldCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getEmpty());
        paymentPage.pushСontinue();
        paymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Покупка тура в кредит по данным активной карты и проверка записи в базе данных")
    void shouldApprovedStatusInDataBaseByPaymentApprovedCardInCredit() throws SQLException {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageSuccess();
        SQL.checkCreditStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Покупка тура в кредит по дебетовой заблокированной карте и проверка записи в базе данных")
    void shouldDeclinedStatusInDataBaseByPaymentDeclinedCardInCredit() throws SQLException {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
        SQL.checkCreditStatus(Status.DECLINED);
    }
}
