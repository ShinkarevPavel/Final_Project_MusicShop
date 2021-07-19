package com.shinkarev.finalproject.util;

import com.shinkarev.finalproject.command.Router;
import com.shinkarev.finalproject.command.common.ChangeLocalCommand;
import com.shinkarev.musicshop.pool.ResourceManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.shinkarev.finalproject.command.ParamName.*;

public class LocaleSetter {
    public static Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isInitialised = new AtomicBoolean(false);
    private static final int COUNTRY_INDEX = 0;
    private static final int LANGUAGE_INDEX = 1;
    private static LocaleSetter instance;
    private Map<Locale, ResourceBundle> bundleMap = new HashMap<>();


    private LocaleSetter() {
        List<Locale> localeList = getAllLocales();
        for (Locale locale : localeList) {
            ResourceBundle localization = ResourceBundle.getBundle("localization", locale);
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

    private List<Locale> getAllLocales() {
        List<Locale> result = new ArrayList<>();
        ResourceManager resourceManager = new ResourceManager();
        try {
            Properties properties = resourceManager.getValue("common.properties");
            String property = properties.getProperty(SUPPORTED_LOCALES);
            for (String next : property.split(",")) {
                String country = next.split("-")[COUNTRY_INDEX].trim();
                String language = next.split("-")[LANGUAGE_INDEX].trim();
                result.add(new Locale(country, language));
            }
        } catch (RuntimeException e) {
            logger.error("Error. Reading properties error");
        }
        return result;
    }

    public String getMassage(String key, HttpServletRequest request) {

        String stringLocale = (String) request.getSession().getAttribute(LOCALE);
        if (stringLocale != null) {
            String[] temp = stringLocale.split("-");
            Locale locale = new Locale(temp[0], temp[1]);
            ResourceBundle resourceBundle = bundleMap.get(locale);
            return resourceBundle.getString(key);
        }
        Locale defaultLocale = new Locale("ru","RU");
        ResourceBundle resourceBundle = bundleMap.get(defaultLocale);
        return resourceBundle.getString(key);
    }
}
