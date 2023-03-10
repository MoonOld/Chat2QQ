package Moonold.client;

import junit.framework.TestCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class MiraiBotTest extends TestCase {

    public void testTestMessage() {
        MiraiBot.login();
        MiraiBot.testMessage("test");
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
        MiraiBot.login();
        MiraiBot.startListen();
        try {
            while (true) {
                Thread.sleep(1000L);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}