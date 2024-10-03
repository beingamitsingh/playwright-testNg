package org.ecommerce.lambdatest;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import framework.Config;
import framework.Runner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdditionFormTest extends Runner {

    @BeforeClass
    public void beforeClass() {
        String web_url = Config.getProperty("FORM_WEB_URL");
        page.navigate(web_url);
    }

    @DataProvider(name = "valuesToAdd")
    public Object[][] messageInputs() {
        return new Object[][] {
                { 13, 9 },
                { 99, 9 },
                { 71, 788 },
                { 55, 0 },
                { 37, 12 }
        };
    }

    @Test(dataProvider = "valuesToAdd")
    public void validate_isMessagePresent(int value1, int value2) {
        Locator inputBox1 = page.locator("input#sum1");
        Locator inputBox2 = page.locator("input#sum2");
        inputBox1.clear();
        inputBox1.type(Integer.toString(value1));
        inputBox2.clear();
        inputBox2.type(Integer.toString(value2));

        Locator getSumButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Sum"));
        getSumButton.click();

        Locator actualSumLabel = page.locator("#addmessage");
        String expectedSum = Integer.toString(value1 + value2);
        assertThat(actualSumLabel).hasText(expectedSum);
    }
}
