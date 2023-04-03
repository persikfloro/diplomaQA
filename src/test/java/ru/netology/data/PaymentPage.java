package ru.netology.data;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import java.util.List;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    public Status status;
    private final SelenideElement buttonPay = $(Selectors.withText("Купить"));
    private final SelenideElement buttonPayCredit = $(Selectors.withText("Купить в кредит"));

    public static void purchaseByCard() {
        $$(".button__content").find(exactText("Купить")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Оплата по карте")).shouldBe(visible);
    }

    public static void buyingOnCredit() {
        $$(".button__content").find(exactText("Купить в кредит")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Кредит по данным карты")).shouldBe(visible);
    }

    public static void messageSuccess() {
        $$(".notification__title").find(exactText("Успешно")).shouldHave(visible, Duration.ofSeconds(15));
    }
    public static void messageError() {
        $$(".notification__title").find(exactText("Ошибка")).shouldHave(visible, Duration.ofSeconds(15));
    }
    public static void messageInvalidFormat() {
        $$(".input__sub").find(exactText("Неверный формат")).shouldBe(visible);
    }
    public static void messageInvalidDate() {
        $$(".input__sub").find(exactText("Неверно указан срок действия карты")).shouldBe(visible);
    }
    public static void messageValidity() {
        $$(".input__sub").find(exactText("Истёк срок действия карты")).shouldBe(visible);
    }
    public static void messageRequiredField() {
        $$(".input__sub").find(exactText("Поле обязательно для заполнения")).shouldBe(visible);
    }
    public static void messageSendingRequest() {
        $$(".input__sub").find(exactText("Отправляем запрос в Банк...")).shouldBe(visible);
    }
    public static void messageApprove() {
        $$(".input__sub").find(exactText("Операция одобрена Банком.")).shouldBe(visible);
    }
    public static void messageDecline() {
        $$(".input__sub").find(exactText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible);
    }

    // public static void pushContinue() {
        // $$(".button__content").find(exactText("Продолжить")).click();
}
