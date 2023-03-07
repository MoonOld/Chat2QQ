package Moonold.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ChatRequestBody {
    Model model;
    List<Message> messages = new LinkedList<>();

    private ChatRequestBody(Builder builder){
        this.model = builder.model;
        this.messages = builder.messages;
    }

    public static class Builder {
        private Model model;
        private List<Message> messages;
        private int hashInt = 0;

        public Builder(){}

        public Builder model(String model){
            this.model = Model.getModel(model);
            hashInt |= 1;
            return this;
        }

        public Builder message(String role,String content){
            ChatMessages(role,content);
            hashInt |= 1<<1;
            return this;
        }

        public ChatRequestBody build(){
            if(hashInt!=3){
                throw new IllegalArgumentException("Not enough params to build!");
            }
            return new ChatRequestBody(this);
        }


        private  void ChatMessages(String role,String content) {
            if (messages == null) {
                messages = new LinkedList<>();
            }
            addNewMessage(role, content);
        }

        public void addNewMessage(String role,String content){
            Message newMessage = new Message(role,content);
            messages.add(newMessage);
        }
    }
    public void addNewMessage(String role,String content){
        Message newMessage = new Message(role,content);
        messages.add(newMessage);
    }

}
