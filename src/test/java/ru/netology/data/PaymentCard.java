package ru.netology.data;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class PaymentCard {
    private SelenideElement cardTitle = $(Selectors.withText("Оплата по карте"));
    private static SelenideElement buttonContinue = $(Selectors.withText("Продолжить"));
    private static SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private static SelenideElement cardMonth = $("[placeholder=\"08\"]");
    private static SelenideElement cardYear = $("[placeholder=\"22\"]");
    private static SelenideElement cardOwner = $("div:nth-child(3) span:nth-child(1) span.input__box input");
    private static SelenideElement cardCVC = $("[placeholder=\"999\"]");

    public PaymentCard() {
        cardTitle.shouldBe(Condition.visible);
    }

    // ВАЛИДНЫЕ ДАННЫЕ

    public static void validNumber() {
        cardNumber.setValue(Helper.getApprovedNumber());
    }

    public static void validMonth() {
        cardMonth.setValue(Helper.getMonth());
    }

    public static void validYear() {
        cardYear.setValue(Helper.getYear());
    }

    public static void validOwner() {
        cardOwner.setValue(Helper.getEnglishOwner());
    }


    public static void validCVC() {
        cardCVC.setValue(Helper.getCVC());
    }

    public static void approvedNumberCard() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    // НЕВАЛИДНЫЕ ДАННЫЕ
    // Номер карты
    public static void declinedNumberCard() {
        cardNumber.setValue(Helper.getDeclinedNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void emptyNumber() {
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void randomNumber() {
        cardNumber.setValue(Helper.getRandomNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void zeroNumber() {
        cardNumber.setValue(Helper.getZeroNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void randomSingleNumber() {
        cardNumber.setValue(Helper.getRandomSingleNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void randomFifteenNumber() {
        cardNumber.setValue(Helper.getRandomFifteenNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void randomSeventeenNumber() {
        cardNumber.setValue(Helper.getRandomSeventeenNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void englishLettersNumber() {
        cardNumber.setValue(Helper.getEnglishLettersNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void cyrillicLettersNumber() {
        cardNumber.setValue(Helper.getCyrillicLettersNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void symbolsNumber() {
        cardNumber.setValue(Helper.getSymbolsNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    // Владелец
    public static void emptyOwner() {
        validNumber();
        validMonth();
        validYear();
        validCVC();
        buttonContinue.click();
    }

    public static void overLimitLettersOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getOverLimitLettersOwner(String.valueOf(1000)));
        validCVC();
        buttonContinue.click();
    }

    public static void singleWordOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getSingleWordOwner());
        validCVC();
        buttonContinue.click();
    }

    public static void cyrillicLettersOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getCyrillicOwner());
        validCVC();
        buttonContinue.click();
    }
    public static void numberOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getNumbersOwner());
        validCVC();
        buttonContinue.click();
    }

    public static void symbolsOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getSymbolOwner());
        validCVC();
        buttonContinue.click();
    }

    public static void threeWordOwner(){
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(Helper.getThreeWordOwner());
        validCVC();
        buttonContinue.click();

    }

    // Месяц
    public static void emptyMonth() {
        validNumber();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void zeroMonth() {
        validNumber();
        cardMonth.setValue(Helper.getZeroMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void thirteenthMonth() {
        validNumber();
        cardMonth.setValue(Helper.getThirteenMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void singleNumberMonth() {
        validNumber();
        cardMonth.setValue(Helper.getRandomSingleNumberMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void englishLettersNumberMonth(){
        validNumber();
        cardMonth.setValue(Helper.getEnglishLettersNumberMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();

    }

    // Год

    public static void emptyYear() {
        validNumber();
        validMonth();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void yearMoreThanSix() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getMoreThanSixYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void pastYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getPastYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void zeroYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getZeroYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void randomSingleYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getRandomSingleYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void lettersYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getLettersYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public static void symbolYear() {
        validNumber();
        validMonth();
        cardYear.setValue(Helper.getSymbolYear());
        validOwner();
        validCVC();
        buttonContinue.click();

    }

    //CVC

    public static void zeroCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getZeroCVC());
        buttonContinue.click();
    }

    public static void lettersCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getLettersCVC());
        buttonContinue.click();
    }

    public static void symbolsCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getSymbolCVC());
        buttonContinue.click();
    }

    public static void emptyCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        buttonContinue.click();
    }

    public static void oneSymbolCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getOneSymbolCVC());
        buttonContinue.click();
    }

    public static void twoSymbolCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(Helper.getTwoSymbolsCVC());
        buttonContinue.click();
    }
}
