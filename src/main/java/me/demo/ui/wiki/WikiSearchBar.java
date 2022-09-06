package me.demo.ui.wiki;

import me.demo.annotations.UI;
import me.demo.annotations.Wiki;
import me.demo.ui.template.AbstractSearchBar;
import org.openqa.selenium.By;

@UI
@Wiki
class WikiSearchBar extends AbstractSearchBar {

	private static final By SEARCH_BOX = By.id("searchInput");

	public WikiSearchBar() {
		super(SEARCH_BOX);
	}

}
