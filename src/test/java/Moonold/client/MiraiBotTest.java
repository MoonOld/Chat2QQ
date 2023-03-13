package Moonold.client;

import junit.framework.TestCase;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class MiraiBotTest extends TestCase {

    public void testTestMessage() {
        MiraiBot bot = new MiraiBot();
        bot.login();
        bot.testMessage("test");
    }

    public void testAtMessage(){
        MessageChain messageChain = new MessageChainBuilder().append("abc").append(new At(102030L))
        .append("add").build();
        for(Message message :messageChain){
            if(message instanceof At){
                System.out.println(message.contentToString());
            }
        }
    }

    public void testGroupListen(){

//        ExecutorService threadPoolExecutor =  Executors.newSingleThreadExecutor();
//        threadPoolExecutor.execute(()-> {
//            MiraiBot.login();
//            MiraiBot.startListen();
//            try {
//                while (true) {
//                    Thread.sleep(1000L);
//                }
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        });
        MiraiBot bot = new MiraiBot();
        bot.login();
        bot.startListen();
        try {
            while (true) {
                Thread.sleep(1000L);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void testEnableChat(){
        MiraiBot bot = new MiraiBot();

        MessageChain messageChain = new MessageChainBuilder().append("/enable 1a ").build();
        System.out.println(bot.enableChat(messageChain));

        messageChain = new MessageChainBuilder().append("/enable 5 ").build();
        System.out.println(bot.enableChat(messageChain));

        messageChain = new MessageChainBuilder().append("/enable ").build();
        System.out.println(bot.enableChat(messageChain));

        messageChain = new MessageChainBuilder().append("/enable 25").build();
        System.out.println(bot.enableChat(messageChain));

        messageChain = new MessageChainBuilder().append("/enable 1 abc").build();
        System.out.println(bot.enableChat(messageChain));

        messageChain = new MessageChainBuilder().append("/enable 10 abc").build();
        System.out.println(bot.enableChat(messageChain));
    }

    public void testContinueChat(){
        MiraiBot bot = new MiraiBot();

        System.out.println(bot.continueChat("a"));

        MessageChain messageChain = new MessageChainBuilder().append("/enable 10 你是一个可爱的猫娘助手").build();
        bot.enableChat(messageChain);
        System.out.println(bot.continueChat("亲亲"));
    }


    public void testChatBot(){
        MiraiBot bot = new MiraiBot();
        bot.login();
        bot.startChat();
        try {
            while (true) {
                Thread.sleep(1000L);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}