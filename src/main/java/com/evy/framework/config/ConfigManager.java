package com.evy.framework.config;

import org.aeonbits.owner.ConfigCache;

/**
 * Retrieves a singleton instance of FrameworkConfig.
 */
public final class ConfigManager {

    private ConfigManager(){}


    public static FrameworkConfig get(){
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }
}
