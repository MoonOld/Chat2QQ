package Moonold;
import Moonold.client.MiraiBot;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
//    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) throws Exception {
        MiraiBot bot = new MiraiBot();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()->{
            bot.login();
            bot.startChat();
        });

    }

}