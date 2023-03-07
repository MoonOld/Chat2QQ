package Moonold.entity.chat;

import lombok.Data;

import java.io.Serializable;

//public class ChatMessages implements Serializable {
//    private List<Message> messages;
//
//    public ChatMessages(String role,String content){
//        messages = new LinkedList<>();
//        addNewMessage(role,content);
//    }
//
//    public ChatMessages addNewMessage(String role,String content){
//        Message newMessage = new Message(role,content);
//        messages.add(newMessage);
//        return this;
//    }
//
//
//}


@Data
public class Message implements Serializable{
    private String role;
    private String content;
    public Message(){}
    public Message(String role, String content){
        this.content = content;
        if(Role.roleSet.contains(role)){
            this.role = role;
        }
        throw new IllegalArgumentException("Invalid Role :"+role);
    }

}
