package com.anand.kafka.kafka;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class SimpleProducer
{
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 30; i++) {
            Thread.sleep(1000);
            if(i%10==0)
                producer.send(new ProducerRecord<String, String>("streams-input-topic", "a", Integer.toString(i)));
            else if(i%3==0)
                producer.send(new ProducerRecord<String, String>("streams-input-topic1", "a", Integer.toString(i)));
            else
                producer.send(new ProducerRecord<String, String>("streams-input-topic1", "count", Integer.toString(i)));
        }
        producer.close();
    }
}
