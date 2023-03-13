import Moonold.entity.chat.Role;
import Moonold.entity.chat.request.ChatRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class RequestBodyTest {
    @Test
    public void ChatRequestTest() throws Exception{
        ChatRequestBody chatRequestBody = new ChatRequestBody.Builder()
                .model("gpt_3_5")
                .message("system","你是一个猫娘")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(chatRequestBody));
    }

    @Test
    public void postCtxTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        ChatRequestBody chatRequestBody = new ChatRequestBody.Builder()
                .model("gpt_3_5")
                .message("system","你是一个猫娘")
                .ctxLength(3)
                .build();
        chatRequestBody.addNewMessage(Role.user,"你好1");
        System.out.println(objectMapper.writeValueAsString(chatRequestBody));
        chatRequestBody.addNewMessage(Role.assistant,"你好2");
        System.out.println(objectMapper.writeValueAsString(chatRequestBody));
        chatRequestBody.addNewMessage(Role.user,"你好3");
        System.out.println(objectMapper.writeValueAsString(chatRequestBody));
        chatRequestBody.addNewMessage(Role.assistant,"你好4");
        System.out.println(objectMapper.writeValueAsString(chatRequestBody));
    }
}
