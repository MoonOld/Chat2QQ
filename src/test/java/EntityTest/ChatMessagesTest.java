package EntityTest;

import Moonold.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class ChatMessagesTest {
    @Test
    public void MessagesTest() throws Exception{
        Message chatMessages =new Message("system","你是个猫娘");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(chatMessages));
    }
}
