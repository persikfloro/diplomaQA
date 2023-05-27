package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.Helper;
import ru.netology.data.SQL;
import ru.netology.pages.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    void clear() {
        SQL.clear();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour on credit according to an active card")
    public void shouldSuccessfulPaymentApprovedCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageSuccess();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour on credit with a debit blocked card")
    public void shouldUnsuccessfulPaymentDeclinedCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour on credit using a non-existent card")
    public void shouldUnsuccessfulPaymentRandomCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering 1 digit in the Card number buying a tour on credit")
    public void shouldErrorSingleNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomDigit(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering 15 digits in the Card number buying a tour on credit")
    public void shouldErrorFifteenNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomFifteenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering 17 digits in the Card number buying a tour on credit")
    public void shouldErrorSeventeenNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getRandomSeventeenNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering cyrillic letters in the Card number buying a tour on credit")
    public void shouldErrorCyrillicLettersNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getCyrillicLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering english letters in the Card number buying a tour on credit")
    public void shouldErrorEnglishLettersNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getEnglishLettersNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Entering special characters in the Card number buying a tour on credit")
    public void shouldErrorSymbolsNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getSymbolsNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Empty Card Number buying a tour on credit")
    public void shouldErrorEmptyNumberCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getEmpty(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Zero Month buying a tour on credit")
    public void shouldErrorZeroMonthCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getTwoZero(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter 13 in the Month buying a tour on credit")
    public void shouldErrorIfNotExistedMonth13Credit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getThirteenMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidDate();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter english letters in the Month buying a tour on credit")
    public void shouldErrorIfInvalidMonthFormatCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getTwoLetters(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter one-digit in the Month buying a tour on credit")
    public void shouldErrorIfInvalidNumberMonthCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getRandomDigit(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @Test
    @DisplayName("Empty Month buying a tour on credit")
    @SneakyThrows
    public void shouldErrorEmptyMonthCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getEmpty(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Zero Month buying a tour on credit")
    public void shouldErrorZeroYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getTwoZero(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageValidity();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter the past year in the Year buying a tour on credit")
    public void shouldErrorIfYearMoreThan6Credit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getPastYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageValidity();
    }

    @SneakyThrows
    @Test
    @DisplayName("Letters Year buying a tour on credit")
    public void shouldErrorLetterYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getTwoLetters(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter one-digit in the Year buying a tour on credit")
    public void shouldErrorRandomSingleYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getRandomDigit(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Empty Year buying a tour on credit")
    public void shouldErrorEmptyYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getEmpty(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter special characters in the Year buying a tour on credit")
    public void shouldErrorSymbolsYearCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getSymbolYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter one name in the Owner buying a tour on credit")
    public void shouldErrorSingleWordOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSingleWordOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter three names in the Owner buying a tour on credit")
    public void shouldErrorThreeWordOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getThreeWordOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Cyrillic surname and name in the Owner buying a tour on credit")
    public void shouldErrorIfCyrillicLettersCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getCyrillicOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter numbers in the Owner buying a tour on credit")
    public void shouldErrorNumberOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getNumbersOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter special characters in the Owner buying a tour on credit")
    public void shouldErrorIfSymbolsOwnerCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getSymbolOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
    }

    @SneakyThrows
    @Test
    @DisplayName("Empty Owner buying a tour on credit")
    public void shouldErrorIfEmptyOwnerFieldCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEmpty(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageRequiredField();
    }

    @SneakyThrows
    @Test
    @DisplayName("Very long owner name buying a tour on credit")
    public void shouldErrorIfOwnerOverLimitCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOverLimitLettersOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter a one-digit CVC buying a tour on credit")
    public void shouldErrorOneSymbolCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getRandomDigit());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter two digits CVC buying a tour on credit")
    public void shouldErrorTwoSymbolCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getTwoSymbolsCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Zero CVC buying a tour on credit")
    public void shouldErrorZeroCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getZeroCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Letters CVC buying a tour on credit")
    public void shouldErrorLetterCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getLettersCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Enter special characters in CVC buying a tour on credit")
    public void shouldErrorSymbolsCVCCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getSymbolCVC());
        paymentPage.pushContinue();
        paymentPage.messageInvalidFormat();
    }

    @SneakyThrows
    @Test
    @DisplayName("Empty CVC buying a tour on credit")
    public void shouldErrorIfEmptyCVCFieldCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getEmpty());
        paymentPage.pushContinue();
        paymentPage.messageRequiredField();
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour on credit according to the active card and checking the status in the database")
    void shouldApprovedStatusInDataBaseByPaymentApprovedCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getApprovedCardNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageSuccess();
        assertEquals("APPROVED", SQL.checkCreditStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Buying a tour on credit with a debit blocked card and checking the status in the database")
    void shouldDeclinedStatusInDataBaseByPaymentDeclinedCardInCredit() {
        paymentPage.buyInCredit();
        paymentPage.setCardDetails(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getOwner(), Helper.getCVC());
        paymentPage.pushContinue();
        paymentPage.messageError();
        assertEquals("DECLINED", SQL.checkCreditStatus());
    }
}
