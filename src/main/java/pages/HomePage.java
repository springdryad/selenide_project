package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {

  private SelenideElement userIcon = $("#header-details-user-fullname");
  private SelenideElement browseLink = $("#browse_link");
  private SelenideElement createIssueButton = $("#create_link");
  private SelenideElement tempWindowIssueCreated = $("#aui-flag-container");
  private SelenideElement searchField = $("#quickSearchInput");


  public void navigateToHomePage() {
    open("https://jira.hillel.it/secure/Dashboard.jspa");
  }

  public boolean findUserIcon() {
    return userIcon.shouldBe(Condition.visible).isDisplayed();
  }

  public void clickCreateIssue() {
    browseLink.shouldBe(Condition.enabled);
    createIssueButton.click();
  }

  public boolean isIssueCreated() {
    return tempWindowIssueCreated.shouldBe(Condition.exist).isDisplayed();
  }

  public void searchJiraTicket() {
    searchField.shouldBe(Condition.exist).sendKeys("WEBINAR-12560");
  }

  public void enterSearchJiraTicket() {
    searchField.pressEnter();
  }
}
