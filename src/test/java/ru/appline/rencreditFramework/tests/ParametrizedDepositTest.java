package ru.appline.rencreditFramework.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.appline.rencreditFramework.baseClasses.BaseTestClass;
import ru.appline.rencreditFramework.utils.Currencies;


public class ParametrizedDepositTest extends BaseTestClass{
    @Tag("DepositTest")
    @ParameterizedTest(name = "Тестирование вклада {0}")
    @CsvFileSource(delimiter='|', resources = "/TestsData.csv")
    public void startTest(Currencies curr, String summ, String monthly, int months, boolean capitalization, String earned, String Replanish, String main){
        pages.getStartPage()
                .clickOnMainOption("Вклады");
        pages.getDepositPage()
                .chooseCurrency(curr)
                .fillInTextField("Сумма вклада", summ)
                .fillInTextField("Ежемесячное пополнение", monthly)
                .fillInDropDownField("На срок", months)
                .clickCheckboxName("Ежемесячная капитализация", capitalization)
                .checkEarned(earned)
                .checkReplenish(Replanish)
                .checkMainResult(main)
        ;
    }
}
