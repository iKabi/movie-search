package me.demo.ui.imdb;

import me.demo.annotations.IMDB;
import me.demo.annotations.UI;
import me.demo.ui.template.AbstractSearchBar;
import org.openqa.selenium.By;

@UI
@IMDB
class IMDBSearchBar extends AbstractSearchBar {

	private static final By SEARCH_BOX = By.id("suggestion-search");

	public IMDBSearchBar() {
		super(SEARCH_BOX);
	}

}
