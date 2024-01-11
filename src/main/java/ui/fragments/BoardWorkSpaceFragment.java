package ui.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class BoardWorkSpaceFragment {
    private static final String ALL_LIST_TITLES = ".//h2[@data-testid='list-name']";
    private static final String ALL_CARDS = ".//a[text()='%s']";

    private SelenideElement rootElement() {
        return $x("//div[@class='board-canvas']");
    }

    public ElementsCollection getAllListTitles() {
        return rootElement().$$x(ALL_LIST_TITLES).shouldBe(sizeGreaterThan(0));
    }

    public SelenideElement getAllCardsFromTheLists(String cardName) {
        return rootElement().$x(format(ALL_CARDS, cardName)).shouldBe(Condition.visible);
    }
}