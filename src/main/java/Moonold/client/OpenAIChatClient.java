package Moonold.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.Map;

public class OpenAIChatClient {
    private static final String chatApi = "https://api.openai.com/v1/chat/completions";
    private static final MediaType jsonType = MediaType.parse("application/json");
    private final ObjectMapper objectMapper;
    private final OkHttpClient client;
    private  final String OpenAISK;

    public OpenAIChatClient(){
        client = new OkHttpClient();
        objectMapper = new ObjectMapper();
        OpenAISK = System.getenv("OPENAI_SK");
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



}
