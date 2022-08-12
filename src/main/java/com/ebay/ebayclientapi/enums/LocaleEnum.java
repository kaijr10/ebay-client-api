package com.ebay.ebayclientapi.enums;

public enum LocaleEnum {
    US("en_US");

    public final String locale;

    private LocaleEnum(String locale) {
        this.locale = locale;
    }
}
