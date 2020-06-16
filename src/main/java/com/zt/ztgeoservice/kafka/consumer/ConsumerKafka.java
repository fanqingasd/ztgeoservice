package com.zt.ztgeoservice.kafka.consumer;

import com.zt.ztgeoservice.websocket.MyWebSocket;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerKafka extends Thread {
private KafkaConsumer<String,String> consumer;
            private String topic = "alarmmessage";
            public ConsumerKafka(){
            }
            @Override
        public void run(){
                 //加载kafka消费者参数
                 Properties props = new Properties();
                 props.put("bootstrap.servers", "192.168.9.11:9092");
                 props.put("group.id", "ytna");
                 props.put("enable.auto.commit", "true");
                 props.put("auto.commit.interval.ms", "1000");
                 props.put("session.timeout.ms", "15000");
                 props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                 props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                 //创建消费者对象
                 consumer = new KafkaConsumer<String,String>(props);
                 consumer.subscribe(Arrays.asList(this.topic));
                 //死循环，持续消费kafka
                 while (true){
                     try {
                        //消费数据，并设置超时时间
                         ConsumerRecords<String, String> records = consumer.poll(100);
                         //Consumer message
                         for (ConsumerRecord<String, String> record : records) {
                             //Send message to every client
                             MyWebSocket myWebSocket=new MyWebSocket();
                             for (MyWebSocket webSocket :myWebSocket.webSocketSet){
                                 webSocket.sendMessage(record.value());
                             }
                         }
                     }catch (IOException e){
                         System.out.println(e.getMessage());
                         continue;
                    }
                 }
             }

             public void close() {
                 try {
                     consumer.close();
                 } catch (Exception e) {
                    System.out.println(e.getMessage());
                 }
             }

            //测试用启动线程
             public static void main(String[] args){
                 ConsumerKafka consumerKafka = new ConsumerKafka();
                consumerKafka.start();
             }
         }
