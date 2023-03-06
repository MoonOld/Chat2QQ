package Moonold.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Model {
    gpt_3_5;

    @JsonValue
    public String getName() {
        switch (this) {
            case gpt_3_5:
                return "gpt-3.5-turbo";
        }
        return "";
    }
    public static  Model getModel(String model){
        for(Model toCheck : Model.values()){
            if(toCheck.name().equals(model)){
                return toCheck;
            }
        }
        throw new IllegalArgumentException("Invalid Model :"+model);
    }
}
