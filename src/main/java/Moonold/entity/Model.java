package Moonold.entity;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashSet;
import java.util.Set;

public class Model {
    public static final String gpt_3_5 = "gpt-3.5-turbo";
    public static final Set<String> modelSet = new HashSet<>();

    static {
        modelSet.add(gpt_3_5);
    }
}
