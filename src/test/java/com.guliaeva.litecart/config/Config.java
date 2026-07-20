package com.guliaeva.litecart.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Config.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                throw new IllegalStateException("config.properties file not found");
            }

            properties.load(inputStream);

        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
    }

    private Config() {
    }

    public static String getAdminUrl() {
        return getRequiredProperty("admin.url");
    }

    public static String getAdminUsername() {
        return getRequiredProperty("admin.username");
    }

    public static String getAdminPassword() {
        return getRequiredProperty("admin.password");
    }

    public static long getTimeoutSeconds() {
        return Long.valueOf(getRequiredProperty("timeOutInSec"));
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    private static String getRequiredProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Required property is missing: " + key);
        }

        return value;
    }
}