package org.example.topic;

import com.rabbitmq.client.*;

public class CustomerTopic {
    public static void main(String[] args) throws Exception {

        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //服务地址
        factory.setHost("47.97.187.217");
        //账号
        factory.setUsername("admin");
        //密码
        factory.setPassword("123456");
        //端口
        factory.setPort(5672);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();

        //接收消息的回调函数
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接受到的消息：" + new String(message.getBody()));
        };
        //取消消息的回调函数
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消费消息被中断。");
        };

        String queueName1 = "topic_queue_name1";
        String queueName2 = "topic_queue_name2";
        String queueName3 = "topic_queue_name3";
        String queueName4 = "topic_queue_name4";

        /**
         * 消费消息
         * 1.消费队列名称
         * 2.消费成功是否自动应答
         * 3.接受消息的回调函数
         * 4.取消消息的回调函数
         */
        channel.basicConsume(queueName1, true, deliverCallback, cancelCallback);
        channel.basicConsume(queueName2, true, deliverCallback, cancelCallback);
        channel.basicConsume(queueName3, true, deliverCallback, cancelCallback);
        channel.basicConsume(queueName4, true, deliverCallback, cancelCallback);

    }
}
