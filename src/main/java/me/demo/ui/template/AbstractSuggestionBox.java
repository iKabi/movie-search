package me.demo.ui.template;

import com.codeborne.selenide.CollectionCondition;
import me.demo.ui.SuggestionBox;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public abstract class AbstractSuggestionBox implements SuggestionBox {
	private static final CollectionCondition NOT_EMPTY = CollectionCondition.sizeGreaterThan(0);

	protected By autoSuggest;

	public AbstractSuggestionBox(By autoSuggest) {
		this.autoSuggest = autoSuggest;
	}

	@Override
	public void select(String desired) {
		$$(autoSuggest)
			.shouldBe(NOT_EMPTY)
			.first()
			.click();
	}
}
