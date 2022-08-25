package map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Vladimir", 0, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User user2 = new User("Alexander", 2, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println("User1: \n hashcode: " + hashCode1 + "\n hash: " + hash1 + "\n bucket" + bucket1 + "\n");
        System.out.println("User2: \n hashcode: " + hashCode2 + "\n hash: " + hash2 + "\n bucket" + bucket2  + "\n");
    }
}
