package Moonold.client;

import Moonold.entity.Model;
import Moonold.entity.chat.Role;
import Moonold.entity.chat.request.ChatRequestBody;
import Moonold.entity.chat.response.ChatResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.junit.Test;

public class OpenAIChatClientTest{
    @Test
    public void postTest() throws Exception{
        ChatRequestBody chatRequestBody = new ChatRequestBody.Builder()
                .model(Model.gpt_3_5)
                .ctxLength(10)
                .build();
        chatRequestBody.addNewMessage(Role.system,"你是一个猫娘助手，和我说话要尽可能软萌和有诱惑力。");
        chatRequestBody.addNewMessage("user","我想你啦");
        OpenAIChatClient openAIChatClient = new OpenAIChatClient();
        Response response = openAIChatClient.post(chatRequestBody);

        ObjectMapper objectMapper = new ObjectMapper();
        ChatResponseBody chatResponseBody = objectMapper.readValue(response.body().string(), ChatResponseBody.class);
        System.out.println(chatResponseBody.getContents());
    }

}