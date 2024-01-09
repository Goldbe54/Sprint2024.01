package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.CollectionElement;

import static com.codeborne.selenide.Selenide.$x;

public class BoardWorkSpace {
    private String ALL_LIST_TITLES = ".//h2[@data-testid='list-name']";

    private SelenideElement rootElement(){
        return $x("//div[@id='content-wrapper']");
    }

    public ElementsCollection allListTitles(){
        return rootElement().$$x(ALL_LIST_TITLES);
    }
}
