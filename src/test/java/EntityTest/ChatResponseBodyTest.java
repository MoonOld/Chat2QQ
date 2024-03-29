package EntityTest;

import Moonold.entity.chat.response.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChatResponseBodyTest {
    @Test
    public void chatResponseDeserializedTest() throws Exception{
        String toTest = "{\"id\":\"chatcmpl-6rTRhKsRWeURSyjXAtpLJd3M3Vbrs\",\"object\":\"chat.completion\",\"created\":1678202593,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":23,\"completion_tokens\":19,\"total_tokens\":42},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"你好，有什么可以帮助你的吗？\"},\"finish_reason\":\"stop\",\"index\":0}]}\n";
        ObjectMapper objectMapper = new ObjectMapper();
        ChatResponseBody chatResponseBody = objectMapper.readValue(toTest.getBytes(), ChatResponseBody.class);
        System.out.println(chatResponseBody.getContents());
    }
    @Test
    public void ErrorResponseTest() throws Exception {
        String toTest = "{\n" +
                "  \"error\": {\n" +
                "    \"message\": \"That model is currently overloaded with other requests. You can retry your request," +
                " or contact us through our help center at help.openai.com if the error persists. " +
                "(Please include the request ID a498e46df427eafdae19e3ca684b0919 in your message.)\",\n" +
                "    \"type\": \"server_error\",\n" +
                "    \"param\": null,\n" +
                "    \"code\": null\n" +
                "  }\n" +
                "}\n"
                ;
//        String toTest = "{\"error\":{\"message\":\"fuck you\",\"type\":\"server_error\",\"param\":null,\"code\":null}}\n";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ChatResponseBody chatResponseBody = objectMapper.readValue(toTest.getBytes(), ChatResponseBody.class);
            System.out.println(chatResponseBody.getContents());
        } catch (Exception e) {
            ChatErrorBody chatErrorBody = objectMapper.readValue(toTest.getBytes(), ChatErrorBody.class);
            System.out.println(chatErrorBody.getError().toString());
        }
        System.out.println("end");

    }
}
