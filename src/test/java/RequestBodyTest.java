import Moonold.entity.chat.ChatRequestBody;
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
}
