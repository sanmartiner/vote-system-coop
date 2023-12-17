package com.coop.votingsystem.config.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class AcceptLanguageInterceptor implements HandlerInterceptor {
    public static final String DEFAULT_ACCEPT_LANGUAGE = "en-US";
    private static final Locale DEFAULT_LOCALE = Locale.US;
    private static final String LOCALE_PORTUGUESE = "pt";
    private static final String LOCALE_SPANISH = "es";

    private final List<Locale> supportedLocales = Arrays.asList(
            Locale.ENGLISH,
            Locale.US,
            new Locale(LOCALE_PORTUGUESE),
            new Locale(LOCALE_PORTUGUESE, "BR"),
            new Locale(LOCALE_SPANISH),
            new Locale(LOCALE_SPANISH, "ES")
    );

    private final UserRequestContext userRequestContext;

    @Autowired
    public AcceptLanguageInterceptor(final UserRequestContext userRequestContext) {
        this.userRequestContext = userRequestContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String headerAcceptanceLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        String acceptanceLanguage = DEFAULT_ACCEPT_LANGUAGE;
        Locale locale = DEFAULT_LOCALE;

        try {
            if (headerAcceptanceLanguage != null && !headerAcceptanceLanguage.isBlank()) {
                final var foundLocale = Locale.lookup(Locale.LanguageRange.parse(headerAcceptanceLanguage),
                        this.supportedLocales);
                if (foundLocale != null) {
                    acceptanceLanguage = headerAcceptanceLanguage;
                    locale = foundLocale;
                }
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }

        this.userRequestContext.setLocale(locale);
        this.userRequestContext.setAcceptLanguage(acceptanceLanguage);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        this.userRequestContext.removeLocale();
        this.userRequestContext.removeAcceptLanguage();
    }

}