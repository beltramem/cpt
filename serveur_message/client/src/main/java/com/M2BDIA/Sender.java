package com.M2BDIA;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class Sender {

    private String queue;

    public Sender(String queue)
    {
        this.queue = queue;
    }

    public void send_string(String message ) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare(this.queue, false, false, false, null);
            channel.basicPublish("",this.queue,false,null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("message envoye");
        }
    }

    public void send_mesure(Mesure mesure) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        try (Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare(this.queue, false, false, false, null);
            byte[] mesureByte = SerializationUtils.serialize(mesure);
            channel.basicPublish("",this.queue,false,null,mesureByte);
            System.out.println("obj envoye");
        }
        catch (Exception exe){
            exe.printStackTrace();
        }
    }
}
