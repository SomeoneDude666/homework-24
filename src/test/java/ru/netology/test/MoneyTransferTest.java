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
        String[] infoBoforeOperation = dashboardPage.getSecondCardInfo().getText().split(": ");
        String infoBoforeOperation2[] = infoBoforeOperation[1].split(" р");
        int balanceBeforeOperation = Integer.parseInt(infoBoforeOperation2[0]);
        int randome = ((int) (Math.random() * 10000));
        int expectedBalanceAfterOperation = balanceBeforeOperation - randome;
        dashboardPage.getFirstCardButton().click();
        dashboardPage.getSumToTransfer().setValue(Integer.toString(randome));
        dashboardPage.getPayCard().setValue("5559 0000 0000 0002");
        dashboardPage.getAcceptButton().click();
        String[] infoAfterOperation = dashboardPage.getSecondCardInfo().getText().split(": ");
        String infoAfterOperation2[] = infoAfterOperation[1].split(" ");
        int actualBalanceAfterOperation = Integer.parseInt(infoAfterOperation2[0]);
        assertEquals(expectedBalanceAfterOperation, actualBalanceAfterOperation);
        close();
        }


    @Test

    void shouldTransferDecimalMoneyCard2Card() {
        DashboardPage dashboardPage = new DashboardPage();
        String[] infoBoforeOperation = dashboardPage.getSecondCardInfo().getText().split(": ");
        String infoBoforeOperation2[] = infoBoforeOperation[1].split(" р");
        Double balanceBeforeOperation = Double.parseDouble(infoBoforeOperation2[0]);
        double moneyToTransfer = 0.1;
        double expectedBalanceAfterOperation = balanceBeforeOperation - moneyToTransfer;
        dashboardPage.getFirstCardButton().click();
        dashboardPage.getSumToTransfer().setValue(Double.toString(moneyToTransfer));
        dashboardPage.getPayCard().setValue("5559 0000 0000 0002");
        dashboardPage.getAcceptButton().click();
        String[] infoAfterOperation = dashboardPage.getSecondCardInfo().getText().split(": ");
        String infoAfterOperation2[] = infoAfterOperation[1].split(" ");
        double actualBalanceAfterOperation = Integer.parseInt(infoAfterOperation2[0]);
        assertEquals(expectedBalanceAfterOperation, actualBalanceAfterOperation);
        close();
    }
}
