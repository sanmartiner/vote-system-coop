package com.coop.votingsystem.config.interceptors;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
    public class UserRequestContext {
        private final ThreadLocal<Locale> threadLocalLocale = new ThreadLocal<>();
        private final ThreadLocal<String> threadLocalRequestId = new ThreadLocal<>();
        private final ThreadLocal<String> threadLocalPortCode = new ThreadLocal<>();
        private final ThreadLocal<String> threadLocalUserId = new ThreadLocal<>();
        private final ThreadLocal<String> threadLocalAcceptLanguage = new ThreadLocal<>();
        private final ThreadLocal<String> threadLocalAcceptTimezone = new ThreadLocal<>();

        public Locale getLocale() {
            return threadLocalLocale.get();
        }

        public void setLocale(final Locale locale) {
            threadLocalLocale.set(locale);
        }

        public void removeLocale() {
            threadLocalLocale.remove();
        }

        public String getRequestId(){
            return threadLocalRequestId.get();
        }

        public void setRequestId(final String requestId) {
            threadLocalRequestId.set(requestId);
        }

        public void removeRequestId() {
            threadLocalRequestId.remove();
        }

        public String getPortCode(){
            return threadLocalPortCode.get();
        }

        public void setPortCode(final String portCode) {
            threadLocalPortCode.set(portCode);
        }

        public void removePortCode() {
            threadLocalPortCode.remove();
        }

        public String getUserId(){
            return threadLocalUserId.get();
        }

        public void setUserId(final String userId) {
            threadLocalUserId.set(userId);
        }

        public void removeUserId() {
            threadLocalUserId.remove();
        }

        public String getAcceptLanguage(){
            return threadLocalAcceptLanguage.get();
        }

        public void setAcceptLanguage(final String acceptLanguage) {
            threadLocalAcceptLanguage.set(acceptLanguage);
        }

        public void removeAcceptLanguage() {
            threadLocalAcceptLanguage.remove();
        }

        public String getAcceptTimezone(){
            return threadLocalAcceptTimezone.get();
        }

        public void setAcceptTimezone(final String acceptTimezone) {
            threadLocalAcceptTimezone.set(acceptTimezone);
        }

        public void removeAcceptTimezone() {
            threadLocalAcceptTimezone.remove();
        }
    }


