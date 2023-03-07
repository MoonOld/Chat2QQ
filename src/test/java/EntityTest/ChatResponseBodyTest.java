package EntityTest;

import Moonold.entity.chat.response.ChatResponseBody;
import Moonold.entity.chat.response.Choice;
import Moonold.entity.chat.response.Usage;
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
}
