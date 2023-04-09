package Moonold.entity.chat.response;

import lombok.Data;

@Data
public class ChatErrorBody {
    public ChatError error;
    @Data
    public class ChatError{
        private String message;
        private String type;
        private String param;
        private String code;
    }
}


