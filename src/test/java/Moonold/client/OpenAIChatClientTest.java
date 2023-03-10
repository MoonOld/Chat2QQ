package Moonold.client;

import Moonold.entity.chat.ChatRequestBody;
import Moonold.entity.chat.response.ChatResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.junit.Test;

public class OpenAIChatClientTest{
    @Test
    public void postTest() throws Exception{
        ChatRequestBody chatRequestBody = new ChatRequestBody.Builder()
                .model("gpt_3_5")
                .message("system","你是一个猫娘")
                .build();
        chatRequestBody.addNewMessage("user","你好");
        OpenAIChatClient openAIChatClient = new OpenAIChatClient();
        Response response = openAIChatClient.post(chatRequestBody);

        ObjectMapper objectMapper = new ObjectMapper();
        ChatResponseBody chatResponseBody = objectMapper.readValue(response.body().string(), ChatResponseBody.class);
        System.out.println(chatResponseBody.getContents());
    }

}