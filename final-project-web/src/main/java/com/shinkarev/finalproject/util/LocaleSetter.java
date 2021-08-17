package com.shinkarev.finalproject.util;

import com.shinkarev.musicshop.pool.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.shinkarev.finalproject.command.ParamName.*;

/**
 * Locale setter provides a message that it receives from resources.
 */

public class LocaleSetter {
    public static Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isInitialised = new AtomicBoolean(false);
    private static LocaleSetter instance;
    private Map<String, ResourceBundle> bundleMap = new HashMap<>();


    private LocaleSetter() {
        List<String> localeList = getAllLocales();
        for (String locale : localeList) {
            ResourceBundle localization = ResourceBundle.getBundle(LOCALIZATION, Locale.forLanguageTag(locale));
            bundleMap.put(locale, localization);
        }
    }

    public static LocaleSetter getInstance() {
        while (instance == null) {
            if (isInitialised.compareAndSet(false, true)) {
                instance = new LocaleSetter();
            }
        }
        return instance;
    }

    /**
     * @return result - collection of locales that are supported by the website
     */

    private List<String> getAllLocales() {
        List<String> result = new ArrayList<>();
        ResourceManager resourceManager = new ResourceManager();
        try {
            Properties properties = resourceManager.getValue(COMMON_PROPERTIES);
            String property = properties.getProperty(SUPPORTED_LOCALES);
            for (String next : property.split(",")) {
                result.add(next.trim());
            }
        } catch (RuntimeException e) {
            logger.error("Error. Reading properties error");
        }
        return result;
    }

    /**
     * @param key - string parameter that used for getting message from resources
     * @param locale - current client's locale
     * @return message according to client's locale
     */

    public String getMassage(String key, String locale) {
        if (locale == null) {
            locale = "ru-RU";
        }
        return bundleMap.get(locale).getString(key);
    }
}
