package me.demo.ui.imdb;

import me.demo.annotations.IMDB;
import me.demo.annotations.UI;
import me.demo.ui.template.AbstractSuggestionBox;
import org.openqa.selenium.By;

@UI
@IMDB
class IMDBSuggestionBox extends AbstractSuggestionBox {

	private static final By AUTO_SUGGEST = By.cssSelector("ul[class*='suggestions-list'] > li div[class *= 'constTitle']");

	public IMDBSuggestionBox() {
		super(AUTO_SUGGEST);
	}

}
