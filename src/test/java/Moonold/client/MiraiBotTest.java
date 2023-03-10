package Moonold.client;

import junit.framework.TestCase;

public class MiraiBotTest extends TestCase {

    public void testTestMessage() {
        MiraiBot.login();
        MiraiBot.testMessage("test");
    }
}