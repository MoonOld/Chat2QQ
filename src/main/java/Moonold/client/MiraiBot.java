package Moonold.client;

import Moonold.entity.Model;
import Moonold.entity.chat.Role;
import Moonold.entity.chat.request.ChatRequestBody;
import Moonold.entity.chat.response.ChatResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
import okhttp3.Response;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class MiraiBot {
    private final Bot bot;
    private ChatRequestBody chatRequestBody = null;
    private Map<Long, ChatResponseBody> contexts = new ConcurrentHashMap<>();
    private final OpenAIChatClient openAIChatClient;
    private final ObjectMapper objectMapper;

    public MiraiBot() {
        bot = BotFactory.INSTANCE.newBot(Long.valueOf(System.getenv("MIRAI_QQ")), System.getenv("MIRAI_PASS"),
                new BotConfiguration() {{
                    setProtocol(MiraiProtocol.IPAD);
                    fileBasedDeviceInfo();
                    redirectBotLogToFile();
                }});
        openAIChatClient = new OpenAIChatClient();
        objectMapper = new ObjectMapper();
    }

    public void login() {
        if (!bot.isOnline()) bot.login();
    }

    public void testMessage(String message) {
        bot.getFriend(774705407L).sendMessage(message);
    }

    public void startListen() {
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

    public void startChat() {
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            MessageChain messages = event.getMessage();
            if (event.getSender().getId() == Long.parseLong(System.getenv("MIRAI_OWNER")) && messages.contentToString().startsWith("/enablechat")) {
                event.getGroup().sendMessage(enableChat(messages));
            } else if (chatRequestBody != null && messages.contains(new At(bot.getId()))) {
                StringBuilder sb = new StringBuilder();
                for (Message toCheck : messages) {
                    if (!(toCheck instanceof At)) {
                        sb.append(toCheck.contentToString());
                    }
                }
                event.getGroup().sendMessage(continueChat(sb.toString()));
            }
        });
    }

    @SneakyThrows
    public String continueChat(String chats) {
        if (chatRequestBody == null) {
            return "暂不支持其他指令或内容";
        }
        ChatRequestBody bodyClone = chatRequestBody.clone();
        bodyClone.addNewMessage(Role.user, chats);

        try {
            Response response = openAIChatClient.post(bodyClone);
            ChatResponseBody chatResponseBody = objectMapper.readValue(response.body().string(), ChatResponseBody.class);

            // success post and read
            String content = chatResponseBody.getContents();
            chatRequestBody.addNewMessage(Role.user,chats);
            chatRequestBody.addNewMessage(Role.assistant, content);
            return content;
        } catch(Exception e) {
            return e.getMessage();
        }
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
                    return "不正确的context长度参数，请输入数字";
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
        if( ( ctxLength %2 != 0 && ctxLength !=1 )  || ctxLength>20 || ctxLength < 1 || (ctxLength <2 && !sysCmd.isEmpty())){
            // 不为1的奇数、不在1到20、有sys且长度为1（此时无法写入新messages）
            return "不正确的context长度参数。请输入1或小于20的偶数，并保证提供System指令时长度大于等于2";
        }
        chatRequestBody = new ChatRequestBody.Builder()
                    .model(Model.gpt_3_5)
                    .ctxLength(ctxLength)
                    .build();
        if(!sysCmd.isEmpty()){
            chatRequestBody.addNewMessage(Role.system,sysCmd);
            chatRequestBody.setCtxLength(ctxLength +1);
        }

        return "已开启聊天";

    }

}
