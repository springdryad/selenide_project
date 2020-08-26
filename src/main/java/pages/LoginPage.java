package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

  private SelenideElement userNameInput = $(By.id("login-form-username"));
  private SelenideElement passwordInput = $(By.id("login-form-password"));
  private SelenideElement loginButton = $(By.id("login"));

  public void enterUserName(String username) {
    userNameInput.setValue(username);
  }

  public void enterPassword(String password) {
    passwordInput.setValue(password);
  }


  public void clickLoginButton() {
    loginButton.click();
  }

  public boolean errorMessageIsPresent(String message) {
    return $(By.xpath("//*[contains(text(), \"" + message + "\")]")).shouldBe(Condition.visible).isDisplayed();
  }

}
