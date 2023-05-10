package ru.netology.test;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.Helper;
import ru.netology.data.SQL;
import ru.netology.data.Status;
import ru.netology.pages.PaymentPage;
import java.sql.SQLException;

public class CardTest {
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
    @DisplayName("Покупка тура по дебетовой активной карте")
    void shouldSuccessfulPaymentApprovedCard() throws SQLException {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageSuccess();
    }
    @Test
    @DisplayName("Покупка тура по дебетовой заблокированной карте")
    public void shouldUnsuccessfulPaymentDeclinedCard() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }
    @Test
    @DisplayName("Покупка тура по дебетовой несуществующей карте")
    public void shouldErrorRandomNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Покупка тура по дебетовой карте 0000 0000 0000 0000")
    public void shouldErrorZeroNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getZeroNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    // Ввод невалидных данных в поле Номер карты

    @Test
    @DisplayName("Оплата картой с номером из одной цифры")
    public void shouldErrorSingleNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomDigit(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оплата тура с 15-значным номером карты")
    public void shouldErrorFifteenNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomFifteenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оплата тура с 17-значным номером карты")
    public void shouldErrorSeventeenNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomSeventeenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Оплата картой с буквами на кириллице в номере")
    public void shouldErrorCyrillicLettersNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getCyrillicLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оплата картой с буквами на латинице в номере")
    public void shouldErrorEnglishLettersNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getEnglishLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оплата картой со спецсимволами в номере")
    public void shouldErrorSymbolsNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getSymbolsNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оплата картой с пустым номером")
    public void shouldErrorEmptyNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getEmpty(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    /* Ввод невалидных данных в поле Месяц
     */
    @Test
    @DisplayName("Ввести два ноля в поле Месяц")
    public void shouldErrorZeroMonth() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getTwoZero(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidDate();
    }

    @Test
    @DisplayName("Ввести 13 в поле Месяц")
    public void shouldErrorIfNotExistedMonth13() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getThirteenMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidDate();
    }

    @Test
    @DisplayName("Ввести в поле Месяц буквы на латинице")
    public void shouldErrorIfInvalidMonthFormat() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getTwoLetters(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести в поле Месяц одну цифру")
    public void shouldErrorIfInvalidNumberMonth() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getRandomDigit(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Оставить поле Месяц пустым")
    public void shouldErrorEmptyMonth() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getEmpty(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    // 7. Ввод невалидных данных в поле Год

    @Test
    @DisplayName("Оставить поле Год пустым")
    public void shouldErrorEmptyYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getEmpty(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввести год из одной цифры")
    public void shouldErrorRandomSingleYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getRandomDigit(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Год старше текущего на пять лет")
    public void shouldErrorIfYearMoreThanFive() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getMoreThanFiveYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidDate();
    }

    @Test
    @DisplayName("Нулевой год")
    public void shouldErrorZeroYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getTwoZero(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageValidity();
    }

    @Test
    @DisplayName("Год из букв")
    public void shouldErrorLetterYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getTwoLetters(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Год из специальных символов")
    public void shouldErrorSymbolsYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getSymbolYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    // Ввод невалидных данных в поле Владелец
    @Test
    @DisplayName("Оставить поле Владелец пустым")
    public void shouldErrorIfEmptyOwnerField() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEmpty(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Фамилия и имя на кириллице")
    public void shouldErrorIfCyrillicLetters() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getCyrillicOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод специальных символов в поле Владелец")
    public void shouldErrorIfSymbolsOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSymbolOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод чисел в поле Владелец")
    public void shouldErrorNumberOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getNumbersOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
    }

    @Test
    @DisplayName("Ввод 1000 символов в поле Владелец")
    public void shouldErrorIfOwnerOverLimit() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOverLimitLettersOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод одного имени в поле Владелец")
    public void shouldErrorSingleWordOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSingleWordOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод трех имён в поле Владелец")
    public void shouldErrorThreeWordOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getThreeWordOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    // Ввод невалидных данных в поле CVV
    @Test
    @DisplayName("Оставить поле CVC пустым")
    public void shouldErrorIfEmptyCVCField() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getEmpty());
        paymentPage.pushСontinue();
        paymentPage.messageRequiredField();
    }

    @Test
    @DisplayName("Ввод в поле CVC одной цифры")
    public void shouldErrorOneSymbolCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getRandomDigit());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC двух цифр")
    public void shouldErrorTwoSymbolCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getTwoSymbolsCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC из трёх нулей")
    public void shouldErrorZeroCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getZeroCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC букв")
    public void shouldErrorLetterCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getLettersCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Ввод в поле CVC специальных символов")
    public void shouldErrorSymbolsCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getSymbolCVC());
        paymentPage.pushСontinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Покупка тура по дебетовой активной карте и проверка записи в базе данных")
    void shouldPayByApprovedCardStatusInDB() throws SQLException {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.approvedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageSuccess();
        SQL.checkPaymentStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Покупка тура по дебетовой заблокированной карте и проверка записи в базе данных")
    void shouldNoPayByDeclinedCardStatusInDB() throws SQLException {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushСontinue();
        paymentPage.messageError();
        SQL.checkPaymentStatus(Status.DECLINED);
    }
}