package com.anand.kafka.kafka;


import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
 
 public static void main(String[] args) throws Exception{
    
    // Check arguments length value
    if(args.length == 0){
       System.out.println("Enter topic name");
       return;
    }
    
    //Assign topicName to string variable
    String topicName = args[0].toString();
    
    // create instance for properties to access producer configs   
    Properties props = new Properties();
    
    //Assign localhost id [brokers hostname]
    props.put("bootstrap.servers", "192.168.0.8:9092");
    
    //Set acknowledgements for producer requests.      
    props.put("acks", "all");
    
    //If the request fails, the producer can automatically retry,
    props.put("retries", 0);
    
    //Specify buffer size in config
    props.put("batch.size", 16384);
    
    //Reduce the no of requests less than 0   
    props.put("linger.ms", 1);
    
    //The buffer.memory controls the total amount of memory available to the producer for buffering.   
    props.put("buffer.memory", 33554432);
    
    //Serializer class for key that implements the Serializer interface.
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    
    //Serializer class for value that implements the Serializer interface.
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    
    //<string,string> represent key-value pair of message.
    Producer<String, String> producer = new KafkaProducer<String, String>(props);
          
    for(int i = 0; i < 10; i++){
       //topicName , key,value. Key represent which partition to put the data.	
       if (i==9)
       {
    	   producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), "end of first call"));
    	   
       }
       else
       {
       producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)));
       }
    }
             System.out.println("Message sent successfully");
             producer.close();
 }
}