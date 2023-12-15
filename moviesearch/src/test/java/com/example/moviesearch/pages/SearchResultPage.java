package com.example.moviesearch.pages;

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
    
    @FindBy(how = How.ID, using = "results")
    private WebElement resultsDiv;

    public WebElement getResultsDiv() {
        return resultsDiv;
    }

    @Autowired
    private WebDriver webDriver;

    @PostConstruct
    public void Init() {
        PageFactory.initElements(webDriver, this);
    }
}
