package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.OrderPage;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayByCardAPITest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private String approved = "APPROVED";
    private String declined = "DECLINED";

    @Test
    void shouldPayIfValidCard() throws SQLException {
        OrderPage orderPage = new OrderPage();
        DataHelper dataHelper = new DataHelper();
        String month = dataHelper.getCurrentMonth();
        String year = dataHelper.getCurrentYear();
        String cardholder = dataHelper.getCardholder();
        String cvv = dataHelper.getCvv();
        orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
        orderPage.getSuccessMessage();
        SQLHelper.comparePaymentAndTransactionID();
        String status = approved;
        String actualStatus = SQLHelper.getPaymentStatus();
        assertEquals(status, actualStatus);
    }

    @Test
    void shouldNotPayIfValidCard() throws SQLException {
        OrderPage orderPage = new OrderPage();
        DataHelper dataHelper = new DataHelper();
        String month = dataHelper.getCurrentMonth();
        String year = dataHelper.getCurrentYear();
        String cardholder = dataHelper.getCardholder();
        String cvv = dataHelper.getCvv();
        orderPage.setPayment(DataHelper.getDeclinedCard(), month, year, cardholder, cvv);
        orderPage.getErrorMessage();
        SQLHelper.comparePaymentAndTransactionID();
        String status = declined;
        String actualStatus = SQLHelper.getPaymentStatus();
        assertEquals(status, actualStatus);
    }

    @Test
    void shouldPayIfAnotherCard() throws SQLException {
        OrderPage orderPage = new OrderPage();
        DataHelper dataHelper = new DataHelper();
        String month = dataHelper.getCurrentMonth();
        String year = dataHelper.getCurrentYear();
        String cardholder = dataHelper.getCardholder();
        String cvv = dataHelper.getCvv();
        orderPage.setPayment(DataHelper.getAnotherBankCard(), month, year, cardholder, cvv);
        orderPage.getSuccessMessage();
        SQLHelper.comparePaymentAndTransactionID();
        String status = approved;
        String actualStatus = SQLHelper.getPaymentStatus();
        assertEquals(status, actualStatus);
    }
}
