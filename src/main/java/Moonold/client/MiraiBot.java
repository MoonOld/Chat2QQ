package Moonold.client;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.DeviceInfo;

import java.awt.*;

public class MiraiBot {
    private static final Bot bot;
    static {

        bot = BotFactory.INSTANCE.newBot(Long.valueOf(System.getenv("MIRAI_QQ")), System.getenv("MIRAI_PASS"),
                new BotConfiguration(){{
                setProtocol(MiraiProtocol.IPAD);
                fileBasedDeviceInfo();
            }});
    }
    public static void login(){
        if(!bot.isOnline())bot.login();
    }

    public static void testMessage(String message){
        bot.getFriend(774705407L).sendMessage(message);
    }

    public static void startListen(){
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            // 判断是否为特定群
            if (event.getGroup().getId() == 761538612L) {
                // 获取消息内容
                String message = event.getMessage().contentToString();
                // 判断消息内容并作出反馈
                if (message.equals("你好")) {
                    MessageChainBuilder builder = new MessageChainBuilder();
                    builder.append(new At(event.getSender().getId()));
                    builder.append(" ");
                    builder.append("你好！");
                    event.getGroup().sendMessage(builder.build());
                }
            }
            long groupId = event.getGroup().getId();
            String message = event.getMessage().contentToString();
            System.out.println("Group ID: " + groupId + ", Message: " + message);
        });
    }

}
