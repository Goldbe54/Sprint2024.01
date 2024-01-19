package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$x;

public class BoardMenuFragment {

    private static final String BACK_BUTTON = ".//a[contains(@class,'back')]";
    private static final String ARCHIVE_BUTTON = ".//a[contains(@Class,'archive')]";
    private static final String ARCHIVED_ITEM_TITLES = ".//span[@Class='list-card-title js-card-name']";

    private SelenideElement rootElement() {
        return $x("//div[@class='board-menu-tab-content-v2']");
    }

    public SelenideElement getBackButton() {
        return rootElement().$x(BACK_BUTTON).shouldBe(interactable);
    }

    public SelenideElement getArchiveButton() {
        return rootElement().$x(ARCHIVE_BUTTON).shouldBe(interactable);
    }

    public ElementsCollection getAllArchivedTitles() {
        return rootElement().$$x(ARCHIVED_ITEM_TITLES).shouldBe(sizeGreaterThan(0));
    }
}
