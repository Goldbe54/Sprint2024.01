package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CardWorkSpaceFragment {

    private static final String GENERAL_CHECKLISTS_TITLES = ".//h3[contains(@id,'checklist')]";
    private static final String COMMENT_NAME = ".//div[@class='comment-container']";
    private static final String DESCRIPTION_FIELD = ".//div[@class='window-main-col']//div[contains(@Class,'markeddown')]/p";
    private static final String ATTACHMENT_SECTION = ".//div[contains(@class,'js-attachment-list')]";
    private static final String SELECTED_ATTACHMENT = ".//span[text()='%s']/..";
    private static final String SPECIFIC_CHECKLIST_TITLE = ".//h3[text()='%s']";
    private static final String CHECKITEMS_NAMES_IN_CHECKLIST = "./ancestor::div[@Class='checklist']//span[@id]";
    private static final String ALL_LABEL_TITLES = ".//span[@data-testid='card-label']";
    private static final String LABELS_CONTAINER = ".//div[@data-testid='card-back-labels-container']/span";
    private static final String ALL_ATTACHMENT_TITLES = ".//span[@class='attachment-thumbnail-name']";

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

    private ElementsCollection getAllLabelTitles() {
        return rootElement().$$x(ALL_LABEL_TITLES).shouldBe(sizeGreaterThan(0));
    }

    public ElementsCollection getAllAttachmentTitles(){
        return rootElement().$$x(ALL_ATTACHMENT_TITLES).shouldBe(sizeGreaterThan(0));
    }

    public List<String> getAttachmentTitles(){
        ElementsCollection allAttachmentTitles = getAllAttachmentTitles();
        return ElementUtil.getListOfStrings(allAttachmentTitles);
    }

    public List<String> getLabelTitles() {
        ElementsCollection allLabelTitlesTitles = getAllLabelTitles();
        return ElementUtil.getListOfStrings(allLabelTitlesTitles);
    }

    public boolean isLabelsAbsent() {
        try {
            rootElement().$x(LABELS_CONTAINER).shouldBe(disappear);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public SelenideElement getSelectedAttachment(String nameAttachment) {
        return getAttachmentSection().$x(format(SELECTED_ATTACHMENT, nameAttachment)).shouldBe(visible);
    }

    public SelenideElement getSpecificChecklistName(String checklistName) {
        return rootElement().$x(format(SPECIFIC_CHECKLIST_TITLE, checklistName)).shouldBe(visible);
    }

    public ElementsCollection getAllChecklistTitles() {
        return rootElement().$$x(GENERAL_CHECKLISTS_TITLES).shouldBe(sizeGreaterThan(0));
    }

    public List<String> getChecklistTitles() {
        ElementsCollection allChecklistTitles = getAllChecklistTitles();
        return ElementUtil.getListOfStrings(allChecklistTitles);
    }

    public List<String> getListCheckitemsTitlesInChecklist(String checklistName) {
        ElementsCollection collection = getSpecificChecklistName(checklistName).$$x(CHECKITEMS_NAMES_IN_CHECKLIST).shouldBe(sizeGreaterThan(0));
        return ElementUtil.getListOfStrings(collection);
    }
}