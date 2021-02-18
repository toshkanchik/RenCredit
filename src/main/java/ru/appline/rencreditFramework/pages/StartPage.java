package ru.appline.rencreditFramework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StartPage extends BasePage{
    @FindBy(css = ".services_main > .service")
    private List<WebElement> mainOptions;

    public void clickOnMainOption(String name){
        for(WebElement element: mainOptions){
            if (element
                    .findElement(By.cssSelector(".service__title-text"))
                    .getText().equals(name)) {
                element.findElement(By.cssSelector(".service__title > a")).click();
                try {
                    wait.until(ExpectedConditions.titleIs(name));
                } catch (TimeoutException e){
                    Assertions.fail("Не удалось открыть " + name);
                }
                break;
            }
        }
    }
}
