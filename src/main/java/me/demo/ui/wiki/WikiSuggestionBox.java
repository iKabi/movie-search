package me.demo.ui.wiki;

import me.demo.annotations.UI;
import me.demo.annotations.Wiki;
import me.demo.ui.template.AbstractSuggestionBox;
import org.openqa.selenium.By;

@UI
@Wiki
class WikiSuggestionBox extends AbstractSuggestionBox {

	private static final By AUTO_SUGGEST = By.cssSelector("a[class*='searchSuggest-link']");

	public WikiSuggestionBox() {
		super(AUTO_SUGGEST);
	}

}
