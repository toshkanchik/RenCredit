package ru.appline.rencreditFramework.pages;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.rencreditFramework.utils.Currencies;

import java.util.List;

public class DepositPage extends BasePage {
    @FindBy(css = ".calculator__currency-field")
    List<WebElement> currencyFields;

    @FindBy(css = ".calculator__slide-input-label")
    List<WebElement> inputFields;

    @FindBy(css = ".js-calc-result")
    WebElement calcResult;

    @FindBy(css = ".calculator__check-row-field")
    List <WebElement> checkboxes;

    @FindBy(css = ".js-calc-earned")
    WebElement earned;

    @FindBy(css = ".js-calc-replenish")
    WebElement replenish;

    @FindBy(css = ".calculator__dep-result-value>.js-calc-result")
    WebElement mainResult;

    public DepositPage chooseCurrency(@NotNull Currencies curr) {
        String str = curr.toString();
        try {
            currencyFields.stream().
                    filter(elem -> elem.findElement(By.cssSelector(".calculator__currency-field-text")).getText().equals(str))
                    .findAny().ifPresent(WebElement::click);
        } catch (NullPointerException e) {
            Assertions.fail("Валюта " + curr + " не найдена");
        }
        return this;
    }

    public DepositPage fillInTextField(String name, String value) {
        WebElement inputField;
        for (WebElement elem : inputFields){
            if(elem.getText().equals(name)){
                inputField = elem.findElement(By.xpath("./following-sibling::div//input"));
                scrollToElementOffsetJs(inputField, -150);
                if(inputField.getAttribute("value").equals(value)) return this;
                inputField.click();
                inputField.sendKeys(value);
                elem.click();
                Assertions.assertEquals(inputField.getAttribute("value"), value, "Данные не ввелись");
                waitToCalc();
                return this;
            }
        }
        Assertions.fail("Элемент под именем " + name +" не нейден");
        return this;
    }
    public DepositPage fillInDropDownField(String name, Integer value) {
        WebElement inputField;
        for (WebElement elem : inputFields){
            if(elem.getText().equals(name)){
                inputField = elem.findElement(By.xpath("./following-sibling::div"));
                scrollToElementOffsetJs(inputField, -150);
                inputField.click();
                for(WebElement choice : inputField.findElements(By.xpath(".//li"))){
                    if(choice.getText().contains(value.toString())){
                        choice.click();
                        Assertions.assertEquals(
                                elem.findElement(By.xpath("./following-sibling::div//select"))
                                .getAttribute("value"), value.toString(), "Данные ввелись некорректно");
                        return this;
                    }
                }
                Assertions.fail("Значение " + value +" не нейдено");
            }
        }
        Assertions.fail("Элемент под именем " + name +" не нейден");
        return this;
    }

    public DepositPage clickCheckboxName(String name, Boolean value){
        WebElement tmp;
        for (WebElement elem : checkboxes){
            if(elem.findElement(By.xpath(".//span[@class='calculator__check-text']")).getText().equals(name)){
                scrollToElementOffsetJs(elem, -150);
                tmp = elem.findElement(By.xpath(".//span[@class='calculator__check-block-input']"));
                if(tmp.findElement(By.xpath("./div")).getAttribute("class").contains("checked") != value){
                    tmp.click();
                    waitToCalc();
                }
                return this;
            }
        }
        Assertions.fail("Элемент под именем " + name +" не нейден");
        return this;
    }

    public DepositPage checkEarned(String value){
        scrollToElementOffsetJs(earned, -150);
        Assertions.assertEquals(earned.getText(), value, "Значение 'Начислено' не соответствует ожидаемому");
        return this;
    }

    public DepositPage checkReplenish(String value){
        scrollToElementOffsetJs(replenish, -150);
        Assertions.assertEquals(replenish.getText(), value, "Значение 'Пополнение' не соответствует ожидаемому");
        return this;
    }

    public DepositPage checkMainResult(String value){
        scrollToElementOffsetJs(mainResult, -150);
        Assertions.assertEquals(mainResult.getText(), value, "Финальное значение не соответствует ожидаемому");
        return this;
    }

    private void waitToCalc(){
        String result = calcResult.getText();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(calcResult, result)));
    }
}
