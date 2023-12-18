package com.example.moviesearch.pages;

import java.util.List;

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
public class SearchResultPage {
    
    @Autowired
    private WebDriver webDriver;

    @PostConstruct
    public void Init() {
        PageFactory.initElements(webDriver, this);
    }
    
    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement searchButton;
    
    public WebElement getResultsDiv() {
        try {
            return webDriver.findElement(By.id("results"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public WebElement getMovie(int number) {
        List<WebElement> movies = webDriver.findElements(By.tagName("li"));
        try {
            return movies.get(number);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void clickNewSearch() {
        searchButton.click();
    }
}
