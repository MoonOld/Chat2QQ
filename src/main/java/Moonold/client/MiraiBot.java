package Moonold.client;

import Moonold.entity.Model;
import Moonold.entity.chat.Role;
import Moonold.entity.chat.request.ChatRequestBody;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.DeviceInfo;

import java.awt.*;
import java.util.Scanner;

public class MiraiBot {
    private  final Bot bot;
    private ChatRequestBody chatRequestBody=null;

    public MiraiBot(){
        bot = BotFactory.INSTANCE.newBot(Long.valueOf(System.getenv("MIRAI_QQ")), System.getenv("MIRAI_PASS"),
                new BotConfiguration(){{
                    setProtocol(MiraiProtocol.IPAD);
                    fileBasedDeviceInfo();
                }});
    }
    public  void login(){
        if(!bot.isOnline())bot.login();
    }

    public  void testMessage(String message){
        bot.getFriend(774705407L).sendMessage(message);
    }

    public  void startListen(){
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

    public void startChat(){
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event ->{
            MessageChain messages = event.getMessage();
            if(messages.contentToString().startsWith("/enablechat")){
                enableChat(messages);
            } else if( chatRequestBody != null && messages.contains(new At(bot.getId()))){
                StringBuilder sb = new StringBuilder();
                for(Message toCheck : messages){
                    if(!(toCheck instanceof At)){
                        sb.append(toCheck.contentToString());
                    }
                }
                continueChat(sb.toString());
            }
        });
    }

    private  String continueChat(String chats) {
        return "ok";
    }

    public String enableChat(MessageChain messages) {
        String cmd = messages.contentToString().trim();
        char[] chars = cmd.toCharArray();
        int spaceCount = 0;
        int left=-1,right =-1;
        String length="1",sysCmd="";
        for(int i =0;i<chars.length;i++){
            if(chars[i]== ' '){
                spaceCount++;

                //pass blank
                while(chars[i+1]==' '){
                    i++;
                }
            } else if(spaceCount == 1) {
                if(chars[i]>'9' || chars[i]<'0'){
                    return "不正确的context长度参数，请输入1～20的数字";
                }
                //first & not blank
                if (left < 0) {
                    left = i;
                }
                if (i<chars.length-1 && chars[i + 1] == ' ') {
                    //first & not blank in end
                    right = i + 1;
                }

            }
        }
        if( left > 0){
            // if what follows cmd
            if( right<0 ){
                // if
                right = chars.length;
            }
            length = cmd.substring(left,right);
            sysCmd = cmd.substring(right).trim();
        }
        int ctxLength = Integer.parseInt(length);
        if(ctxLength>20 || ctxLength < 1){
            return "不正确的context长度参数，请输入1～20的数字";
        }
        chatRequestBody = new ChatRequestBody.Builder()
                    .model(Model.gpt_3_5)
                    .ctxLength(Integer.parseInt(length))
                    .build();
        if(!sysCmd.isEmpty())chatRequestBody.addNewMessage(Role.system,sysCmd);

        return "已开启聊天";

    }

}
