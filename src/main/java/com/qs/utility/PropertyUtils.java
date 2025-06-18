package com.qs.utility;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static final Logger log = LoggerFactory.getLogger(PropertyUtils.class);

    private final Properties properties = new Properties();
    private final String fileName;

    /**
     * Constructor to load the properties file.
     * @param fileName <fileName as String>
     */
    public PropertyUtils(String fileName) {
        this.fileName = fileName;
        log.info("Loading property file: {}", fileName);
        File file = new File(fileName);

        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                properties.load(fis);
            } catch (IOException e) {
                log.error("Error loading properties file: {}", e.getMessage());
            } finally {
                IOUtils.closeQuietly(fis);
            }
        } else {
            log.error("Properties file not found: {}", fileName);
        }
    }

    /**
     * Gets the property value for the given key.
     * @param key <key as String>
     * @return Value for the key, or null if not found
     */
    public String getProperty(String key) {
        String value = null;
        try {
            value = properties.getProperty(key);
            if (value == null) {
                log.warn("Property key '{}' not found in file '{}'", key, fileName);
            }
        } catch (Exception e) {
            log.error("Error reading property '{}': {}", key, e.getMessage());
        }
        return value;
    }

    /**
     * Gets the property value or returns a default if key not found.
     * @param key <key as String>
     * @param defaultValue Default value to return if key is not found
     * @return Value or default
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
