package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CardWorkSpaceFragment {

    private static final String COMMENT_NAME = ".//div[@class='comment-container']";
    private static final String DESCRIPTION_FIELD =".//div[@class='window-main-col']//div[contains(@Class,'markeddown')]/p";
    private static final String ATTACHMENT_SECTION = ".//div[contains(@class,'js-attachment-list')]";
    private static final String SELECTED_ATTACHMENT = ".//span[text()='%s']/..";
    private static final String SPECIFIC_CHECKLIST_TITLE = ".//h3[text()='%s']";
    private static final String CHECKITEMS_NAMES_IN_CHECKLIST = "./ancestor::div[@Class='checklist']//span[@id]";
    private SelenideElement rootElement() {
        return $x("//div[contains(@class,'card-detail-window')]");
    }

    public SelenideElement getCardDescription() {
        return rootElement().$x(DESCRIPTION_FIELD).shouldBe(exist);
    }

    public SelenideElement getCommentOnTheCard() {
        return rootElement().$x(COMMENT_NAME).shouldBe(visible);
    }

    private SelenideElement getAttachmentSection() {
        return rootElement().$x(ATTACHMENT_SECTION).shouldBe(visible);
    }

    public SelenideElement getSelectedAttachment(String nameAttachment) {
        return getAttachmentSection().$x(format(SELECTED_ATTACHMENT, nameAttachment)).shouldBe(visible);
    }

    public SelenideElement getSpecificChecklistName(String checklistName) {
        return rootElement().$x(format(SPECIFIC_CHECKLIST_TITLE,checklistName)).shouldBe(visible);
    }

    public List<String> getListCheckitemsTitlesInChecklist(String checklistName) {
        ElementsCollection collection = getSpecificChecklistName(checklistName).$$x(CHECKITEMS_NAMES_IN_CHECKLIST).shouldBe(sizeGreaterThan(0));
        return ElementUtil.getListOfStrings(collection);
    }
}