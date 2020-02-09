package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Integer.parseInt;

@Data
public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCardInfo = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCardInfo = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement firstCardButton = $$("[data-test-id='action-deposit']").first();
    private SelenideElement secondCardButton = $$("[data-test-id='action-deposit']").last();
    private SelenideElement sumToTransfer = $("maxlength='14'");
    private SelenideElement payCard = $("maxlength='19'");
    private SelenideElement acceptButton = $("[data-test-id='action-transfer']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int readFirstCardMoney() {
        String before[] = firstCardInfo.getValue().toString().split(": ");
        String before2[] = before[1].split(" ");
        return Integer.parseInt(before2[0]);
    }

    public int readSecondCardMoney() {
        String before[] = secondCardInfo.getValue().toString().split(": ");
        String before2[] = before[1].split(" ");
        return Integer.parseInt(before2[0]);
    }

    public DashboardPage transferMoneyIntFormFirstToSecond(DataHelper.DashboardTestInfo dashboardTestInfo) {
        firstCardButton.click();
        int randome = dashboardTestInfo.getMoneyToTransfer();
        sumToTransfer.setValue(Integer.toString(randome));
        payCard.setValue(dashboardTestInfo.getSecondCard());
        acceptButton.click();
        System.out.println(randome);
        return new DashboardPage();
    }
}

