package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class BoardWorkSpaceFragment {
    private static final String ALL_LIST_TITLES = ".//h2[@data-testid='list-name']";
    private static final String SPECIFIC_LIST = ".//textarea[@aria-label='%s']";
    private static final String SPECIFIC_CARD_TITLE_IN_LIST = "./ancestor::div[@data-testid='list']//a[contains(text(),'%s')]";
    private static final String ALL_CARD_TITLES = "./ancestor::div[@data-testid='list']//a";

    private SelenideElement rootElement() {
        return $x("//div[@class='board-canvas']");
    }

    public ElementsCollection getAllListTitles() {
        return rootElement().$$x(ALL_LIST_TITLES).shouldBe(sizeGreaterThan(0));
    }

    public SelenideElement getSpecificList(String listName) {
        return rootElement().$x(format(SPECIFIC_LIST,listName)).shouldBe(exist);
    }

    public SelenideElement getSpecificCardTitleInList(String listName,String cardName) {
        return getSpecificList(listName).$x(format(SPECIFIC_CARD_TITLE_IN_LIST,cardName)).shouldBe(interactable);
    }

    public ElementsCollection getAllCardsTitlesInList(String listName) {
        return getSpecificList(listName).$$x(ALL_CARD_TITLES).shouldBe(sizeGreaterThan(0));
    }
}