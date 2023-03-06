package Moonold.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class ChatMessages implements Serializable {
    private List<Message> messages;

    public ChatMessages(String role,String content){
        messages = new LinkedList<>();
        addNewMessage(role,content);
    }

    public ChatMessages addNewMessage(String role,String content){
        Message newMessage = new Message(role,content);
        messages.add(newMessage);
        return this;
    }

    @Data
    private class Message implements Serializable{
        Role role;
        String content;
        Message(String role, String content){
            this.content = content;
            for(Role element : Role.values()){
                if(element.name().equals(role)){
                    this.role = element;
                    return;
                }
            }
            throw new IllegalArgumentException("Invalid Role :"+role);
        }
    }
    private enum Role{
        system,user,assistant;
    }
}


