package com.evy.test;

import com.evy.framework.config.ConfigManager;
import com.evy.framework.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

    @BeforeMethod
    public void setup(){
        Driver.getInstance().init(ConfigManager.get().browserType());
    }


    @AfterMethod
    public void tearDown(){
        Driver.getInstance().quit();
    }



}
