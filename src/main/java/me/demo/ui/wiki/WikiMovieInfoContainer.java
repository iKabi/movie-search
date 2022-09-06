package me.demo.ui.wiki;

import lombok.extern.slf4j.Slf4j;
import me.demo.annotations.UI;
import me.demo.annotations.Wiki;
import me.demo.ui.template.AbstractMovieInfoContainer;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

@UI
@Wiki
@Slf4j
class WikiMovieInfoContainer extends AbstractMovieInfoContainer {

	private static final By RELEASE_DATE = By.xpath("//th[div[text()='Release date']]/following-sibling::td//span[contains(@class, 'published')]");
	private static final By COUNTRY_OF_ORIGIN = By.xpath("//th[text()='Country']/following-sibling::td");

	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	protected String readCountry() {
		return $(COUNTRY_OF_ORIGIN).innerText();
	}

	@Override
	protected ZonedDateTime readReleaseDate() {
		var word = $(RELEASE_DATE).innerText();
		var date = LocalDate.parse(word, DATE_PATTERN);
		return unixTime(date, readCountry());
	}

}

