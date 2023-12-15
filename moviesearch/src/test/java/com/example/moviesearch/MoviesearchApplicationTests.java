package com.example.moviesearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.moviesearch.pages.IndexPage;
import com.example.moviesearch.pages.SearchFormPage;

@SpringBootTest
public class MoviesearchApplicationTests {

	@Value("${app.url}")
	private String appUrl;

	@Autowired
	private WebDriver webDriver;

	@Autowired
	private IndexPage indexPage;

	@Autowired
	private SearchFormPage searchFormPage;

	@Test
	public void indexPage_OnButtonClick_NavigatesToSearchFormPage() {
		webDriver.navigate().to(appUrl);

		indexPage.clickSearch();

		assertFalse(webDriver.findElements(By.tagName("form")).isEmpty());
	}

	@Test
	public void searchFormPage_OnFormValidSubmit_ResultDataIsShown() {
		webDriver.navigate().to(appUrl + "search");

		searchFormPage.fillAndSubmitForm("Jigsaw", "0", "movie");

		assertFalse(webDriver.findElements(By.id("results")).isEmpty());
	}

	@Test
	public void searchFormPage_OnFormValidationFail_StayOnThePage() {
		webDriver.navigate().to(appUrl + "search");

		searchFormPage.fillAndSubmitForm("", "-5", "series");

		// check if the form is still present on the page, and there are no results
		assertFalse(webDriver.findElements(By.tagName("form")).isEmpty());
		assertTrue(webDriver.findElements(By.className("results")).isEmpty());
	}

	// TODO: test form reset button
}
