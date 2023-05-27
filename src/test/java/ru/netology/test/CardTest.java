package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.Helper;
import ru.netology.data.SQL;
import ru.netology.pages.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    static void clear() {
        SQL.clear();
    }


    @SneakyThrows
    @Test
    @DisplayName("Buying a tour with a debit active card")
    void shouldSuccessfulPaymentApprovedCard() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageSuccess();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour with a debit blocked card")
    public void shouldUnsuccessfulPaymentDeclinedCard() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour with a non-existent debit card")
    public void shouldErrorRandomNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour with a debit card 0000 0000 0000 0000")
    public void shouldErrorZeroNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getZeroNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    // Ввод невалидных данных в поле Номер карты

    @SneakyThrows
    @Test
    @DisplayName("Payment by card with a number of one digit")
    public void shouldErrorSingleNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomDigit(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Payment for the tour with a 15-digit card number")
    public void shouldErrorFifteenNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomFifteenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Payment for the tour with a 17-digit card number")
    public void shouldErrorSeventeenNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getRandomSeventeenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Payment by card with a number of cyrillic letters")
    public void shouldErrorCyrillicLettersNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getCyrillicLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Payment by card with a number of english letters")
    public void shouldErrorEnglishLettersNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getEnglishLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Payment by card with a number of special characters")
    public void shouldErrorSymbolsNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getSymbolsNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Leave the card number field empty")
    public void shouldErrorEmptyNumber() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getEmpty(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    /* Ввод невалидных данных в поле Месяц
     */
    @SneakyThrows
    @Test
    @DisplayName("Enter two zeros in the Month field")
    public void shouldErrorZeroMonth() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getTwoZero(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidDate();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter 13 in the Month")
    public void shouldErrorIfNotExistedMonth13() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getThirteenMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidDate();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter English letters in the Month field")
    public void shouldErrorIfInvalidMonthFormat() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getTwoLetters(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter one number in the Month field")
    public void shouldErrorIfInvalidNumberMonth() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getRandomDigit(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Leave the Month field empty")
    public void shouldErrorEmptyMonth() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getEmpty(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    // 7. Ввод невалидных данных в поле Год

    @SneakyThrows
    @Test
    @DisplayName("Leave the Year field empty")
    public void shouldErrorEmptyYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getEmpty(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter a one-digit year")
    public void shouldErrorRandomSingleYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getRandomDigit(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Year older than the current one by five years")
    public void shouldErrorIfYearMoreThanFive() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getMoreThanFiveYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidDate();
    }

    @SneakyThrows
    @Test
    @DisplayName("Zero Year")
    public void shouldErrorZeroYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getTwoZero(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageValidity();
    }

    @SneakyThrows
    @Test
    @DisplayName("Year of letters")
    public void shouldErrorLetterYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getTwoLetters(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Year from special characters")
    public void shouldErrorSymbolsYear() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getSymbolYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    // Ввод невалидных данных в поле Владелец
    @SneakyThrows
    @Test
    @DisplayName("Empty Owner field")
    public void shouldErrorIfEmptyOwnerField() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEmpty(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageRequiredField();
    }

    @SneakyThrows
    @Test
    @DisplayName("Surname and name in cyrillic")
    public void shouldErrorIfCyrillicLetters() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getCyrillicOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering special characters in the Owner field")
    public void shouldErrorIfSymbolsOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSymbolOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering numbers in the Owner field")
    public void shouldErrorNumberOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getNumbersOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Very long owner name")
    public void shouldErrorIfOwnerOverLimit() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOverLimitLettersOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter one name in the Owner field")
    public void shouldErrorSingleWordOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSingleWordOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter three names in the Owner field")
    public void shouldErrorThreeWordOwner() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getThreeWordOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    // Ввод невалидных данных в поле CVV
    @SneakyThrows
    @Test
    @DisplayName("Empty field CVC")
    public void shouldErrorIfEmptyCVCField() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getEmpty());
        paymentPage.pushContinue();
        paymentPage.messageRequiredField();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter a one-digit CVC")
    public void shouldErrorOneSymbolCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getRandomDigit());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter two digits CVC")
    public void shouldErrorTwoSymbolCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getTwoSymbolsCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Zero CVC")
    public void shouldErrorZeroCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getZeroCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter letters CVC")
    public void shouldErrorLetterCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getLettersCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter special characters in the CVC")
    public void shouldErrorSymbolsCVC() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getSymbolCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour with a debit active card and checking status in the database")
    void shouldPayByApprovedCardStatusInDB() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageSuccess();
        assertEquals("APPROVED", SQL.checkPaymentStatus());
    }


    @SneakyThrows
    @Test
    @DisplayName("Buying a tour with a debit blocked card and checking status in the database")
    void shouldNoPayByDeclinedCardStatusInDB() {
        paymentPage.payByCard();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
        assertEquals("DECLINED", SQL.checkPaymentStatus());
    }
}