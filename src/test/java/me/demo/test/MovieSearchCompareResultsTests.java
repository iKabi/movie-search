package me.demo.test;

import me.demo.annotations.IMDB;
import me.demo.annotations.Wiki;
import me.demo.manager.MovieManager;
import me.demo.ui.MovieInfoContainer;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.TemporalUnitOffset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assumptions.assumeThat;


public
@SpringBootTest(classes = me.demo.MovieSearchApplication.class)
class MovieSearchCompareResultsTests extends AbstractTestNGSpringContextTests {

	private static final TemporalUnitOffset RELEASE_DATE_THRESHOLD = within(2, ChronoUnit.MONTHS);


	@IMDB
	@Autowired
	MovieManager desiredMovieManager;

	@Wiki
	@Autowired
	MovieManager testableMovieManager;

	MovieInfoContainer.MovieInfo want;

	@BeforeClass
	@Parameters("movie")
	void precondition_initWant(String searchText) {

		assumeThat(searchText).isNotNull();

		desiredMovieManager.init();
		want = desiredMovieManager.getMovieInfo(searchText);
		desiredMovieManager.shutDown();
	}

	@BeforeMethod
	void setup() {
		assumeThat(want).isNotNull();
		testableMovieManager.init();
	}

	@Test(description = """
		Given a search result from IMDB
		When user searches in Wiki
		Then user should see the same result
		""")
	@Parameters("movie")
	void shouldHaveTheSameResult_whenSearchedWithDifferentMovieManagers(String searchText) {
		var softly = new SoftAssertions();
		var got = testableMovieManager.getMovieInfo(searchText);
		softly.assertThat(got.country())
			.as("country")
			.isEqualTo(want.country());
		softly.assertThat(got.releaseDate())
			.as("release-date")
			.isCloseTo(want.releaseDate(), RELEASE_DATE_THRESHOLD);
		softly.assertAll();
	}

	@AfterMethod
	void tearDown() {
		testableMovieManager.shutDown();
	}

}
