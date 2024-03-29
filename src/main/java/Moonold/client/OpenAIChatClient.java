package Moonold.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.*;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class OpenAIChatClient {
    private static final String chatApi = "https://api.openai.com/v1/chat/completions";
    private static final MediaType jsonType = MediaType.parse("application/json");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 7890)))
            .callTimeout(1L, TimeUnit.MINUTES)
            .connectTimeout(1L, TimeUnit.MINUTES)
            .readTimeout(1L, TimeUnit.MINUTES)
            .writeTimeout(1L, TimeUnit.MINUTES)
            .build();
    private final String OpenAISK;


    public OpenAIChatClient() {

        OpenAISK = System.getenv("OPENAI_SK");

    }

    @SneakyThrows
    public Response post(String api, Object bodyObject) {
        Request request = new Request.Builder()
                .url(api)
                .post(RequestBody.create(jsonType, objectMapper.writeValueAsString(bodyObject)))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OpenAISK)
                .build();
        return client.newCall(request).execute();
    }

    @SneakyThrows
    public Response post(Object bodyObject) {
        return post(chatApi, bodyObject);
    }


}
