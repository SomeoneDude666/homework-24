package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data
public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCardInfo = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCardInfo = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement firstCardButton = $$("[data-test-id='action-deposit']").first();
    private SelenideElement secondCardButton = $$("[data-test-id='action-deposit']").last();
    private SelenideElement sumToTransfer = $("[maxlength='14']");
    private SelenideElement payCard = $("[maxlength='19']");
    private SelenideElement acceptButton = $("[data-test-id='action-transfer']");
    final int randome = ((int) (Math.random() * 10000));


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public double getBalanceSecondCard() {
        String[] infoBoforeOperation = secondCardInfo.getText().split(": ");
        String infoBoforeOperation2[] = infoBoforeOperation[1].split(" р");
        return Double.parseDouble(infoBoforeOperation2[0]);
    }


    public void transferIntMoney() {
        firstCardButton.click();
        sumToTransfer.setValue(Integer.toString(randome));
        payCard.setValue("5559 0000 0000 0002");
        acceptButton.click();
    }

    public void transferDoubleMoney() {
        firstCardButton.click();
        sumToTransfer.setValue(Double.toString(randome));
        payCard.setValue("5559 0000 0000 0002");
        acceptButton.click();
    }
}

