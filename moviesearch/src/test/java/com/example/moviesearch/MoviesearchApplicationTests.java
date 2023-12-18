package com.example.moviesearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.moviesearch.pages.IndexPage;
import com.example.moviesearch.pages.SearchFormPage;
import com.example.moviesearch.pages.SearchResultPage;

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

	@Autowired
	private SearchResultPage searchResultPage;

	@Test
	public void indexPage_onButtonClick_navigatesToSearchFormPage() {
		webDriver.navigate().to(appUrl);

		indexPage.clickSearch();

		assertNotNull(searchFormPage.getForm());
	}

	@Test
	public void searchFormPage_onFormValidSubmit_resultDataIsShown() {
		webDriver.navigate().to(appUrl + "search");

		searchFormPage.fillAndSubmitForm("Jigsaw", "0", "movie");

		assertNotNull(searchResultPage.getResultsDiv());
	}

	@Test
	public void searchFormPage_onFormValidationFail_stayOnThePage() {
		webDriver.navigate().to(appUrl + "search");

		searchFormPage.fillAndSubmitForm("", "-5", "series");

		assertNotNull(searchFormPage.getForm());
		assertNull(searchResultPage.getResultsDiv());
	}

	@Test
	public void searchFormPage_onResetClick_resetsValues() {
		webDriver.navigate().to(appUrl + "search");

		searchFormPage.resetForm();
		List<String> values = searchFormPage.getFormValues();

		assertNotNull(searchFormPage.getForm());
		assertEquals("", values.get(0));
		assertEquals("0", values.get(1));
		assertEquals("movie", values.get(2));
	}

	@Test
	public void searchResultPage_containsData() {
		webDriver.navigate().to(appUrl + "search");
		searchFormPage.fillAndSubmitForm("Jigsaw", "0", "movie");

		assertNotNull(searchResultPage.getResultsDiv());
	}
}
