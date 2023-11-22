package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class TopUpPage {
    @FindBy(linkText = "Пополнение карты")
    private SelenideElement header;
    @FindBy(css = "[data-test-id=amount] input")
    private SelenideElement amountField;
    @FindBy(css = "[data-test-id=from] input")
    private SelenideElement fromField;
    @FindBy(css = "[data-test-id=to] input")
    private SelenideElement toField;
    @FindBy(css = "[ data-test-id=action-transfer] ")
    private SelenideElement transferButton;

    @FindBy(css = "[ data-test-id=error-notification] ")
    private SelenideElement errorTopUp;

    public TopUpPage(){
    header.shouldBe(Condition.visible);
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
