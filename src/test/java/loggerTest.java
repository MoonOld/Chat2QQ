import Moonold.client.MiraiBot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.awt.*;

public class loggerTest {
    public static final Logger log = LogManager.getLogger(loggerTest.class);

    public static final MiraiBot miraiBot= new MiraiBot();

    @Test
    public void testLog(){
        log.error("test Something");
        System.out.println(miraiBot);
    }
}
