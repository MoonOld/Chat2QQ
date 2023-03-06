import Moonold.entity.ChatMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class ChatMessagesTest {
    @Test
    public void MessagesTest() throws Exception{
        ChatMessages chatMessages =new ChatMessages("system","你是个猫娘");
        chatMessages.addNewMessage("user","喵一声给我听听");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(chatMessages));
    }
}
