package me.demo.manager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import me.demo.ui.MovieInfoContainer;
import me.demo.ui.MovieInfoContainer.MovieInfo;
import me.demo.ui.SearchBar;
import me.demo.ui.SuggestionBox;
import org.springframework.beans.factory.annotation.Value;

public abstract class MovieManager implements Hooks {

	private final SearchBar searchBar;
	private final SuggestionBox suggestionBox;
	private final MovieInfoContainer movieInfoContainer;

	@Value("${browser}")
	private String browser;

	@Value("${headless}")
	private boolean headless;

	protected MovieManager(SearchBar searchBar,
	                       SuggestionBox suggestionBox,
	                       MovieInfoContainer movieInfoContainer) {
		this.searchBar = searchBar;
		this.suggestionBox = suggestionBox;
		this.movieInfoContainer = movieInfoContainer;
	}

	public void shutDown() {
		Selenide.closeWebDriver();
	}

	public void init() {
		Configuration.browser = browser;
		Configuration.headless = headless;
		Selenide.open(baseUrl());
	}

	protected abstract String baseUrl();

	public final MovieInfo getMovieInfo(String movie) {
		searchBar.search(movie);
		suggestionBox.select(movie);
		return movieInfoContainer.readMovieInfo();
	}

}
