package com.dominikpiotrowski.eventSearcher.configs;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class Provider implements I18NProvider {

@Override
public List<Locale> getProvidedLocales() {
        return Collections.unmodifiableList(
        Arrays.asList(Locale.ENGLISH, Locale.forLanguageTag("pl_PL")));
        }

@Override
public String getTranslation(String key, Locale locale, Object... params) {
        if (Locale.ENGLISH.equals(locale)) {
        if (key.equals("root.navigate_to_component")) {
        return "Navigate to another component";
        }
        } else if (Locale.forLanguageTag("pl_PL").equals(locale)) {
        if (key.equals("root.navigate_to_component")) {
        return "Navigate to another component";
        }
        }
        return null;
        }
}