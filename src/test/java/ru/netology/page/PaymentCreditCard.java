package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PaymentCreditCard {

    public PaymentCreditCard() {
        SelenideElement creditCardTitle = $(Selectors.withText("Кредит по данным карты"));
        creditCardTitle.shouldBe(Condition.visible);
    }
}
