package tk.aizydorczyk.sns.operation.domain.contra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Scope;
import tk.aizydorczyk.sns.operation.domain.config.SeleniumProperties;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@TestComponent
@Scope(SCOPE_PROTOTYPE)
public class SignInPage {
    private final SeleniumProperties seleniumProperties;
    private final WebDriver driver;

    private WebElement passwordInput;
    private WebElement signInButton;
    private WebElement loginInput;

    public SignInPage(WebDriver driver,
                      SeleniumProperties seleniumProperties) {
        this.seleniumProperties = seleniumProperties;
        this.driver = driver;
    }

    public void open() {
        driver.get(seleniumProperties.getApplicationUrl());
        final WebDriverWait wait = new WebDriverWait(driver, seleniumProperties.getDefaultWaitingTime());
        this.loginInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("LoginName")));
        this.passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("LoginPassword")));
        this.signInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("LoginLoginButton")));
    }

    public void loginAsDefaultUser() {
        loginInput.sendKeys(seleniumProperties.getDefaultUserName());
        passwordInput.sendKeys(seleniumProperties.getDefaultPassword());
        signInButton.click();
    }
}
