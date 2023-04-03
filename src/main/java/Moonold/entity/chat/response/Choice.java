package Moonold.entity.chat.response;

import Moonold.entity.chat.Message;
import lombok.Data;

@Data
public class Choice {
    private Message message;
    private String finish_reason;
    private Integer index;

    public String getContent() {
        return message.getContent();
    }
}
