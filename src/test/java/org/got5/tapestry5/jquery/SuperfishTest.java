package org.got5.tapestry5.jquery;

import org.got5.tapestry5.jquery.test.SeleniumTestCase2;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class SuperfishTest extends SeleniumTestCase2 {

    private static final String SUPERFISH_MENU_SELECTOR = "//ul[@id='superfish']";

    private static final String SUPERFISH_MENU_FIRST_ITEM_SELECTOR = SUPERFISH_MENU_SELECTOR + "/li[1]/a";

    private static final String SUPERFISH_MENU_THIRD_ITEM_SELECTOR = SUPERFISH_MENU_SELECTOR + "/li[3]/a";

    @Test
    public void testSuperfishComponent(){

        open("/SuperFish");

        new Wait()
        {
            @Override
            public boolean until()
            {
                return isElementPresent(SUPERFISH_MENU_SELECTOR + "[contains(@class,'sf-menu')]");
            }

        }.wait("The ul element should have the sf-menu class : " + getAttribute(SUPERFISH_MENU_SELECTOR + "@class"), JQueryTestConstants.TIMEOUT);

        new Actions(webDriver).moveToElement(
                webDriver.findElement(convertLocator(SUPERFISH_MENU_FIRST_ITEM_SELECTOR))
        ).perform();

        new Wait()
        {
            @Override
            public boolean until()
            {
                return getAttribute(SUPERFISH_MENU_SELECTOR + "/li[1]@class").contains("sfHover");
            }
        }.wait("The ul element should have the sfHover class : " + getAttribute(SUPERFISH_MENU_SELECTOR + "[contains(@class,'sf-menu')]/li[1]@class"), JQueryTestConstants.TIMEOUT);

        new Actions(webDriver).moveToElement(
                webDriver.findElement(convertLocator(SUPERFISH_MENU_THIRD_ITEM_SELECTOR))
        ).perform();

        new Wait()
        {
            @Override
            public boolean until()
            {
                return !getAttribute(SUPERFISH_MENU_SELECTOR + "/li[1]@class").contains("sfHover");
            }
        }.wait("The ul element should not have the sfHover class : " + getAttribute(SUPERFISH_MENU_SELECTOR + "[contains(@class,'sf-menu')]/li[1]@class"), JQueryTestConstants.TIMEOUT);
    }

}
