package com.evy.framework.driver;

import com.evy.framework.utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the creation of WebDriver instances based on the specified BrowserType.
 */
public final class DriverManager {

    private static final Map<BrowserType, DriverSupplier> DRIVER_SUPPLIER_MAP = new HashMap<>();

    static {
        DRIVER_SUPPLIER_MAP.put(BrowserType.CHROME, new ChromeDriverSupplier());
        DRIVER_SUPPLIER_MAP.put(BrowserType.FIREFOX, new FirefoxDriverSupplier());
        DRIVER_SUPPLIER_MAP.put(BrowserType.EDGE, new EdgeDriverSupplier());
        DRIVER_SUPPLIER_MAP.put(BrowserType.EXPLORER, new ExplorerDriverSupplier());
        DRIVER_SUPPLIER_MAP.put(BrowserType.OPERA, new OperaDriverSupplier());
        DRIVER_SUPPLIER_MAP.put(BrowserType.SAFARI, new SafariDriverSupplier());
    }

    private DriverManager() {}

    public static WebDriver getDriver(BrowserType browserType) {
        try {
            DriverSupplier driverSupplier = DRIVER_SUPPLIER_MAP.get(browserType);
            if (driverSupplier != null) {
                LoggerUtils.info(DriverManager.class, "Creating WebDriver for browser: " + browserType);
                return driverSupplier.get();
            } else {
                LoggerUtils.warn(DriverManager.class, "Unsupported browser type: " + browserType);
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
        } catch (Exception e) {
            LoggerUtils.error(DriverManager.class, "Failed to create WebDriver for browser type: " + browserType, e);
            throw new RuntimeException("Failed to create WebDriver for browser type: " + browserType, e);
        }
    }

    private static final class ChromeDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver get() {
            return WebDriverManager.chromedriver()
                    .capabilities(new ChromeOptions().addArguments("--headless"))
                    .create();
        }
    }

    private static final class FirefoxDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver get() {
            return WebDriverManager.firefoxdriver()
                    .capabilities(new FirefoxOptions().addArguments("--headless"))
                    .create();
        }
    }

    private static final class EdgeDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver get() {
            return WebDriverManager.edgedriver()
                    .capabilities(new EdgeOptions().addArguments("--headless"))
                    .create();
        }
    }

    private static final class OperaDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver get() {
            return WebDriverManager.operadriver().create();
        }
    }

    private static final class SafariDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver get() {
            return WebDriverManager.safaridriver().create();
        }
    }

    private static final class ExplorerDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver get() {
            return WebDriverManager.iedriver().create();
        }
    }
}
