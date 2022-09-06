package me.demo.ui.imdb;

import lombok.extern.slf4j.Slf4j;
import me.demo.annotations.IMDB;
import me.demo.annotations.UI;
import me.demo.ui.template.AbstractMovieInfoContainer;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;

@UI
@IMDB
@Slf4j
class IMDBMovieInfoContainer extends AbstractMovieInfoContainer {

	private static final By COUNTRY_OF_ORIGIN = By.cssSelector("ul[class *= 'content'] a[href *='country_of_origin']");
	private static final By RELEASE_DATE = By.cssSelector("ul[class *= 'content'] a[href *='releaseinfo']");

	private static final int DATE_GROUP = 1;
	private static final int COUNTRY_GROUP = 3;
	private static final Pattern DATE_REGEX = Pattern.compile("([A-z]+ \\d{0,2}, \\d{4}) (\\(([\\w\\s]+)\\))");
	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("MMMM d, u", Locale.ENGLISH);

	@Override
	protected String readCountry() {
		return $(COUNTRY_OF_ORIGIN).innerText();
	}

	@Override
	protected ZonedDateTime readReleaseDate() {
		var word = $(RELEASE_DATE).innerText();
		var matcher = DATE_REGEX.matcher(word);
		if (!matcher.matches()) {
			log.error("word '{}' doesn't match pattern '{}'", word, DATE_REGEX.pattern());
			throw new IllegalArgumentException(word);
		}
		var dateWord = matcher.group(DATE_GROUP);
		var localDate = LocalDate.parse(dateWord, DATE_PATTERN);
		var countrySuffix = matcher.group(COUNTRY_GROUP);
		return convertToZDT(localDate, countrySuffix);
	}

}

