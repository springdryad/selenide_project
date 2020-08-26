package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CreateIssueWindow {

  private SelenideElement projectField = $("#project-field");
  private SelenideElement issueTypeField = $("#issuetype-field");
  private SelenideElement summaryField = $("#summary");
  private SelenideElement reporterField = $("#reporter-field");
  private SelenideElement summaryMode = $(byXpath("//ul/li[@data-mode='source']"));
  private SelenideElement issueDescription = $("#description");
  private SelenideElement createIssueButton = $("#create-issue-submit");


  public void clearProjectField() {
    projectField.waitUntil(Condition.enabled, 10000).clear();
  }

  public void enterProjectField(String text) {
    projectField.setValue(text);
  }

  public void pressTabAfterProjectField() {
    projectField.pressTab();
  }

  public boolean isIssueTypeFieldDisplayed() {
    return issueTypeField.shouldBe(Condition.visible).isDisplayed();
  }

  public void clearIssueTypeField() {
    issueTypeField.clear();
  }

  public void enterIssueTypeField(String text) {
    issueTypeField.setValue(text);
  }

  public void pressTabAfterIssueTypeField() {
    issueTypeField.pressTab();
  }

  public void enterSummary(String text) {
    summaryField.shouldBe(Condition.visible).setValue(text);
  }

  public void clearReporterField() {
    reporterField.shouldBe(Condition.exist).clear();
  }

  public void enterReporterField(String text) {
    reporterField.setValue(text);
  }

  public void selectDescriptionMode() {
    summaryMode.click();
  }

  public void enterDescription(String text) {
    issueDescription.setValue(text);
  }

  public void pressCreateIssueButton() {
    createIssueButton.click();
  }


}
