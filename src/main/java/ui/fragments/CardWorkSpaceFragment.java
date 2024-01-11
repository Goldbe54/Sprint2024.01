package ui.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CardWorkSpaceFragment {

    private static final String COMMENT_NAME = "//div[@class='comment-container']";

    public SelenideElement getCommentOnTheCard() {
        return $x(COMMENT_NAME).shouldBe(Condition.visible);
    }
}