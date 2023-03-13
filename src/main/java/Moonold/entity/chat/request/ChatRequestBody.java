package Moonold.entity.chat.request;

import Moonold.entity.Model;
import Moonold.entity.chat.Message;
import Moonold.entity.chat.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class ChatRequestBody {
    Model model;
    List<Message> messages;

    @JsonIgnore
    private int ctxLength=1;

    private ChatRequestBody(Builder builder){
        this.model = builder.model;
        this.messages = builder.messages;
        this.ctxLength = builder.ctxLength;
    }

    public static class Builder {
        private Model model;
        private List<Message> messages;
        private int hashInt = 0;
        private int ctxLength = 1;

        public Builder(){}

        public Builder model(String model){
            this.model = Model.getModel(model);
            hashInt |= 1;
            return this;
        }

        public Builder message(String role,String content){
            if(hashInt>>1 == 0 ){
                ChatMessages(role,content);
                hashInt |= 1<<1;
            } else {
                addNewMessage(role,content);
            }
            return this;
        }

        public Builder ctxLength(int length){
            if(length<=0){
                throw new IllegalArgumentException("context Length cant be lower than 1.");
            }
            this.ctxLength = length;
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
        if(messages.size()==ctxLength){
            Message bufMessage = messages.remove(0);
            if(messages.size()>1 && bufMessage.getRole().equals(Role.system)){
                messages.remove(0);
                messages.add(0,bufMessage);
            }
        }
        messages.add(newMessage);
    }

}
