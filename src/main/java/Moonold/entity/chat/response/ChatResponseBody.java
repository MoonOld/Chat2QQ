package Moonold.entity.chat.response;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponseBody {
    private String id;
    private String object;
    private Integer created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    public String getContents(){
        StringBuilder sb = new StringBuilder();
        for(Choice choice: choices){
            sb.append(choice.getContent());
        }
        return sb.toString();
    }
}
