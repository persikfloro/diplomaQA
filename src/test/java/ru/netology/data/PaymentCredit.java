package ru.netology.data;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class PaymentCredit {
    public Status status;
    public PaymentCredit() {
        SelenideElement creditCardTitle = $(Selectors.withText("Кредит по данным карты"));
        creditCardTitle.shouldBe(Condition.visible);
    }
}
