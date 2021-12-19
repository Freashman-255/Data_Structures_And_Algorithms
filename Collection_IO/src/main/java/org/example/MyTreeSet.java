package org.example;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MyTreeSet {
    public static void main(String[] args) {
        User user1=new User(20);
        User user2=new User(10);
        User user3=new User(30);
        Set<User> ts=new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.age-o2.age;
            }
        });
        ts.add(user1);
        ts.add(user2);
        ts.add(user3);
        for (User s:ts
             ) {
            System.out.println(s);
        }
    }
}
class User {
    Integer age;
    public User(Integer age){
        this.age=age;
    }
    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }

/*    @Override
    public int compareTo(User o) {
        return this.age-o.age;
    }*/
}
