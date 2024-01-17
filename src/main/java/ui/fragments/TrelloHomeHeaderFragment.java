package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$x;

public class TrelloHomeHeaderFragment {

    private static final String SEARCH_FIELD = ".//input";
    private static final String SEARCH_RESULTS_TITLES = ".//a[@data-testid='cross-product-search-result']//span[not(@*)]";
    private SelenideElement rootElement() {
        return $x("//div[@data-testid='header-container']");
    }

    private SelenideElement searchDropdownElement() {
        return $x("//div[@data-test-id='search-dialog-dialog-wrapper']");
    }

    public SelenideElement getSearchField() {
        return rootElement().$x(SEARCH_FIELD).shouldBe(interactable);
    }

    public ElementsCollection getSearchResultTitles() {
        return searchDropdownElement().$$x(SEARCH_RESULTS_TITLES).shouldBe(sizeGreaterThan(0));
    }

    public List<String> getListOfSearchResultTitles() {
        ElementsCollection elements = getSearchResultTitles();
        return ElementUtil.getListOfStrings(elements);
    }
}
