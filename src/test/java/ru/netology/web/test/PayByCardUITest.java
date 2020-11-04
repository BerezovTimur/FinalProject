package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.OrderPage;

public class PayByCardUITest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    public class NegativeTestsByNumberCard {

        @Test
        void shouldNotPayIfShortNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getSmallNumberCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNoNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getCardWithoutNumber(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfWrongSymbolNoNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getNotDigitalNumber(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }
    }

    @Nested
    public class NegativeTestsByMonth {

        @Test
        void shouldNotPayIfNoMonth() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getNoMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNullMonth() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getNullMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }

        @Test
        void shouldNotPayIfWrongMonth() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getWrongMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }

        @Test
        void shouldNotPayIfCardExpired() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getLastMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }
    }

    @Nested
    public class NegativeTestsByYear {

        @Test
        void shouldNotPayIfNoYear() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getNoYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNullYear() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getNullYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.invalidCardMassage();
        }

        @Test
        void shouldNotPayIfLastYear() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getLastYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.invalidCardMassage();
        }

        @Test
        void shouldPayIfWrongYear() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getMaxValidDate();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }
    }

    @Nested
    public class NegativeTestsByCVV {

        @Test
        void shouldNotPayIfNoCVV() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getNoCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfWrongCVV() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholder();
            String cvv = dataHelper.getWrongCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }
    }

    @Nested
    public class NegativeTestsByCardholder {

        @Test
        void shouldNotPayIfNoName() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getNoCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }

        @Test
        void shouldNotPayIfWrongName() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getCardholderRussianLatter();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }

        @Test
        void shouldNotPayIfOnlyName() {
            OrderPage orderPage = new OrderPage();
            DataHelper dataHelper = new DataHelper();
            String month = dataHelper.getCurrentMonth();
            String year = dataHelper.getCurrentYear();
            String cardholder = dataHelper.getOnlyNameCardholder();
            String cvv = dataHelper.getCvv();
            orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }
    }
}
