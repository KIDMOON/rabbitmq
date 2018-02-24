package com.test.rabbtmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author：Kid date:2018/2/24
 */
public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String ...args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
        System.out.println("P [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

}
