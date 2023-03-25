package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Helper;

import static com.codeborne.selenide.Selenide.$;

public class PaymentCard {
    private SelenideElement cardTitle = $(Selectors.withText("Оплата по карте"));
    private SelenideElement buttonContinue = $(Selectors.withText("Продолжить"));
    private SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement cardMonth = $("[placeholder=\"08\"]");
    private SelenideElement cardYear = $("[placeholder=\"22\"]");
    private SelenideElement cardOwner = $("div:nth-child(3) span:nth-child(1) span.input__box input");
    private SelenideElement cardCVC = $("[placeholder=\"999\"]");

    public PaymentCard() {
        cardTitle.shouldBe(Condition.visible);
    }

    // ВАЛИДНЫЕ ДАННЫЕ

    public void validNumber() {
        cardNumber.setValue(Helper.getApprovedNumber());
    }

    public void validOwner() {
        cardOwner.setValue(Helper.getEnglishOwner());
    }

    public void validMonth() {
        cardMonth.setValue(Helper.getMonth());
    }

    public void validYear() {
        cardYear.setValue(Helper.getYear());
    }


    public void validCVC() {
        cardCVC.setValue(Helper.getCVC());
    }

    public void approvedNumberCard() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    // НЕВАЛИДНЫЕ ДАННЫЕ
    // Номер карты
    public void declinedNumberCard() {
        cardNumber.setValue(Helper.getDeclinedNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void emptyNumber() {
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void randomNumber() {
        cardNumber.setValue(Helper.getRandomNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void zeroNumber() {
        cardNumber.setValue(Helper.getZeroNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void randomSingleNumber() {
        cardNumber.setValue(Helper.getRandomSingleNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void randomFifteenNumber() {
        cardNumber.setValue(Helper.getRandomFifteenNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void randomSeventeenNumber() {
        cardNumber.setValue(Helper.getRandomSeventeenNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void englishLettersNumber() {
        cardNumber.setValue(Helper.getEnglishLettersNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void cyrillicLettersNumber() {
        cardNumber.setValue(Helper.getCyrillicLettersNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void symbolsNumber() {
        cardNumber.setValue(Helper.getSymbolsNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    // Владелец
    public void emptyOwner() {
        validNumber();
        validMonth();
        validYear();
        validCVC();
        buttonContinue.click();
    }

    public void overLimitLettersOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getOverLimitLettersOwner(String.valueOf(65)));
        validCVC();
        buttonContinue.click();
    }

    public void singleWordOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getSingleWordOwner());
        validCVC();
        buttonContinue.click();
    }

    public void cyrillicLettersOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getCyrillicOwner());
        validCVC();
        buttonContinue.click();
    }
    public void numberOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getNumbersOwner());
        validCVC();
        buttonContinue.click();
    }

    public void symbolsOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getSymbolOwner());
        validCVC();
        buttonContinue.click();
    }

    public void threeWordOwner(){
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getThreeWordOwner());
        validCVC();
        buttonContinue.click();

    }

    // Месяц
    public void emptyMonth() {
        validNumber();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void zeroMonth() {
        validNumber();
        cardMonth.setValue(Helper.getZeroMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void thirteenthMonth() {
        validNumber();
        cardMonth.setValue(Helper.getThirteenMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void singleNumberMonth() {
        validNumber();
        cardMonth.setValue(Helper.getRandomSingleNumberMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void englishLettersNumberMonth(){
        validNumber();
        cardMonth.setValue(Helper.getEnglishLettersNumberMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();

    }

    // Год

    public void emptyYear() {
        validNumber();
        validMonth();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void yearMoreThanSix() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getMoreThanSixYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void pastYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getPastYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void zeroYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getZeroYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void randomSingleYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getRandomSingleYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void lettersYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getLettersYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void symbolYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getSymbolYear());
        validOwner();
        validCVC();
        buttonContinue.click();

    }

    //CVC

    public void zeroCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getZeroCVC());
        buttonContinue.click();
    }

    public void lettersCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getLettersCVC());
        buttonContinue.click();
    }

    public void symbolsCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getSymbolCVC());
        buttonContinue.click();
    }

    public void emptyCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        buttonContinue.click();
    }

    public void oneSymbolCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getOneSymbolCVC());
        buttonContinue.click();
    }

    public void twoSymbolCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getTwoSymbolsCVC());
        buttonContinue.click();
    }


}
