package com.example.moviesearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.moviesearch.pages.IndexPage;

@SpringBootTest
class MoviesearchApplicationTests {

	@Value("${app.url}")
	private String appUrl;

	@Autowired
	private WebDriver webDriver;

	@Autowired
	private IndexPage indexPage;

	@Test
	void indexPage_OnButtonClick_NavigatesToFormPage() {
		webDriver.navigate().to(appUrl);
		indexPage.clickSearch();

		assertEquals(appUrl + "search", webDriver.getCurrentUrl());
	}

}
