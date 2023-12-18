package com.example.moviesearch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    
    public WebElement getResultsDiv() {
        try {
            return webDriver.findElement(By.id("results"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
