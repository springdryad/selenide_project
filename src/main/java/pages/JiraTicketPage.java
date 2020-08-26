package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class JiraTicketPage {

  private String comment = "Comment 1 from Ruslana";
  private SelenideElement commentButton = $("#footer-comment-button");
  private SelenideElement commentField = $("#comment");
  private SelenideElement addCommentButton = $("#issue-comment-add-submit");
  private SelenideElement addedComment = $(byXpath("//p[contains(text(),'" + comment + "')]"));
  private SelenideElement deleteDialogButton = $("#comment-delete-submit");
  private SelenideElement deleteCommentButton = $(byXpath("//p[contains(text(),'" + comment + "')]//parent::div//parent::div//a[contains(@id, 'delete')]"));


  public void clickCommentButton() {
    commentButton.shouldBe(Condition.visible).click();
  }

  public void enterTextToCommentField() {
    commentField.setValue(comment);
  }

  public void clickAddCommentButton() {
    addCommentButton.shouldBe(Condition.visible).click();
  }

  public boolean isCommentAdded() {
    return addedComment.shouldBe(Condition.exist).isDisplayed();
  }

  public void clickOnDeleteComment() {
    deleteCommentButton.click();
  }

  public void clickDeleteDialogButton() {
    deleteDialogButton.click();
  }

  public boolean isCommentDeleted() {
    return addedComment.shouldBe(Condition.disappear).isDisplayed();
  }

}
