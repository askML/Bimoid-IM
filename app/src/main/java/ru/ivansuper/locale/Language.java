package ru.ivansuper.locale;

public class Language {
    public String NAME;
    public String LANGUAGE;
    public String AUTHOR;
    public boolean internal;

    public Language(String name, String language, String author, boolean internal) {
        NAME = name;
        LANGUAGE = language;
        AUTHOR = author;
        this.internal = internal;
    }
}
