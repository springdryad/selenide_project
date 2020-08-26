import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.codeborne.selenide.Configuration;
import pages.CreateIssueWindow;
import pages.HomePage;
import pages.JiraTicketPage;
import pages.LoginPage;

public class JiraTestCases {

  private LoginPage loginPage;
  private HomePage homePage;
  private CreateIssueWindow createIssueWindow;
  private JiraTicketPage jiraTicketPage;

  @BeforeMethod
  public void setUp() {
    Configuration.browser = "chrome";
    loginPage = new LoginPage();
    homePage = new HomePage();
    createIssueWindow = new CreateIssueWindow();
    jiraTicketPage = new JiraTicketPage();
  }


  @Test
  public void successfulLoginTest() {
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
    loginPage.clickLoginButton();

    Assert.assertTrue(homePage.findUserIcon());
  }


  @DataProvider(name = "unsuccessfulLogins")
  public Object[][] createData() {
    return new Object[][]{
        {"RuslanaChumachenko", "wrong_password", "Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз."},
        {"wrong_username", "RuslanaChumachenko", "Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз."},
    };
  }


  @Test(dataProvider = "unsuccessfulLogins")
  public void unsuccessfulLoginTest(String name, String password, String expectedResult) {
    homePage.navigateToHomePage();
    loginPage.enterUserName(name);
    loginPage.enterPassword(password);
    loginPage.clickLoginButton();

    Assert.assertTrue(loginPage.errorMessageIsPresent(expectedResult));
  }


  @Test
  public void createIssueTest() {
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
    loginPage.clickLoginButton();

    homePage.clickCreateIssue();

    createIssueWindow.clearProjectField();
    createIssueWindow.enterProjectField("Webinar");
    createIssueWindow.pressTabAfterProjectField();

    createIssueWindow.isIssueTypeFieldDisplayed();
    createIssueWindow.clearIssueTypeField();
    createIssueWindow.enterIssueTypeField("Task");
    createIssueWindow.pressTabAfterIssueTypeField();

    createIssueWindow.enterSummary("Ruslana's task #3");
    createIssueWindow.clearReporterField();
    createIssueWindow.enterReporterField("RuslanaChumachenko");
    createIssueWindow.selectDescriptionMode();
    createIssueWindow.enterDescription("Sample description");

    createIssueWindow.pressCreateIssueButton();
    Assert.assertTrue(homePage.isIssueCreated());
  }

  @Test
  public void addCommentForTicketTest() {
    //login to home page
    homePage.navigateToHomePage();
    loginPage.enterUserName("RuslanaChumachenko");
    loginPage.enterPassword("RuslanaChumachenko");
    loginPage.clickLoginButton();

    //it is needed to wait until user becomes logged in, otherwise alert appears
    homePage.findUserIcon();
    //search for the ticket
    homePage.searchJiraTicket();
    homePage.enterSearchJiraTicket();

    //add comment
    jiraTicketPage.clickCommentButton();
    jiraTicketPage.enterTextToCommentField();
    jiraTicketPage.clickAddCommentButton();

    //verify that comment is on the page
    Assert.assertTrue(jiraTicketPage.isCommentAdded());

    //delete the comment
    jiraTicketPage.clickOnDeleteComment();
    jiraTicketPage.clickDeleteDialogButton();

    //verify that comment has been deleted
    Assert.assertFalse(jiraTicketPage.isCommentDeleted());
  }


  @AfterMethod
  public void tearDown() {
    Selenide.closeWebDriver();
  }
}
