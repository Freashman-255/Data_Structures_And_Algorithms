package org.example;

import javax.jws.Oneway;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Collection c=new ArrayList();
        c.add(1);
        c.add(2);
        c.add(3);
        Iterator it=c.iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
        System.out.println(c.size());
        System.out.println(10>>2);
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"adb");
        map.put(2,"def");
        map.put(3,"ghk");
        Set<Map.Entry<Integer,String>> set=map.entrySet();
        for (Map.Entry<Integer,String> entry:set
             ) {
            System.out.println(entry.getKey()+"   "+entry.getValue());
        }
    }
}
