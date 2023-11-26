package ru.netology.web.test;

import com.codeborne.selenide.commands.ToString;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV3;
import ru.netology.web.page.TopUpPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.web.data.DataHelper.*;


class MoneyTransferTest {
  DashboardPage dashboardPage;
  DataHelper.CardInfo cardInfo1;
  DataHelper.CardInfo cardInfo2;
  int balance1;
  int balance2;

  @BeforeEach
  void setup() {
    var loginPage = open("http://localhost:9999", LoginPageV3.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    dashboardPage = verificationPage.validVerify(verificationCode);
    cardInfo1 = getCardInfo1();
    cardInfo2 = getCardInfo2();
    balance1 = dashboardPage.getFirstCardBalance();
    balance2 = dashboardPage.getSecondCardBalance();
    }

  @Test
  void transferMoneyFrom1Test() {
    int ammount = getTopUpAmount(balance1);
    int expected1 = balance1 - ammount;
    int expected2 = balance2 + ammount;
    TopUpPage topUpPage = dashboardPage.cardTopUp(cardInfo2);
    dashboardPage = topUpPage.succesTopUp(String.valueOf(ammount), cardInfo1);
    int actual1 = dashboardPage.getFirstCardBalance();
    int actual2 = dashboardPage.getSecondCardBalance();
    Assertions.assertEquals(expected1, actual1);
    Assertions.assertEquals(expected2, actual2);
  }

  @Test
  void errorTest() {
    int amount = getWrongTopUpAmount(balance1);
        TopUpPage topUpPage = dashboardPage.cardTopUp(cardInfo2);
    dashboardPage = topUpPage.succesTopUp(String.valueOf(amount), cardInfo1);
    sleep(3000);
    topUpPage.errorMessage("Ошибка! Произошла ошибка!");
    Assertions.assertEquals(balance1, dashboardPage.getFirstCardBalance());
    Assertions.assertEquals(balance2, dashboardPage.getSecondCardBalance());
  }


}

