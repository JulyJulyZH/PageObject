package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TopUpPage {

    private SelenideElement header = $ (byText("Пополнение карты"));
    private SelenideElement amountField = $ ("[data-test-id=amount] input");
    private SelenideElement fromField = $ ("[data-test-id=from] input");
    private SelenideElement toField = $ ("[data-test-id=to] input");
    private SelenideElement transferButton = $("[ data-test-id=action-transfer] ");

    private SelenideElement errorTopUp = $("[ data-test-id=error-notification] ");

    public TopUpPage(){ header.shouldBe(Condition.visible);
    }

    public DashboardPage succesTopUp (String amount, DataHelper.CardInfo cardInfo) {
        fillPage(amount, cardInfo);
        return page(DashboardPage.class);
    }

    public void fillPage(String amount, DataHelper.CardInfo cardInfo) {
        amountField.setValue(amount);
        fromField.setValue(cardInfo.getCardNum());
        transferButton.click();
    }
    public void errorMessage(String errorText) {
        errorTopUp.shouldHave(Condition.text(errorText)).shouldBe(Condition.visible);
    }

}
