package com.shinkarev.musicshop.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceManager {
    private static final ResourceManager instance = new ResourceManager();
    private Properties properties = new Properties();

    static ResourceManager getInstance() {
        return instance;
    }

   public Properties getValue(String propertyPath) {
        try (InputStream poolProperties = getClass().getClassLoader().getResourceAsStream(propertyPath)) {
            properties.load(poolProperties);
        } catch (IOException e) {
            throw new RuntimeException("Fatal. Reading properties error ", e);
        }
        return properties;
    }
}
