package me.demo.manager.impl;

import me.demo.annotations.IMDB;
import me.demo.annotations.LazyAutowired;
import me.demo.manager.MovieManager;
import me.demo.ui.MovieInfoContainer;
import me.demo.ui.SearchBar;
import me.demo.ui.SuggestionBox;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@IMDB
@Lazy
@Component
@Scope(SCOPE_PROTOTYPE)
class IMDBMovieManager extends MovieManager {

	private static final String IMDB_URL = "https://imdb.com";

	@LazyAutowired
	public IMDBMovieManager(@IMDB SearchBar searchBar,
	                        @IMDB SuggestionBox suggestionBox,
	                        @IMDB MovieInfoContainer movieInfoContainer) {
		super(searchBar, suggestionBox, movieInfoContainer);
	}

	@Override
	public String baseUrl() {
		return IMDB_URL;
	}

}
