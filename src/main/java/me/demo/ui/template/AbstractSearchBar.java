package me.demo.ui.template;

import me.demo.ui.SearchBar;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class AbstractSearchBar implements SearchBar {

	protected By searchBox;

	public AbstractSearchBar(By searchBox) {
		this.searchBox = searchBox;
	}

	public void search(String movieName) {
		$(searchBox)
			.shouldBe(visible)
			.setValue(movieName)
			.shouldHave(value(movieName));
	}

}
