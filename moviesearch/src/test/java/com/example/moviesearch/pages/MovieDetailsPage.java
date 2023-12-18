package com.example.moviesearch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MovieDetailsPage {
    
    @Autowired
    private WebDriver webDriver;

    @PostConstruct
    public void Init() {
        PageFactory.initElements(webDriver, this);
    }
    
    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement searchButton;

    public WebElement getTitle() {
        try {
            return webDriver.findElement(By.id("details-title"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public WebElement getYear() {
        try {
            return webDriver.findElement(By.id("details-released"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public WebElement getType() {
        try {
            return webDriver.findElement(By.id("details-type"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public WebElement getPlot() {
        try {
            return webDriver.findElement(By.id("details-plot"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void clickNewSearch() {
        searchButton.click();
    }
}
