package Moonold.client;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

import java.awt.*;

public class MiraiBot {
    public static final Bot bot;
    static {

        bot = BotFactory.INSTANCE.newBot(Long.valueOf(System.getenv("MIRAI_QQ")), System.getenv("MIRAI_PASS"),
                new BotConfiguration(){{
                setProtocol(MiraiProtocol.IPAD);
            }});
    }
    public static void login(){
        if(!bot.isOnline())bot.login();
    }

    public static void testMessage(String message){
        bot.getFriend(774705407L).sendMessage(message);
    }

}
