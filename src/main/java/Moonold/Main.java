package Moonold;

import Moonold.client.MiraiBot;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
//    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) {
        MiraiBot bot = new MiraiBot();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            bot.login();
            bot.startChat();
        });

    }

}