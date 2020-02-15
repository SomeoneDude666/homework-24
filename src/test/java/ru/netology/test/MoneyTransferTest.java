package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {


    @BeforeEach
    void shouldTransferMoneyBetweenOwnCards() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferIntMoneyCard2Card() {
        DashboardPage dashboardPage = new DashboardPage();
        double balanceBeforeOperation = dashboardPage.getBalanceSecondCard();
        double expectedBalanceAfterOperation = balanceBeforeOperation - dashboardPage.getRandome();
        dashboardPage.transferIntMoney();
        double actualBalanceAfterOperation = dashboardPage.getBalanceSecondCard();
        assertEquals(expectedBalanceAfterOperation, actualBalanceAfterOperation);
        close();
        }


    @Test

    void shouldTransferDecimalMoneyCard2Card() {
        DashboardPage dashboardPage = new DashboardPage();
        double balanceBeforeOperation = dashboardPage.getBalanceSecondCard();
        double moneyToTransfer = 0.1;
        double expectedBalanceAfterOperation = balanceBeforeOperation - moneyToTransfer;
        dashboardPage.transferDoubleMoney();
        double actualBalanceAfterOperation = dashboardPage.getBalanceSecondCard();
        assertEquals(expectedBalanceAfterOperation, actualBalanceAfterOperation);
        close();
    }
}
