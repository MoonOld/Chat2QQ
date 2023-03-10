package Moonold.client;

import Moonold.entity.chat.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class OpenAIChatClient {
    private static final String chatApi = "https://api.openai.com/v1/chat/completions";
    private static final MediaType jsonType = MediaType.parse("application/json");
    private static final ObjectMapper objectMapper= new ObjectMapper();
    private static final OkHttpClient client= new OkHttpClient();
    private  final String OpenAISK;

    private  Deque<Message> ctx;
    private int ctxLength;

    public OpenAIChatClient(){

        OpenAISK = System.getenv("OPENAI_SK");
        ctx = new LinkedList<>();
    }

    @SneakyThrows
    public Response post(String api,Object bodyObject){
        Request request = new Request.Builder()
                .url(api)
                .post(RequestBody.create(jsonType,objectMapper.writeValueAsString(bodyObject)))
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer " +OpenAISK)
                .build();
        return client.newCall(request).execute();
    }

    @SneakyThrows
    public Response post(Object bodyObject){
        return post(chatApi,bodyObject);
    }

    public boolean setCtxLength(int Length){
        ctxLength = Length;
        return true;
    }

}
