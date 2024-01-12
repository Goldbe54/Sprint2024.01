package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$x;

public class BoardWorkSpaceFragment {
    private final String ALL_LIST_TITLES = ".//h2[@data-testid='list-name']";

    private SelenideElement rootElement() {
        return $x("//div[@class='board-canvas']");
    }

    public ElementsCollection getAllListTitles() {
        return rootElement().$$x(ALL_LIST_TITLES).shouldBe(sizeGreaterThan(0));
    }
}