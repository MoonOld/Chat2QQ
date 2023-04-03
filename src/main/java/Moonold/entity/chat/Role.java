package Moonold.entity.chat;

import java.util.HashSet;
import java.util.Set;

public class Role {
    public static String system = "system";
    public static String user = "user";
    public static String assistant = "assistant";
    public static Set<String> roleSet = new HashSet<>();

    static {
        roleSet.add(system);
        roleSet.add(user);
        roleSet.add(assistant);
    }
}
