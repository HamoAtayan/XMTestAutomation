package com.xm.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
    private final Logger log = LoggerFactory.getLogger(Config.class);

    private PropertiesConfiguration properties;

    public Config(String path) {
        try {
            properties = new PropertiesConfiguration("src/test/resources/" + path);
        } catch (ConfigurationException ex) {
            log.info(ex.getMessage());
        }
    }

    public String get(String name) {
        return (String) properties.getProperty(name);
    }
}
