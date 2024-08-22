package com.evy.framework.driver;

import com.evy.framework.config.ConfigManager;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

/**
 * Manages the WebDriver instance for each thread, including initialization and cleanup.
 */
public final class Driver {

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();
    private static final Driver INSTANCE = new Driver();

    private Driver() {
        // Private constructor for Singleton pattern
    }

    /**
     * Provides the singleton instance of the Driver class.
     *
     * @return The singleton instance of Driver.
     */
    public static Driver getInstance() {
        return INSTANCE;
    }

    /**
     * Initializes the WebDriver for the current thread with the specified browser type.
     *
     * @param browserType The type of browser to initialize.
     */
    public void init(BrowserType browserType) {
        if (browserType == null) {
            LoggerUtils.warn(Driver.class,"BrowserType cannot be null");
            throw new IllegalArgumentException("BrowserType cannot be null");
        }

        try {
            LoggerUtils.info(Driver.class, "Initializing WebDriver for browser: " + browserType);
            WebDriver driver = DriverManager.getDriver(browserType);
            THREAD_LOCAL.set(configure(driver));
            LoggerUtils.info(Driver.class, "WebDriver initialized successfully for browser: " + browserType);
        } catch (Exception e) {
            LoggerUtils.error(Driver.class, "Failed to initialize WebDriver for browser: " + browserType, e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    /**
     * Quits the WebDriver for the current thread and removes it from the ThreadLocal.
     */
    public void quit() {
        try {
            WebDriver driver = THREAD_LOCAL.get();
            if (driver != null) {
                driver.quit();
                THREAD_LOCAL.remove();
                LoggerUtils.info(Driver.class, "WebDriver quit and removed from ThreadLocal");
            } else {
                LoggerUtils.warn(Driver.class, "No WebDriver instance found to quit");
            }
        } catch (Exception e) {
            LoggerUtils.error(Driver.class, "Failed to quit WebDriver", e);
            throw new RuntimeException("Failed to quit WebDriver", e);
        }
    }

    /**
     * Retrieves the WebDriver instance for the current thread.
     *
     * @return The WebDriver instance.
     */
    public WebDriver get() {
        WebDriver driver = THREAD_LOCAL.get();
        if (driver == null) {
            LoggerUtils.warn(Driver.class, "No WebDriver instance found for the current thread");
        }
        return driver;
    }

    /**
     * Configures the WebDriver instance with timeouts and initial URL.
     *
     * @param driver The WebDriver instance to configure.
     * @return The configured WebDriver instance.
     */
    private WebDriver configure(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.get().pageLoadTime()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.get().implicitTime()));
        driver.manage().deleteAllCookies();
        driver.get(ConfigManager.get().url());
        LoggerUtils.info(Driver.class, "Configured WebDriver with page load timeout and implicit wait");
        return driver;
    }
}
