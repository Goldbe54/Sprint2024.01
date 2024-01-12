package ui.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CardWorkSpaceFragment {

    private static final String COMMENT_NAME = "//div[@class='comment-container']";
    private static final String ATTACHMENT_SECTION = "//div[contains(@class,'js-attachment-list')]";
    private static final String SELECTED_ATTACHMENT = ".//span[text()='%s']/..";

    public SelenideElement getCommentOnTheCard() {
        return $x(COMMENT_NAME).shouldBe(Condition.visible);
    }

    private SelenideElement getAttachmentSection() {
        return $x(ATTACHMENT_SECTION).shouldBe(Condition.visible);
    }

    public SelenideElement getSelectedAttachment(String nameAttachment) {
        return getAttachmentSection().$x(format(SELECTED_ATTACHMENT,nameAttachment)).shouldBe(Condition.visible);
    }
}