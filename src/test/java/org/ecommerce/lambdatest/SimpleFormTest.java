package org.ecommerce.lambdatest;

import com.microsoft.playwright.Locator;
import framework.Config;
import framework.Runner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SimpleFormTest extends Runner {

    @BeforeClass
    public void beforeClass() {
        String web_url = Config.getProperty("FORM_WEB_URL");
        page.navigate(web_url);
    }

    @DataProvider(name = "messages")
    public Object[][] messageInputs() {
        return new Object[][] {
                { "Hey Tester" },
                { "Do you see another message" },
                { "Nice to meet you" },
                { "Lets continue playwright practice" }
        };
    }


    @Test(dataProvider = "messages")
    public void validate_isMessagePresent(String message) {
        Locator inputBox = page.locator("input#user-message");
        Locator getValueButton = page.locator("id=showInput");
        inputBox.clear();
        inputBox.type(message);
        getValueButton.click();

        Locator actualMessageLabel = page.locator("#message");
        String actualMessage = actualMessageLabel.textContent();
        System.out.println("Text present on screen: " + actualMessage);
        assertThat(actualMessageLabel).hasText(message);
    }
}
