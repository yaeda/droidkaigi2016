package io.github.droidkaigi.confsched.util;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class LocaleUtilsTest {

    @Test
    public void testCurrentLanguageId() throws Exception {
        Context context = InstrumentationRegistry.getContext();
        // is not null value.
        assertThat(LocaleUtil.getCurrentLanguageId(context)).isNotNull();

        PrefUtil.put(context, PrefUtil.KEY_CURRENT_LANGUAGE_ID, "ja");
        // eq to languageID is put in SharedPreferences.
        assertThat(LocaleUtil.getCurrentLanguageId(context)).isEqualTo("ja");

        PrefUtil.remove(context, PrefUtil.KEY_CURRENT_LANGUAGE_ID);
        String defaultLanguage = Locale.getDefault().getLanguage().toLowerCase();
        if (Arrays.asList(LocaleUtil.SUPPORT_LANG).contains(defaultLanguage)) {
            // eq to Locale.getDefault().getLanguage() when it is supported
            assertThat(LocaleUtil.getCurrentLanguageId(context)).isEqualTo(defaultLanguage);
        } else {
            // eq to "en" when Locale.getDefault().getLanguage() is not supported
            assertThat(LocaleUtil.getCurrentLanguageId(context)).isEqualTo("en");
        }
    }

}
