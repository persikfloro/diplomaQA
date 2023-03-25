package ru.netology.page;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Main {
    private final SelenideElement buttonPay = $(Selectors.withText("Купить"));
    private final SelenideElement buttonPayCredit = $(Selectors.withText("Купить в кредит"));

    public PaymentCard clickButtonPay() {
        buttonPay.click();
        return new PaymentCard();
    }

    public PaymentCreditCard clickButtonCredit() {
        buttonPayCredit.click();
        return new PaymentCreditCard();
    }
}
