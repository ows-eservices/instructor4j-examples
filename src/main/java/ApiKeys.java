package main.java;

import static solutions.own.instructor4j.util.Utils.getOrDefault;

public class ApiKeys {
    public static final String OPENAI_API_KEY_NOT_PROVIDED = "none";
    public static final String OPENAI_API_KEY = getOrDefault(getEnv("OPENAI_API_KEY"),
        OPENAI_API_KEY_NOT_PROVIDED);

    protected static String getEnv(String key) {
        return System.getenv(key);
    }
}