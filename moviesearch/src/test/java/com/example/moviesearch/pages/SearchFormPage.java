package com.example.moviesearch.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class SearchFormPage {

    @FindBy(how = How.ID, using = "form-title-input")
    private WebElement titleInput;

    @FindBy(how = How.ID, using = "form-year-input")
    private WebElement yearInput;

    @FindBy(how = How.ID, using = "form-type-select")
    private WebElement typeSelect;

    @FindBy(how = How.ID, using = "submit-button")
    private WebElement submitButton;

    @FindBy(how = How.ID, using = "reset-button")
    private WebElement resetButton;

    @Autowired
    private WebDriver webDriver;

    @PostConstruct
    public void Init() {
        PageFactory.initElements(webDriver, this);
    }

    public void fillAndSubmitForm(String title, String year, String type) {
        titleInput.sendKeys(title);
        yearInput.sendKeys(year);
        Select select = new Select(typeSelect);
        select.selectByValue(type);
        submitButton.click();
    }
}
