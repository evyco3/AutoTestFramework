package com.evy.framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * Provides a method to supply a WebDriver instance.
 * Implementations will define how to create or retrieve a WebDriver.
 */
public interface DriverSupplier {

    WebDriver get();
}
