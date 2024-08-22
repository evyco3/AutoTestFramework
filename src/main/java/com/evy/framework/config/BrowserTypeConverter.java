package com.evy.framework.config;

import com.evy.framework.driver.BrowserType;
import com.evy.framework.utils.LoggerUtils;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * Converting String type to BrowserType
 */

public class BrowserTypeConverter implements Converter<BrowserType> {
    @Override
    public BrowserType convert(Method method, String browserType) {
        try {
            return BrowserType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException e) {
            LoggerUtils.error(getClass(),"Invalid browserType: "+browserType,e);
            throw new RuntimeException("Invalid browser type: " + browserType, e);
        }
    }
}
