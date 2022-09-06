package me.demo.manager.impl;

import me.demo.annotations.LazyAutowired;
import me.demo.annotations.Wiki;
import me.demo.manager.MovieManager;
import me.demo.ui.MovieInfoContainer;
import me.demo.ui.SearchBar;
import me.demo.ui.SuggestionBox;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Wiki
@Lazy
@Component
@Scope(SCOPE_PROTOTYPE)
class WikiMovieManager extends MovieManager {

	private static final String WIKI_EN_URL = "https://en.wikipedia.org/wiki/Main_Page";

	@LazyAutowired
	public WikiMovieManager(@Wiki SearchBar searchBar,
	                        @Wiki SuggestionBox suggestionBox,
	                        @Wiki MovieInfoContainer movieInfoContainer) {
		super(searchBar, suggestionBox, movieInfoContainer);
	}

	@Override
	public String baseUrl() {
		return WIKI_EN_URL;
	}

}
