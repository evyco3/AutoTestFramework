package com.evy.framework.pages;

import com.evy.framework.pages.account.NavigateToAuthentication;
import com.evy.framework.pages.product.NavigateToProductDropdown;

public class HomePage extends BasePage{

    private static final HomePage INSTANCE=new HomePage();


    public static HomePage getInstance(){
        return INSTANCE;
    }

    public NavigateToAuthentication navigateToAuthentication(){
        return new NavigateToAuthentication();
    }

    public NavigateToProductDropdown navigateToProductDropdown(){
        return new NavigateToProductDropdown();
    }
}
