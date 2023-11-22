package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
  private DataHelper() {}

  public static int getTopUpAmount(int balance){
    Random random = new Random();
    int ammount = random.nextInt(balance);
    return ammount;
  }

  public static int getWrongTopUpAmount(int balance){
    Random random = new Random();
    int ammount = random.nextInt(balance);
    return ammount;
  }

  public static CardInfo getCardInfo1 (){
    CardInfo firstCard = new CardInfo("5559 0000 0000 0001","92df3f1c-a033-48e6-8390-206f6b1f56c0");
    return firstCard;
  }
  public static CardInfo getCardInfo2 (){
    CardInfo secondCard = new CardInfo("5559 0000 0000 0002","0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    return secondCard;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }

  public static AuthInfo getOtherAuthInfo(AuthInfo original) {
    return new AuthInfo("petya", "123qwerty");
  }
  public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
    return new VerificationCode("12345");
  }

  @Value
  public static class VerificationCode {
    private String code;
  }
  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }

  @Value
  public static class CardInfo {
    private String cardNum;
    private String cardId;
  }



}
