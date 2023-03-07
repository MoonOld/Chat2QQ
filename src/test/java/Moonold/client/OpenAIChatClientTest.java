package Moonold.client;

import Moonold.entity.chat.ChatRequestBody;
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
        System.out.println(response.body().string());
    }

}