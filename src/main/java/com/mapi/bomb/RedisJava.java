package com.mapi.bomb;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mapi.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class RedisJava {
    public static void main(String[] args) {
        //���ӱ��ص� Redis ����
        Jedis jedis = RedisUtil.getJedis();
        System.out.println("���ӳɹ�");
       jedis.set("elong", "flight.elong.com");
       
       System.out.println("redis �洢���ַ�����" + jedis.get("elong"));
       
       jedis.lpush("type", "flight");
       jedis.lpush("type", "train");
       jedis.lpush("type", "bus");
       
       List<String> list = jedis.lrange("type", 0, 2);
       System.out.println("redis �洢��List" +list);
              
       Set<String> keys = jedis.keys("*");
       Iterator<String> iterator = keys.iterator();
       while(iterator.hasNext()){
    	   String next = iterator.next();
    	   System.out.println("key:" + next);
       }
    }
}