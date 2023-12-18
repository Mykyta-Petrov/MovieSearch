package com.example.moviesearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.moviesearch.pages.IndexPage;
import com.example.moviesearch.pages.MovieDetailsPage;
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

	@Autowired
	private MovieDetailsPage movieDetailsPage;

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

	@Test
	public void searchResultPage_onNewSearchClick_navigatesToSearchFormPage() {
		webDriver.navigate().to(appUrl + "search");
		searchFormPage.fillAndSubmitForm("Avengers", "0", "movie");

		searchResultPage.clickNewSearch();

		assertEquals(appUrl + "search", webDriver.getCurrentUrl());
		assertNotNull(searchFormPage.getForm());
	}

	@Test
	public void searchResultPage_onExistingMovie_hasResults() {
		webDriver.navigate().to(appUrl + "search");
		searchFormPage.fillAndSubmitForm("Bee", "0", "movie");

		assertNotNull(searchResultPage.getMovie(0));
	}

	@Test
	public void searchResultPage_onDetailsClick_navigatesToMovieDetailsPage() {
		webDriver.navigate().to(appUrl + "search");
		searchFormPage.fillAndSubmitForm("Shrek", "0", "movie");
		WebElement movie = searchResultPage.getMovie(0);
		String title = movie.findElement(By.className("result-title")).getText().substring(7);
		String year = movie.findElement(By.className("result-year")).getText().substring(6);
		String type = movie.findElement(By.className("result-type")).getText().substring(6);

		movie.findElement(By.tagName("a")).click();

		assertTrue(movieDetailsPage.getTitle().getText().contains(title));
		assertTrue(movieDetailsPage.getType().getText().contains(type));
		assertTrue(movieDetailsPage.getYear().getText().contains(year));
		assertNotNull(movieDetailsPage.getPlot());
	}

	@Test
	public void movieDetailsPage_onNewSearchClick_navigatesToSearchFormPage() {
		webDriver.navigate().to(appUrl + "search");
		searchFormPage.fillAndSubmitForm("indiana", "0", "movie");
		searchResultPage.getMovie(0).findElement(By.tagName("a")).click();

		movieDetailsPage.clickNewSearch();

		assertEquals(appUrl + "search", webDriver.getCurrentUrl());
		assertNotNull(searchFormPage.getForm());
	}
}
