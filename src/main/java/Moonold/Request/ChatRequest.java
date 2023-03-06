package Moonold.Request;

import Moonold.entity.ChatMessages;
import Moonold.entity.Model;

public class ChatRequest {
    Model model;
    ChatMessages messages;

    private ChatRequest(Builder builder){
        this.model = builder.model;
        this.messages = builder.messages;
    }

    public static class Builder {
        private Model model;
        private ChatMessages messages;
        private int hashInt = 0;

        public Builder(){}

        public Builder model(String model){
            this.model = Model.getModel(model);
            hashInt |= 1;
            return this;
        }

        public Builder message(String role,String content){
            this.messages = new ChatMessages(role,content);
            hashInt |= 1<<1;
            return this;
        }

        public ChatRequest build(){
            if(hashInt!=3){
                throw new IllegalArgumentException("Not enough params to build!");
            }
            return new ChatRequest(this);
        }

    }

}
